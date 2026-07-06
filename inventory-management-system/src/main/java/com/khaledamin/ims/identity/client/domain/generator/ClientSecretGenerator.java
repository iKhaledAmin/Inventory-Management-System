package com.khaledamin.ims.identity.client.domain.generator;

import java.security.SecureRandom;

public final class ClientSecretGenerator {

    private static final SecureRandom RANDOM =
            new SecureRandom();

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789" +
                    "!@#$%^&*()-_=+";

    private static final int SECRET_LENGTH = 40;

    private ClientSecretGenerator() {
    }

    public static String generate() {

        StringBuilder secret =
                new StringBuilder(SECRET_LENGTH);

        for (int i = 0; i < SECRET_LENGTH; i++) {

            int index =
                    RANDOM.nextInt(CHARACTERS.length());

            secret.append(
                    CHARACTERS.charAt(index)
            );
        }

        return secret.toString();
    }
}