package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.UserDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "updateDateTime", expression = "java(userModel.getUpdateDateTime() == null ? userModel.getCreationDateTime() : userModel.getUpdateDateTime())")
    UserDTO userModelToDTO(UserModel userModel);

}
