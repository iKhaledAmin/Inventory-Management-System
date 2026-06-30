package com.khaledamin.ims.identity.core.model;


public class SystemActor extends AbstractActor {



    private static final ActorIdentity IDENTITY =
            ActorIdentity.of(
                    ActorType.SYSTEM,
                    ActorCode.of(ActorType.SYSTEM.getCodePrefix())
            );
    public static final SystemActor INSTANCE = new SystemActor();



    private SystemActor() {
        super(IDENTITY);
    }

    @Override
    public boolean hasAuthority(String authority) {
        return false;
    }
}