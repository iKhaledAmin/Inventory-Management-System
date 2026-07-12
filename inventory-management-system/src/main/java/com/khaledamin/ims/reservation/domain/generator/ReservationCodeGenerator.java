package com.khaledamin.ims.reservation.domain.generator;

import com.khaledamin.ims.core.generator.UlidGenerator;

public class ReservationCodeGenerator {

    public static String generate() {
        return "RSV" + "-" + UlidGenerator.generate();
    }
}
