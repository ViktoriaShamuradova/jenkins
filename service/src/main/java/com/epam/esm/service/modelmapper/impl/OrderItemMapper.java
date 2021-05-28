package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.OrderItemDto;
import com.epam.esm.entity.OrderItem;
import com.epam.esm.service.modelmapper.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderItemMapper extends AbstractModelMapper<OrderItemDto, OrderItem> {

    public OrderItemMapper(ModelMapper modelMapper) {
        super(OrderItem.class, OrderItemDto.class, modelMapper);
    }

    @Override
    public OrderItemDto toDTO(OrderItem entity) {
        OrderItemDto orderItemDto = Objects.isNull(entity) ? null : super.getMapper().map(entity, OrderItemDto.class);
        if(orderItemDto!=null){
            orderItemDto.setIdCertificate(entity.getCertificate().getId());
        }
        return orderItemDto;
    }
}
