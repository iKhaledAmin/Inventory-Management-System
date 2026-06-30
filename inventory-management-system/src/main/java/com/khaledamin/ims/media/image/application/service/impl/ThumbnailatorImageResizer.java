package com.khaledamin.ims.media.image.application.service.impl;


import com.khaledamin.ims.media.image.application.model.GeneratedImageVariant;
import com.khaledamin.ims.media.image.application.model.ImageConstants;
import com.khaledamin.ims.media.image.application.service.ImageResizer;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ThumbnailatorImageResizer implements ImageResizer {

    @Override
    public GeneratedImageVariant resize(BufferedImage source, ImageResolution resolution) {

        BufferedImage resized =
                switch (resolution.getResizeMode()) {

                    case NONE -> source;

                    case FIT -> resizeFit(source, resolution);

                    case SQUARE_CROP -> resizeSquare(source, resolution);
                };

        return buildVariant(resized);
    }






    private BufferedImage resizeFit(BufferedImage source, ImageResolution resolution) {

        try {

            return Thumbnails.of(source)
                    .size(
                            resolution.getWidth(),
                            resolution.getHeight()
                    )
                    .keepAspectRatio(true)
                    .asBufferedImage();

        } catch (IOException exception) {

            throw new RuntimeException(exception);
        }
    }

    private BufferedImage resizeSquare(BufferedImage source, ImageResolution resolution) {

        try {

            return Thumbnails.of(source)
                    .size(
                            resolution.getWidth(),
                            resolution.getHeight()
                    )
                    .crop(Positions.CENTER)
                    .asBufferedImage();

        } catch (IOException exception) {

            throw new RuntimeException(exception);
        }
    }

    private GeneratedImageVariant buildVariant(
            BufferedImage image
    ) {

        try {

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            ImageIO.write(
                    image,
                    ImageConstants.OUTPUT_FORMAT,
                    output
            );

            byte[] bytes = output.toByteArray();

            return new GeneratedImageVariant(
                    new ByteArrayInputStream(bytes),
                    image.getWidth(),
                    image.getHeight(),
                    bytes.length,
                    ImageConstants.OUTPUT_MIME_TYPE
            );

        } catch (IOException exception) {

            throw new RuntimeException(exception);
        }
    }
}