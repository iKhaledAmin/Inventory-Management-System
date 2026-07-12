package com.khaledamin.ims.auth.security.Spring_integration;

import com.khaledamin.ims.auth.security.core.jwt.JwtPayload;
import com.khaledamin.ims.auth.security.core.jwt.JwtService;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.auth.security.core.principal.PrincipalResolverRegistry;
import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.auth.security.exception.CustomSecurityException;
import com.khaledamin.ims.core.logging.event.SecurityEventLogger;
import com.khaledamin.ims.core.logging.core.ActorLoggingContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PrincipalResolverRegistry principalResolverRegistry;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final SecurityEventLogger securityEventLogger;
    private final PublicEndpointMatcher publicEndpointMatcher;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Skip if already authenticated
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }


        if (publicEndpointMatcher.isPublic(request)){
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String token = extractToken(request);

            // Parse token (snapshot of the token)
            JwtPayload payload = jwtService.extractPayload(token);

            // Resolve correct principal (ACCOUNT / CLIENT / SERVICE)
            AuthenticatedPrincipal principal = principalResolverRegistry.resolve(payload);

            // Validate using PAYLOAD (NOT TOKEN)
            jwtService.validateToken(payload, principal);

            // Set actor context
            ActorLoggingContext.put(principal); // here we put the principal in the MDC (any date we needed along the request)

            // Build Authentication
            UsernamePasswordAuthenticationToken auth = buildAuthentication(principal);
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Log authentication success
            securityEventLogger.authenticationSucceeded(principal);

        } catch (CustomSecurityException | TechnicalException ex)  {

            // Log authentication failures
            if (ex instanceof CustomSecurityException customSecurityException){
                securityEventLogger.authenticationFailed(customSecurityException);
            }

            SecurityContextHolder.clearContext();

            authenticationEntryPoint.commence(
                    request,
                    response,
                    new InsufficientAuthenticationException(
                            ex.getMessage(),
                            ex
                    )
            );

            return;
        }

        filterChain.doFilter(request, response);
    }


    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);
    }

    private UsernamePasswordAuthenticationToken buildAuthentication(AuthenticatedPrincipal principal) {

        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                principal.getGrantedAuthorities()
        );
    }
}