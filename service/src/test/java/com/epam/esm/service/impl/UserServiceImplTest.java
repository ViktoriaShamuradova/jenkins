package com.epam.esm.service.impl;

import com.epam.esm.criteria_info.UserCriteriaInfo;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.persistence.UserDao;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.modelmapper.GenericMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Mock
    private GenericMapper<UserDto, User> mapper;

    @Test
    public void findById_shouldFind() {
        User user = new User();
        user.setId(1L);
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));

        userService.findById(user.getId());

        verify(mapper).toDTO(user);
        verify(userDao).findById(user.getId());
    }

    @Test
    public void findById_shouldThrownException() {
        when(userDao.findById(anyLong())).thenThrow(NoSuchResourceException.class);
        assertThatThrownBy(() -> userService.findById(anyLong())).isInstanceOf(NoSuchResourceException.class);
    }

    @Test
    public void delete_shouldThrownException() {
        assertThatThrownBy(() -> userService.delete(1L))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void update_shouldThrownException() {
        assertThatThrownBy(() -> userService.update(new UserDto()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void find_shouldFindListOfUsers() {
        //given
        List<User> usersList = new ArrayList<>();
        User u = new User();
        usersList.add(u);

        Page<User> users = new PageImpl<>(usersList);
        Pageable pageRequest = PageRequest.of(0, 1);

        UserCriteriaInfo criteriaInfo = new UserCriteriaInfo();


        when(userDao.findAll(any(), any()))
                .thenReturn(users);
        //when
        userService.find(pageRequest, criteriaInfo);
        //then
        verify(userDao)
                .findAll(criteriaInfo, pageRequest);
        verify(mapper).toDTO(u);
    }

    @Test
    public void create_shouldThrownException() {
        assertThatThrownBy(() -> userService.update(new UserDto()))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
