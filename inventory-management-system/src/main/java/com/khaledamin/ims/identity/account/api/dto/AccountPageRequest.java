package com.khaledamin.ims.identity.account.api.dto;

import com.khaledamin.ims.core.api.pagination.PageRequest;
import com.khaledamin.ims.identity.account.domain.model.AccountSortField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountPageRequest extends PageRequest {
    private String sortBy = AccountSortField.getDefault();

    @Override
    public String getSortBy() {
        return sortBy;
    }
}