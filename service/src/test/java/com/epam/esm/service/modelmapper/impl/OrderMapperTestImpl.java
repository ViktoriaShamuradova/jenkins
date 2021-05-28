package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTestImpl {
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private OrderMapper orderMapper;
    @Mock
    private OrderItemMapper orderItemMapper;

    @Test
    public void shouldToDto() {
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(new OrderItem());
        order.setOrderItems(orderItems);

        OrderDto orderDto = new OrderDto();

        when(orderMapper.getMapper().map(any(), any())).thenReturn(orderDto);
        orderMapper.toDTO(order);
        verify(orderMapper.getMapper()).map(order, OrderDto.class);
        verify(orderItemMapper).toDTO(any());
    }
}
