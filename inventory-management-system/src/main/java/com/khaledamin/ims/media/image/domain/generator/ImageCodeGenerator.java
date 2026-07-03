package com.khaledamin.ims.media.image.domain.generator;


import com.khaledamin.ims.core.generator.UlidGenerator;

public class ImageCodeGenerator {

    public static String generate() {

        return  "IMG" + "-" + UlidGenerator.generate();
    }
}
