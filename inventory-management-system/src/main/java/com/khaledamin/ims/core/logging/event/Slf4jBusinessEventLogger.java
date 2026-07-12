package com.khaledamin.ims.core.logging.event;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.definition.LogCategory;
import com.khaledamin.ims.core.logging.definition.BusinessEvent;
import com.khaledamin.ims.core.logging.definition.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j(topic = "BusinessEventLogger")
@Component
public class Slf4jBusinessEventLogger implements BusinessEventLogger {


// --------------------- Auth events --------------------- //

    @Override
    public void accountRegistered(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.AUTH)
                .addKeyValue("event", BusinessEvent.ACCOUNT_REGISTERED)
                .addKeyValue("accountCode", accountCode)
                .log("account registered");
    }

    @Override
    public void passwordResetRequested(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.AUTH)
                .addKeyValue("event", BusinessEvent.PASSWORD_RESET_REQUESTED)
                .addKeyValue("accountCode", accountCode)
                .log("password reset requested");
    }

    // --------------------- End Auth events --------------------- //
    // --------------------- Account events --------------------- //
    @Override
    public void accountCreated(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_CREATED)
                .addKeyValue("accountCode", accountCode)
                .log("account created");

    }

    @Override
    public void accountUpdated(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_UPDATED)
                .addKeyValue("accountCode", accountCode)
                .log("account updated");

    }

    @Override
    public void accountActivated(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_ACTIVATED)
                .addKeyValue("accountCode", accountCode)
                .log("account activated");

    }

    @Override
    public void accountViewed(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_VIEWED)
                .addKeyValue("accountCode", accountCode)
                .log("account viewed");
    }

    @Override
    public void accountListed(int page, int size, String sortBy, String direction) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNTS_LISTED)
                .addKeyValue("page", page)
                .addKeyValue("size", size)
                .addKeyValue("sortBy", sortBy)
                .addKeyValue("direction", direction)
                .log("accounts listed");

    }

    @Override
    public void accountPasswordReset(String accountCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_PASSWORD_RESET)
                .addKeyValue("accountCode", accountCode)
                .log("account password reset");

    }

    @Override
    public void accountRoleAssigned(String accountCode, String roleName) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_ROLE_ASSIGNED)
                .addKeyValue("accountCode", accountCode)
                .addKeyValue("roleName", roleName)
                .log("account role assigned");

    }

    @Override
    public void accountRolesAssigned(String accountCode, List<String> roleNames) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_ROLES_ASSIGNED)
                .addKeyValue("accountCode", accountCode)
                .addKeyValue("roleNames", roleNames)
                .log("account roles assigned");

    }

    @Override
    public void accountRolesReplaced(String accountCode, List<String> roleNames) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_ROLES_REPLACED)
                .addKeyValue("accountCode", accountCode)
                .addKeyValue("roleNames", roleNames)
                .log("account roles replaced");

    }

    @Override
    public void accountRoleRemoved(String accountCode, String roleName) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ACCOUNT)
                .addKeyValue("event", BusinessEvent.ACCOUNT_ROLE_REMOVED)
                .addKeyValue("accountCode", accountCode)
                .addKeyValue("roleName", roleName)
                .log("account role removed");

    }

    // --------------------- End Account events --------------------- //


    // ---------------------- Email events ----------------------- //
    @Override
    public void emailQueued(Long emailId, String template, String recipient) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.EMAIL)
                .addKeyValue("event", BusinessEvent.EMAIL_QUEUED)
                .addKeyValue("emailId", emailId)
                .addKeyValue("template", template)
                .addKeyValue("recipient", recipient)
                .log("email queued");
    }

    @Override
    public void emailSent(Long emailId, String template, String recipient ,int retryCount) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.EMAIL)
                .addKeyValue("event", BusinessEvent.EMAIL_SENT)
                .addKeyValue("emailId", emailId)
                .addKeyValue("template", template)
                .addKeyValue("recipient", recipient)
                .addKeyValue("retryCount", retryCount)
                .log("email sent");
    }

    @Override
    public void emailSendFailed(Long emailId, String template, String recipient,int retryCount) {

        log.atWarn()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.EMAIL)
                .addKeyValue("event", BusinessEvent.EMAIL_SEND_FAILED)
                .addKeyValue("emailId", emailId)
                .addKeyValue("template", template)
                .addKeyValue("recipient", recipient)
                .addKeyValue("retryCount", retryCount)
                .log("email send failed");
    }

// ---------------------- End Email events ----------------------- //

// --------------------- Verification events --------------------- //

    @Override
    public void verificationTokenGenerated(Long tokenId, String tokenType, String targetActorType, String targetActorCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.VERIFICATION)
                .addKeyValue("event", BusinessEvent.VERIFICATION_TOKEN_GENERATED)
                .addKeyValue("tokenId", tokenId)
                .addKeyValue("tokenType", tokenType)
                .addKeyValue("targetActorType", targetActorType)
                .addKeyValue("targetActorCode", targetActorCode)
                .log("verification token generated");
    }

    @Override
    public void tokenVerificationSucceeded(Long tokenId, String tokenType, String targetActorType, String targetActorCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.VERIFICATION)
                .addKeyValue("event", BusinessEvent.VERIFICATION_TOKEN_SUCCEEDED)
                .addKeyValue("tokenId", tokenId)
                .addKeyValue("tokenType", tokenType)
                .addKeyValue("targetActorType", targetActorType)
                .addKeyValue("targetActorCode", targetActorCode)
                .log("token verification succeeded");
    }

    @Override
    public void tokenVerificationFailed(Long tokenId, String tokenType, String targetActorType, String targetActorCode, String reason) {
        log.atWarn()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.VERIFICATION)
                .addKeyValue("event", BusinessEvent.VERIFICATION_TOKEN_FAILED)
                .addKeyValue("tokenId", tokenId)
                .addKeyValue("tokenType", tokenType)
                .addKeyValue("targetActorType", targetActorType)
                .addKeyValue("targetActorCode", targetActorCode)
                .addKeyValue("reason", reason)
                .log("token verification failed");
    }

// --------------------- End Verification events --------------------- //


// --------------------- Role events --------------------- //

    @Override
    public void roleCreated(String roleName) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLE_CREATED)
                .addKeyValue("roleName", roleName)
                .log("role created");
    }

    @Override
    public void roleUpdated(String roleName) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLE_UPDATED)
                .addKeyValue("roleName", roleName)
                .log("role updated");
    }

    @Override
    public void roleDeleted(String roleName) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLE_DELETED)
                .addKeyValue("roleName", roleName)
                .log("role deleted");
    }

    @Override
    public void roleViewed(String roleName) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLE_VIEWED)
                .addKeyValue("roleName", roleName)
                .log("role viewed");
    }

    @Override
    public void roleListed() {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLES_LISTED)
                .log("roles listed");
    }

    @Override
    public void roleCapabilityAssigned(String roleName, String capabilityCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLE_CAPABILITY_ASSIGNED)
                .addKeyValue("roleName", roleName)
                .addKeyValue("capabilityCode", capabilityCode)
                .log("role capability assigned");
    }

    @Override
    public void roleCapabilityRemoved(String roleName, String capabilityCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ROLE)
                .addKeyValue("event", BusinessEvent.ROLE_CAPABILITY_REMOVED)
                .addKeyValue("roleName", roleName)
                .addKeyValue("capabilityCode", capabilityCode)
                .log("role capability removed");
    }

// --------------------- End Role events --------------------- //


// --------------------- Capability events --------------------- //

    @Override
    public void capabilityCreated(String capabilityCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CAPABILITY)
                .addKeyValue("event", BusinessEvent.CAPABILITY_CREATED)
                .addKeyValue("capabilityCode", capabilityCode)
                .log("capability created");
    }

    @Override
    public void capabilityUpdated(String capabilityCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CAPABILITY)
                .addKeyValue("event", BusinessEvent.CAPABILITY_UPDATED)
                .addKeyValue("capabilityCode", capabilityCode)
                .log("capability updated");
    }

    @Override
    public void capabilityDeleted(String capabilityCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CAPABILITY)
                .addKeyValue("event", BusinessEvent.CAPABILITY_DELETED)
                .addKeyValue("capabilityCode", capabilityCode)
                .log("capability deleted");
    }

    @Override
    public void capabilityViewed(String capabilityCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CAPABILITY)
                .addKeyValue("event", BusinessEvent.CAPABILITY_VIEWED)
                .addKeyValue("capabilityCode", capabilityCode)
                .log("capability viewed");
    }

    @Override
    public void capabilityListed(String domain) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CAPABILITY)
                .addKeyValue("event", BusinessEvent.CAPABILITIES_LISTED)
                .addKeyValue("domain", domain)
                .log("capabilities listed");
    }


    // --------------------- End Capability events --------------------- //



    // --------------------- Organization events --------------------- //

    @Override
    public void organizationCreated(String organizationCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ORGANIZATION)
                .addKeyValue("event", BusinessEvent.ORGANIZATION_CREATED)
                .addKeyValue("organizationCode", organizationCode)
                .log("organization created");
    }

    @Override
    public void organizationUpdated(String organizationCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ORGANIZATION)
                .addKeyValue("event", BusinessEvent.ORGANIZATION_UPDATED)
                .addKeyValue("organizationCode", organizationCode)
                .log("organization updated");
    }

    @Override
    public void organizationViewed(String organizationCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.ORGANIZATION)
                .addKeyValue("event", BusinessEvent.ORGANIZATION_VIEWED)
                .addKeyValue("organizationCode", organizationCode)
                .log("organization viewed");
    }

    // --------------------- End Organization events --------------------- //


    // --------------------- Stock events --------------------- //
    @Override
    public void stockCreated(String stockCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCK_CREATED)
                .addKeyValue("stockCode", stockCode)
                .log("stock created");
    }

    @Override
    public void StockUpdated(String stockCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCK_UPDATED)
                .addKeyValue("stockCode", stockCode)
                .log("stock updated");
    }

    @Override
    public void stockDeleted(String stockCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCK_DELETED)
                .addKeyValue("stockCode", stockCode)
                .log("stock deleted");
    }

    @Override
    public void stockViewed(String stockCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCK_VIEWED)
                .addKeyValue("stockCode", stockCode)
                .log("stock viewed");
    }

    @Override
    public void stockRestocked(String stockCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCK_RESTOCKED)
                .addKeyValue("stockCode", stockCode)
                .log("stock restocked");
    }

    @Override
    public void stockListed(String organizationCode, int page, int size, String sortBy, String direction) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCKS_LISTED)
                .addKeyValue("organizationCode",organizationCode)
                .addKeyValue("page", page)
                .addKeyValue("size", size)
                .addKeyValue("sortBy", sortBy)
                .addKeyValue("direction", direction)
                .log("stocks listed");
    }

    @Override
    public void stockBatchesListed(String stockCode, int page, int size, String sortBy, String direction) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCKS_BATCHES_LISTED)
                .addKeyValue("stockCode",stockCode)
                .addKeyValue("page", page)
                .addKeyValue("size", size)
                .addKeyValue("sortBy", sortBy)
                .addKeyValue("direction", direction)
                .log("stock batches listed");
    }

    @Override
    public void stockExistenceChecked(String stockCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.STOCK)
                .addKeyValue("event", BusinessEvent.STOCK_EXISTENCE_CHECKED)
                .addKeyValue("stockCode", stockCode)
                .log("stock existence checked");
    }

    // --------------------- End Stock events --------------------- //


    // --------------------- Client events --------------------- //

    @Override
    public void clientCreated(String clientCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CLIENT)
                .addKeyValue("event", BusinessEvent.CLIENT_CREATED)
                .addKeyValue("clientCode", clientCode)
                .log("client created");
    }

    @Override
    public void clientSecretRotated(String clientCode) {


        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.CLIENT)
                .addKeyValue("event", BusinessEvent.CLIENT_SECRET_ROTATED)
                .addKeyValue("clientCode", clientCode)
                .log("client secret rotated");
    }

    // --------------------- End Client events --------------------- //


    // --------------------- Reservation events --------------------- //
    @Override
    public void reservationCreated(String reservationCode, String organizationCode) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.RESERVATION)
                .addKeyValue("event", BusinessEvent.RESERVATION_CREATED)
                .addKeyValue("reservationCode", reservationCode)
                .addKeyValue("organizationCode", organizationCode)
                .log("reservation created");
    }

    @Override
    public void reservationReleased(String reservationCode, String organizationCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.RESERVATION)
                .addKeyValue("event", BusinessEvent.RESERVATION_RELEASED)
                .addKeyValue("reservationCode", reservationCode)
                .addKeyValue("organizationCode", organizationCode)
                .log("reservation released");
    }

    @Override
    public void reservationConfirmed(String reservationCode, String organizationCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.RESERVATION)
                .addKeyValue("event", BusinessEvent.RESERVATION_CONFIRMED)
                .addKeyValue("reservationCode", reservationCode)
                .addKeyValue("organizationCode", organizationCode)
                .log("reservation confirmed");
    }

    @Override
    public void reservationExpired(String reservationCode, String organizationCode) {
        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.BUSINESS)
                .addKeyValue("domain", SystemDomain.RESERVATION)
                .addKeyValue("event", BusinessEvent.RESERVATION_EXPIRED)
                .addKeyValue("reservationCode", reservationCode)
                .addKeyValue("organizationCode", organizationCode)
                .log("reservation expired");
    }

    // ------------------- End Reservation events -------------------- //

}