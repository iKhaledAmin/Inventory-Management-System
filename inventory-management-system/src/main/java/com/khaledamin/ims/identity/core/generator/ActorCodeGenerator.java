package com.khaledamin.ims.identity.core.generator;


import com.khaledamin.ims.core.generator.UniqueIdentifierGenerator;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActorCodeGenerator {

    private final UniqueIdentifierGenerator generator;


    public ActorCode generate(ActorType actorType) {

        // Technical actors use stable predefined code
        if (actorType.isTechnicalActor()) {
            return ActorCode.of(
                    actorType.getCodePrefix()
            );
        }


        // Domain actors get generated unique identity
        return ActorCode.of(
                actorType.getCodePrefix() + "-" + generator.generate()
        );
    }
}