package com.khaledamin.ims.verification.application.dto;


import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.verification.domain.model.TokenType;

public record VerificationResult(ActorIdentity target, TokenType type)
{}