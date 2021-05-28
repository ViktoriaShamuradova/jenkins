package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.OrderItemDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderItemMapperTest {
    @InjectMocks
    private OrderItemMapper orderItemMapper;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void shouldToDto() {
        OrderItem orderItem = new OrderItem();
        Certificate certificate = new Certificate();
        certificate.setId(1L);
        orderItem.setCertificate(certificate);
        OrderItemDto orderItemDto = new OrderItemDto();

        //when
        when(orderItemMapper.getMapper().map(any(), any())).thenReturn(orderItemDto);

        orderItemMapper.toDTO(orderItem);
        verify(orderItemMapper.getMapper()).map(orderItem, OrderItemDto.class);
    }
}
