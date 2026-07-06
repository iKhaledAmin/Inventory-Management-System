package com.khaledamin.ims.auth.security.core.jwt;

import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
@AllArgsConstructor
public class JwtPayload {

     // Technical authentication identity
     private final String subject; // username OR clientId

     // Business actor identity
     private final ActorType actorType; // ACCOUNT | CLIENT
     private final ActorCode actorCode;  // accountCode OR clientCode

     // Time-based security
     private final Date issuedAt;
     private final Date expiration;


     private final Set<String> roles;        // optional (human only)
     private final Set<String> authorities;   // PRIMARY AUTH MODEL
}