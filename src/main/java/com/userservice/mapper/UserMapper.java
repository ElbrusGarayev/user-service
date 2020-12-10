package com.userservice.mapper;

import com.userservice.dto.LoginDTO;
import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO modelToDto(User user);

    @Mapping(target = "createdDate", ignore = true)
    User dtoToModel(UserDTO userDTO);

    @Mapping(target = "createdDate", ignore = true)
    User dtoToModel(LoginDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User dtoToModel(UserDTO userDTO, @MappingTarget User user);
}
