package com.khaledamin.ims.identity.account.domain.command;


import com.khaledamin.ims.identity.account.domain.value.FirstName;
import com.khaledamin.ims.identity.account.domain.value.LastName;

public record ProfileCreateCommand(
        FirstName firstName,
        LastName lastName

) {
    public static ProfileCreateCommand of(String firstName , String lastName){
        return new ProfileCreateCommand(
                 FirstName.of(firstName),
                 LastName.of(lastName)
        );
    }
}
