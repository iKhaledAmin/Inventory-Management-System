package com.khaledamin.ims.media.image.api.mapper;


import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.media.image.api.dto.ImageResponse;
import com.khaledamin.ims.media.image.application.service.ImageService;
import com.khaledamin.ims.media.image.domain.model.Image;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = ImageVariantMapper.class
)
public abstract class ImageMapper implements BaseMapper<ImageResponse, Image> {

    @Autowired
    protected ImageService imageService;


    @Named("largeUrl")
    public String largeUrl(Image image){
        return imageService.getUrl(image, ImageResolution.LARGE);
    }

    @Named("mediumUrl")
    public String mediumUrl(Image image){
        return imageService.getUrl(image, ImageResolution.MEDIUM);
    }

    @Named("smallUrl")
    public String smallUrl(Image image){
        return imageService.getUrl(image, ImageResolution.SMALL);
    }

    @Named("thumbnailUrl")
    public String thumbnailUrl(Image image){
        return imageService.getUrl(image, ImageResolution.SQUARE_THUMBNAIL);
    }


    public String toUrl(Image image, ImageResolution resolution) {

        if (image == null) {
            return null;
        }

        return imageService.getUrl(image, resolution);
    }

}