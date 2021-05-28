package com.epam.esm.service;

import com.epam.esm.criteria_info.UserCriteriaInfo;
import com.epam.esm.dto.RegistrationUserDto;
import com.epam.esm.dto.UserDto;

public interface UserService extends CrudService<UserDto, Long, UserCriteriaInfo> {

    UserDto create(RegistrationUserDto registrationUserDto);
}
