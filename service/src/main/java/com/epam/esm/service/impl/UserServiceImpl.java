package com.epam.esm.service.impl;

import com.epam.esm.Role;
import com.epam.esm.Status;
import com.epam.esm.criteria_info.UserCriteriaInfo;
import com.epam.esm.dto.RegistrationUserDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.persistence.UserDao;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exception.ExceptionCode;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.exception.ResourceAlreadyExistsException;
import com.epam.esm.service.modelmapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserDao userDAO;
    private final GenericMapper<UserDto, User> mapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return mapper.toDTO(userDAO.findById(id).orElseThrow(()
                -> new NoSuchResourceException(
                ExceptionCode.NO_SUCH_USER_FOUND.getErrorCode(), "id= " + id)));
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDto update(UserDto user) {
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UserDto> find(Pageable pageable, UserCriteriaInfo criteriaInfo) {
        Page<User> all = userDAO.findAll(criteriaInfo, pageable);
        return  all.map(mapper::toDTO);
    }
    @Transactional
    @Override
    public UserDto create(UserDto userDTO) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public UserDto create(RegistrationUserDto registrationUserDto) {
        Optional<User> user = userDAO.findByEmail(registrationUserDto.getEmail());
        if (user.isPresent()) {
            throw new ResourceAlreadyExistsException(ExceptionCode.USER_ALREADY_EXIST.getErrorCode(),
                    registrationUserDto.getEmail());
        }
        User registerUser = new User();
        registerUser.setName(registrationUserDto.getName());
        registerUser.setSurname(registrationUserDto.getSurname());
        registerUser.setPassword(registrationUserDto.getPassword());
        registerUser.setRole(Role.ROLE_USER);
        registerUser.setStatus(Status.ACTIVE);
        registerUser.setCreateDate(Instant.now());
        registerUser.setLastUpdateDate(Instant.now());
        registerUser.setEmail(registrationUserDto.getEmail());

        return mapper.toDTO(userDAO.save(registerUser));
    }
}
