package com.khaledamin.ims.identity.account.domain.model;


import com.khaledamin.ims.identity.account.exception.AccountValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountSortField {

    USERNAME("username"),
    LAST_LOGIN("lastLogin"),
    CREATED_AT("createdAt");

    private final String field

    ;



    public static String getDefault() {
        return USERNAME.getField();
    }

    public static String getFieldFrom(String queryParam) {
        try {
            return AccountSortField.valueOf(queryParam).getField();
        } catch (IllegalArgumentException e) {
            throw AccountValidationException.invalidSortField()
                    .withDebugDetails("sortField" , queryParam);
        }
    }
}
