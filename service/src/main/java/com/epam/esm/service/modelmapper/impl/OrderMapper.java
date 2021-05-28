package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.OrderItemDto;
import com.epam.esm.entity.Order;
import com.epam.esm.service.modelmapper.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper extends AbstractModelMapper<OrderDto, Order> {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper, ModelMapper mapper) {
        super(Order.class, OrderDto.class, mapper);
        this.orderItemMapper = orderItemMapper;
    }

    public OrderDto toDTO(Order entity) {
        OrderDto orderDto = Objects.isNull(entity) ? null : super.getMapper().map(entity, OrderDto.class);
        if (orderDto != null) {
            Set<OrderItemDto> orderItemDtos = entity.getOrderItems()
                    .stream()
                    .map(orderItemMapper::toDTO)
                    .collect(Collectors.toSet());

            orderDto.setOrderItemDto(orderItemDtos);
        }

        return orderDto;
    }
}
