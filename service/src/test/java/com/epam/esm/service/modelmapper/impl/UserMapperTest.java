package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void shouldToDto() {
        User user = new User();

        UserDto userDto = new UserDto();

         //when
        when(userMapper.getMapper().map(any(), any())).thenReturn(userDto);

        userMapper.toDTO(user);
        verify(userMapper.getMapper()).map(user, UserDto.class);
    }
}
