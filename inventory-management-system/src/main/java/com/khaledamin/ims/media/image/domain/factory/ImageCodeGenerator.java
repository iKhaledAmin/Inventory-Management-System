package com.khaledamin.ims.media.image.domain.factory;

import com.khaledamin.ims.core.generator.UniqueIdentifierGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ImageCodeGenerator {
    private final UniqueIdentifierGenerator generator;

    String generate() {

        return  "IMG" + "-" + generator.generate();
    }
}
