package com.khaledamin.ims.auth.account.application.service;

import com.khaledamin.ims.auth.account.api.dto.*;
import com.khaledamin.ims.core.api.response.ApiActionResponse;

public interface AccountAuthService {


    AccountRegistrationResponse register(AccountRegistrationRequest request);

    AccountActivationResponse activate(AccountActivationRequest request);

    AccountLoginResponse login(AccountLoginRequest request);

    ApiActionResponse requestResetPassword(AccountResetPasswordRequest request);

    ApiActionResponse resetPassword(AccountConfirmResetPasswordRequest request);
}
