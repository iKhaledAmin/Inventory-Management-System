package com.khaledamin.ims.auth.security.core.jwt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtClaims {

    public static final String ACTOR_TYPE = "actor_type";
    public static final String ACTOR_CODE = "actor_code";

    public static final String ROLES = "roles";
    public static final String AUTHORITIES = "authorities";
}