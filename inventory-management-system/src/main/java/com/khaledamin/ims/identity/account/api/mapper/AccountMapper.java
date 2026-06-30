package com.khaledamin.ims.identity.account.api.mapper;


import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.identity.account.api.dto.AccountResponse;
import com.khaledamin.ims.identity.account.domain.model.Account;
import org.mapstruct.Mapper;


@Mapper(
        config = GlobalMapperConfig.class,
        uses = {
                ProfileMapper.class
        }
)
public interface AccountMapper extends BaseMapper<AccountResponse, Account> {

}
