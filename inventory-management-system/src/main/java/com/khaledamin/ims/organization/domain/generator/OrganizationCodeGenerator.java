package com.khaledamin.ims.organization.domain.generator;

import com.khaledamin.ims.core.generator.UlidGenerator;

public class OrganizationCodeGenerator {

    public static String generate() {
        return  "ORG" + "-" + UlidGenerator.generate();
    }
}
