package com.khaledamin.ims.organization.domain.factory;

import com.khaledamin.ims.core.generator.UniqueIdentifierGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
class OrganizationCodeGenerator {

    private final UniqueIdentifierGenerator generator;

    String generate() {

        return  "ORG" + "-" + generator.generate();
    }
}