package com.khaledamin.ims.auth.security.core.jwt;




import com.khaledamin.ims.auth.security.exception.AuthenticationException;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.core.exception.validation.ValidationException;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public  class JwtService {
    private final JwtProperties jwtProperties;
    private final JwtClaimsContributorRegistry claimsContributorRegistry;



    /**
     * Generates a signed JWT token for the given authenticated principal.
     *
     * @param principal {@link AuthenticatedPrincipal} principal used as the token identity source
     * @return token {@link String} signed JWT token
     */
    public String generateToken(AuthenticatedPrincipal principal) {
        return generateToken(new HashMap<>(), principal);
    }



    /**
     * Generates a signed JWT token for the provided authenticated principal.
     *
     * <p>
     * The token generation process consists of:
     * </p>
     * <ol>
     *     <li>Applying standard security claims
     *     (clientId, actor type, actor code, timestamps)</li>
     *
     *     <li>Resolving the appropriate
     *     {@link JwtClaimsContributor}
     *     for the authenticated principal</li>
     *
     *     <li>Adding actor-specific authorization claims
     *     such as roles and authorities</li>
     *
     *     <li>Signing the final JWT using the configured signing key</li>
     * </ol>
     *
     * <p>
     * Actor-specific claims are delegated to specialized contributors
     * to keep this service independent of authorization model clientDetails.
     * </p>
     *
     * @param extraClaims additional custom claims to include in the token
     * @param principal authenticated principal used as token identity source
     * @return signed JWT token
     * @throws IllegalStateException if no claims contributor is registered
     * for the provided principal type
     */
    public String generateToken(Map<String, Object> extraClaims, AuthenticatedPrincipal principal) {

        Date expirationDate = resolveExpirationDate(principal);

        JwtBuilder builder = Jwts.builder()
                .claims(extraClaims)
                .subject(principal.getSubject())
                .claim(JwtClaims.ACTOR_TYPE, principal.getActorType().name())
                .claim(JwtClaims.ACTOR_CODE, principal.getActorCode().toString())
                .issuedAt(new Date())
                .expiration(expirationDate);

        JwtClaimsContributor<AuthenticatedPrincipal> contributor = claimsContributorRegistry.get(principal);

        contributor.contribute(builder, principal);

        return builder
                .signWith(getSignInKey())
                .compact();
    }

    /**
     * Extracts and validates the JWT payload from the provided token.
     *
     * <p>
     * This method validates token structure, signature, and required claims
     * before mapping the token into a {@link JwtPayload}.
     * </p>
     *
     * @param token {@link String} raw JWT token
     * @return payload {@link JwtPayload} extracted JWT payload
     * @throws SecurityException if the token is invalid, malformed,
     * expired, unsupported, or missing required claims
     */
    public JwtPayload extractPayload(String token) {

        Claims claims = extractAllClaims(token);

        // ================= ACTOR =================

        ActorType actorType = extractActorType(claims);

        ActorCode actorCode = extractActorCode(claims);

        // ================= SUBJECT =================

        String subject = claims.getSubject();

        if (subject == null || subject.isBlank()) {

            throw AuthenticationException.invalidToken()
                    .withDebugDetails("reason", "Token clientId is missing");
        }



        // ================= TIME CLAIMS =================

        Date issuedAt = claims.getIssuedAt();

        Date expiration = claims.getExpiration();

        if (expiration == null) {

            throw AuthenticationException.invalidToken()
                    .withDebugDetails("reason", "Token expiration claim is missing");
        }

        // ================= AUTHORIZATION CLAIMS =================

        Set<String> roles = extractRoles(claims);

        Set<String> authorities = extractAuthorities(claims);

        return new JwtPayload(
                subject,
                actorType,
                actorCode,
                issuedAt,
                expiration,
                roles,
                authorities
        );
    }

    /**
     * Validates that the provided JWT payload matches the authenticated principal.
     *
     * <p>
     * Validation includes:
     * </p>
     * <ul>
     *     <li>clientId consistency</li>
     *     <li>actor type consistency</li>
     *     <li>token expiration</li>
     *     <li>principal active state</li>
     *     <li>principal locked state</li>
     * </ul>
     *
     * @param payload {@link JwtPayload} extracted JWT payload
     * @param principal {@link AuthenticatedPrincipal}resolved authenticated principal
     * @throws SecurityException if validation fails
     */
    public void validateToken(JwtPayload payload, AuthenticatedPrincipal principal) {

        // ================= TOKEN CONSISTENCY =================

        if (!principal.supportsToken(payload.getSubject())) {

            throw AuthenticationException.invalidToken()
                    .withDebugDetails("reason", "Token clientId mismatch")
                    .withDebugDetails("tokenSubject", payload.getSubject())
                    .withDebugDetails("principalSubject", principal.getSubject());
        }

        if (payload.getActorType() != principal.getActorType()) {

            throw AuthenticationException.invalidToken()
                    .withDebugDetails("reason", "Actor type mismatch")
                    .withDebugDetails("tokenActorType", payload.getActorType())
                    .withDebugDetails("principalActorType", principal.getActorType());
        }

        if (!payload.getActorCode().equals(principal.getActorCode())) {

            throw AuthenticationException.invalidToken()
                    .withDebugDetails("reason", "Actor code mismatch")
                    .withDebugDetails("tokenActorCode", payload.getActorCode())
                    .withDebugDetails("principalActorCode", principal.getActorCode());
        }

        // ================= TOKEN STATE =================

        if (isTokenExpired(payload)) {

            throw AuthenticationException.expiredToken()
                    .withDebugDetails("reason", "Token expired")
                    .withDebugDetails("expiration", payload.getExpiration());
        }

        // ================= PRINCIPAL STATE =================

        if (principal.isLocked()) {
            String principalType = principal.getActorType().name();
            throw AuthenticationException.principalLocked(principalType.toLowerCase(Locale.ROOT))
                    .withDebugDetails("reason", "Principal is locked")
                    .withDebugDetails("actorType",principalType)
                    .withDebugDetails("clientId", principal.getSubject());
        }

        if (!principal.isActive()) {
            String principalType = principal.getActorType().name();
            throw AuthenticationException.principalInactive(principalType.toLowerCase(Locale.ROOT))
                    .withDebugDetails("reason", "Principal is inactive")
                    .withDebugDetails("actorType",principalType)
                    .withDebugDetails("clientId", principal.getSubject());
        }
    }


    /**
     * Extracts a specific claim from the provided JWT token.
     *
     * @param token {@link String} raw JWT token
     * @param claimsResolver function used to extract the target claim
     * @param <T> extracted claim type
     * @return extracted claim value
     * @throws SecurityException if the token is invalid or cannot be parsed
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }





    private Date resolveExpirationDate(AuthenticatedPrincipal principal) {
        long expirationMinutes = jwtProperties.getExpirationMinutes(principal.getActorType());
        long expirationMillis = expirationMinutes * 60 * 1000;
        return new Date(System.currentTimeMillis() + expirationMillis);
    }

    private ActorType extractActorType(Claims claims) {

        try {

            String actorTypeRaw = claims.get(JwtClaims.ACTOR_TYPE, String.class);

            if (actorTypeRaw == null || actorTypeRaw.isBlank()) {
                throw AuthenticationException.invalidToken()
                        .withDebugDetails("reason", "Actor type claim is missing");
            }

            return ActorType.from(actorTypeRaw);

        } catch (RequiredTypeException | ValidationException ex) {
            throw AuthenticationException.invalidToken(ex)
                    .withDebugDetails("reason", "Invalid actor type");
        }
    }

    private ActorCode extractActorCode(Claims claims) {

        try {

            String actorCodeRaw = claims.get(JwtClaims.ACTOR_CODE, String.class);

            if (actorCodeRaw == null || actorCodeRaw.isBlank()) {

                throw AuthenticationException.invalidToken()
                        .withDebugDetails("reason", "Actor code claim is missing");
            }

            return ActorCode.of(actorCodeRaw);

        } catch (RequiredTypeException | ValidationException ex) {

            throw AuthenticationException.invalidToken(ex)
                    .withDebugDetails("reason", "Invalid actor code");
        }
    }

    private Set<String> extractRoles(Claims claims) {

        Object raw = claims.get(JwtClaims.ROLES);

        if (raw instanceof Collection<?> list) {
            return list.stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }

        return Set.of();
    }


    private Set<String> extractAuthorities(Claims claims) {

        Object raw = claims.get(JwtClaims.AUTHORITIES);

        if (raw instanceof Collection<?> list) {
            return list.stream()
                    .map(Object::toString)
                    .collect(Collectors.toUnmodifiableSet());
        }

        return Set.of();
    }

    private boolean isTokenExpired(JwtPayload payload) {
        return payload.getExpiration().before(new Date());
    }

    /**
     * Extracts the expiration date from the provided JWT token.
     *
     * @param token {@link String} raw JWT token
     * @return date {@link Date} token expiration date
     * @throws SecurityException if the token is invalid or cannot be parsed
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {

        try {

            return Jwts
                    .parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException ex) {

            throw AuthenticationException.expiredToken(ex)
                    .withDebugDetails("reason", "Token expired")
                    .withDebugDetails("expiration", ex.getClaims().getExpiration());

        } catch (MalformedJwtException ex) {

            throw AuthenticationException.malformedToken(ex)
                    .withDebugDetails("reason", "Malformed JWT token");

        } catch (SignatureException ex) {

            throw AuthenticationException.invalidTokenSignature(ex)
                    .withDebugDetails("reason", "Invalid JWT signature");

        } catch (UnsupportedJwtException ex) {

            throw AuthenticationException.invalidToken(ex)
                    .withDebugDetails("reason", "Unsupported JWT token");

        } catch (IllegalArgumentException ex) {

            throw AuthenticationException.invalidToken(ex)
                    .withDebugDetails("reason", "JWT token is missing or empty");
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
