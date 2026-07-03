package com.khaledamin.ims.identity.account.domain.model;


import com.khaledamin.ims.identity.account.domain.command.AccountCreateCommand;
import com.khaledamin.ims.identity.account.domain.command.ProfileCreateCommand;
import com.khaledamin.ims.identity.core.generator.ActorCodeGenerator;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.identity.role.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AccountFactory {



    public Account create(
            String username,
            String encodedPassword,
            String email,
            String firstName,
            String lastName,
            List<Role> roles
    ){

        ActorCode accountCode = ActorCodeGenerator.generate(ActorType.ACCOUNT);

        AccountCreateCommand command = AccountCreateCommand.of(accountCode, username, encodedPassword, email);

        Profile profile = createProfile(firstName, lastName);

        return Account.create(command, profile, roles);
    }



    private Profile createProfile(String firstName,String lastName) {

        ProfileCreateCommand command = ProfileCreateCommand.of(firstName, lastName);

        return Profile.create(command);
    }

}


