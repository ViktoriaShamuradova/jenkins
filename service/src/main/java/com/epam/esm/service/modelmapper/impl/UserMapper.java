package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.service.modelmapper.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper extends AbstractModelMapper<UserDto, User> {

    public UserMapper(ModelMapper modelMapper) {
        super(User.class, UserDto.class, modelMapper);
    }

    public UserDto toDTO(User entity) {
        UserDto userDTO =  Objects.isNull(entity) ? null : super.getMapper().map(entity, UserDto.class);
        if(userDTO != null) {
            userDTO.setUsername(entity.getEmail());
            userDTO.setPassword(null);
        }
        return userDTO;
    }
}
