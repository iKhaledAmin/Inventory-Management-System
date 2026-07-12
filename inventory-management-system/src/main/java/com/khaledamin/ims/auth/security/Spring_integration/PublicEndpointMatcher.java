package com.khaledamin.ims.auth.security.Spring_integration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;


@Component
public class PublicEndpointMatcher {

    private final RequestMatcher matcher;


    public PublicEndpointMatcher() {

        this.matcher = new OrRequestMatcher(
                PathPatternRequestMatcher.withDefaults()
                        .matcher("/auth/**"),

                PathPatternRequestMatcher.withDefaults()
                        .matcher("/swagger-ui/**"),

                PathPatternRequestMatcher.withDefaults()
                        .matcher("/v3/api-docs/**"),

                PathPatternRequestMatcher.withDefaults()
                        .matcher("/media/images/**")
        );
    }


    public boolean isPublic(HttpServletRequest request) {
        return matcher.matches(request);
    }


    public RequestMatcher matcher() {
        return matcher;
    }
}