package com.userservice.mapper;

import com.userservice.dto.UserDTO;
import com.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO modelToDto(User user);

    @Mapping(target = "createdDate", ignore = true)
    User dtoToModel(UserDTO userDTO);
}
