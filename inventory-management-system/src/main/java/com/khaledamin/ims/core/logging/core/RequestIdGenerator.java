package com.khaledamin.ims.core.logging.core;

import com.khaledamin.ims.core.generator.UlidGenerator;


public class RequestIdGenerator {

    public static String generate() {
       return UlidGenerator.generate();
    }
}

