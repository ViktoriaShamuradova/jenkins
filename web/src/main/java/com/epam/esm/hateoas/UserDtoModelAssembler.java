package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.UserDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDtoModelAssembler extends RepresentationModelAssemblerSupport<UserDto, UserDto> {

    public UserDtoModelAssembler() {
        super(UserController.class, UserDto.class);
    }

    @Override
    public UserDto toModel(UserDto dto) {
        appendSelfReference(dto);
        return dto;
    }

    private void appendSelfReference(UserDto dto) {
        dto.add(linkTo(methodOn(UserController.class)
                .find(dto.getId()))
                .withRel("find by id")
                .withType(HttpMethod.GET.name()));
    }
}
