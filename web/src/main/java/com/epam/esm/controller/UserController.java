package com.epam.esm.controller;

import com.epam.esm.hateoas.UserDtoModelAssembler;
import com.epam.esm.criteria_info.UserCriteriaInfo;
import com.epam.esm.dto.UserDto;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * a class which performs GET operations on a resource called "user"
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final UserDtoModelAssembler userModelAssembler;
    private final PagedResourcesAssembler<UserDto> pagedResourcesAssembler;

    public static final String AUTHORITY_READ = "hasAuthority('user:read')";

    /**
     * a method which realizes REST's READ operation of all resources
     *
     * @param pageable         - object witch contains information about pagination
     * @param userCriteriaInfo - object with information about user to search
     * @return a responseEntity with status code and collection of User, which represents a resource "user" from database
     * with links
     */
    @GetMapping
    @PreAuthorize(AUTHORITY_READ)
    public ResponseEntity<CollectionModel<UserDto>> find(Pageable pageable, @Valid UserCriteriaInfo userCriteriaInfo) {
        Page<UserDto> users = userService.find(pageable, userCriteriaInfo);
        PagedModel<UserDto> pagedModel = pagedResourcesAssembler.toModel(users, userModelAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    /**
     * a method which realizes REST's READ operation of a specific resource with name stored in a request path
     *
     * @param id an identification requested resource
     * @return a responseEntity with status code and object user, which represents a resource "user" from database
     * with links
     */
    @GetMapping("/{id}")
    @PreAuthorize(AUTHORITY_READ)
    public ResponseEntity<UserDto> find(@PathVariable @Min(1) long id) {
        UserDto userDTO = userService.findById(id);
        userModelAssembler.toModel(userDTO);
        return ResponseEntity.ok(userDTO);
    }
}

