package com.khaledamin.ims.identity.account.api.mapper;



import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.identity.account.api.dto.ProfileResponse;
import com.khaledamin.ims.identity.account.domain.model.Profile;
import com.khaledamin.ims.media.image.api.mapper.ImageMapper;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = ImageMapper.class
)
public interface ProfileMapper extends BaseMapper<ProfileResponse, Profile> {

}
