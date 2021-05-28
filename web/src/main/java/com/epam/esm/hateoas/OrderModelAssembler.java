package com.epam.esm.hateoas;

import com.epam.esm.controller.OrderController;
import com.epam.esm.dto.OrderDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<OrderDto, OrderDto> {

    public OrderModelAssembler() {
        super(OrderController.class, OrderDto.class);
    }

    @Override
    public OrderDto toModel(OrderDto dto) {
        appendSelfReference(dto);
        return dto;
    }

    private void appendSelfReference(OrderDto dto) {
        dto.add(linkTo(methodOn(OrderController.class)
                .find(dto.getId()))
                .withRel("find by id")
                .withType(HttpMethod.GET.name()));
    }
}
