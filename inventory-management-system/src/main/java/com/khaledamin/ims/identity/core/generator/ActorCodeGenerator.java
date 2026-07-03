package com.khaledamin.ims.identity.core.generator;


import com.khaledamin.ims.core.generator.UlidGenerator;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;


public class ActorCodeGenerator {



    public static ActorCode generate(ActorType actorType) {

        // Technical actors use stable predefined code
        if (actorType.isTechnicalActor()) {
            return ActorCode.of(
                    actorType.getCodePrefix()
            );
        }


        // Domain actors get generated unique identity
        return ActorCode.of(
                actorType.getCodePrefix() + "-" + UlidGenerator.generate()
        );
    }
}