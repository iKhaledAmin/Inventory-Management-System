package com.khaledamin.ims.auth.client.application.service;

import com.khaledamin.ims.auth.client.api.dto.ClientTokenRequest;
import com.khaledamin.ims.auth.client.api.dto.ClientTokenResponse;

public interface ClientAuthService {
    ClientTokenResponse generateToken(ClientTokenRequest request);
}
