package com.khaledamin.ims.core.logging.core;

import com.khaledamin.ims.core.generator.UniqueIdentifierGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RequestIdGenerator {

    private final UniqueIdentifierGenerator generator;

    String generate() {
       return generator.generate();
    }
}

