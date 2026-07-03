package com.khaledamin.ims.core.logging.event;

import java.util.List;

public interface BusinessEventLogger {

    // Auth events
    void accountRegistered(String accountCode);
    void passwordResetRequested(String accountCode);

    // Account events
    void accountCreated(String accountCode);
    void accountUpdated(String accountCode);
    void accountActivated(String accountCode);
    void accountViewed(String accountCode);
    void accountListed(int page, int size, String sortBy, String direction);
    void accountPasswordReset(String accountCode);
    void accountRoleAssigned(String accountCode, String roleName);
    void accountRolesAssigned(String accountCode, List<String> roleNames);
    void accountRolesReplaced(String accountCode, List<String> roleNames);
    void accountRoleRemoved(String accountCode, String roleName);


    // Email events
    void emailQueued(Long emailId, String template, String recipient);
    void emailSent(Long emailId, String template, String recipient,int retryCount);
    void emailSendFailed(Long emailId, String template, String recipient,int retryCount);



    // Verification events
    void verificationTokenGenerated(Long tokenId, String tokenType, String targetActorType, String targetActorCode);
    void tokenVerificationSucceeded(Long tokenId, String tokenType, String targetActorType, String targetActorCode);
    void tokenVerificationFailed(Long tokenId, String tokenType, String targetActorType, String targetActorCode, String reason);


    // Role events
    void roleCreated(String roleName);
    void roleUpdated(String roleName);
    void roleDeleted(String roleName);
    void roleViewed(String roleName);
    void roleListed();
    void roleCapabilityAssigned(String roleName, String capabilityCode);
    void roleCapabilityRemoved(String roleName, String capabilityCode);


    // Capability events
    void capabilityCreated(String capabilityCode);
    void capabilityUpdated(String capabilityCode);
    void capabilityDeleted(String capabilityCode);
    void capabilityViewed(String capabilityCode);
    void capabilityListed(String domain);

    // Organization events
    void organizationCreated(String organizationCode);
    void organizationUpdated(String organizationCode);
    void organizationViewed(String organizationCode);

    // Stock events
    void stockCreated(String stockCode);
    void StockUpdated(String stockCode);
    void stockDeleted(String stockCode);
    void stockViewed(String stockCode);
    void stockRestocked(String stockCode );
    void stockListed(String organizationCode, int page, int size, String sortBy, String direction);
    void stockBatchesListed(String stockCode, int page, int size, String sortBy, String direction);

}