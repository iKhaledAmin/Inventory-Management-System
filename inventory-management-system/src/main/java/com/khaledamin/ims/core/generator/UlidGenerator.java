package com.khaledamin.ims.core.generator;

import com.github.f4b6a3.ulid.UlidCreator;
import org.springframework.stereotype.Component;

@Component
public class UlidGenerator implements UniqueIdentifierGenerator {

    @Override
    public String generate() {
        return UlidCreator.getUlid().toString();
    }
}