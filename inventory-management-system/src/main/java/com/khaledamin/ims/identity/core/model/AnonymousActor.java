package com.khaledamin.ims.identity.core.model;

public class AnonymousActor extends AbstractActor {

    private static final ActorIdentity IDENTITY =
            ActorIdentity.of(
                    ActorType.ANONYMOUS,
                    ActorCode.of(ActorType.ANONYMOUS.getCodePrefix())
            );
    public static final AnonymousActor INSTANCE = new AnonymousActor();



    private AnonymousActor() {
        super(IDENTITY);
    }

    @Override
    public boolean hasAuthority(String authority) {
        return false;
    }
}