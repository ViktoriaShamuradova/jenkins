package com.epam.esm.service.impl;

import com.epam.esm.criteria_info.OrderCriteriaInfo;
import com.epam.esm.dto.Cart;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.persistence.CertificateDao;
import com.epam.esm.persistence.OrderDao;
import com.epam.esm.persistence.UserDao;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.modelmapper.GenericMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderDao orderDao;
    @Mock
    private GenericMapper<OrderDto, Order> mapper;
    @Mock
    private UserDao userDao;
    @Mock
    private CertificateDao certificateDao;

    @Test
    public void findById_shouldFind() {
        Order o = new Order();
        OrderDto orderDto = new OrderDto();
        o.setId(1L);

        when(orderDao.findById(anyLong())).thenReturn(Optional.of(o));
        when(mapper.toDTO(o)).thenReturn(orderDto);

        orderService.findById(o.getId());

        verify(orderDao).findById(o.getId());
        verify(mapper).toDTO(o);

        assertThat(orderService.findById(o.getId()).equals(orderDto));
    }

    @Test
    public void findById_shouldThrowException() {
        when(orderDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(()
                -> orderService.findById(anyLong()))
                .isInstanceOf(NoSuchResourceException.class);
    }

    @Test
    public void create_shouldCreate_WithCart() {
        Cart cart = new Cart();
        cart.setUserId(1L);
        User user = new User();
        user.setId(cart.getUserId());
        Order o = new Order(cart.getUserId());
        OrderDto orderDto = new OrderDto();
        o.setId(1L);

        when(userDao.findById(cart.getUserId())).thenReturn(Optional.of(user));
        when(orderDao.save(any())).thenReturn(o);
        when(mapper.toDTO(any())).thenReturn(orderDto);

        orderService.create(cart);

        assertThat(orderService.create(cart).equals(orderDto));
    }
    @Test
    public void find_shouldReturnPageOfOrders() {
        //given
        List<Order> orders = new ArrayList<>();
        Order o = new Order();
        orders.add(o);

        OrderCriteriaInfo criteriaInfo = new OrderCriteriaInfo();
        Page<Order> page = new PageImpl<>(orders);
        Pageable pageRequest = PageRequest.of(0, 1);


        when(orderDao.findAll(any(), any()))
                .thenReturn(page);
        //when
        orderService.find(pageRequest, criteriaInfo);
        //then
        verify(orderDao)
                .findAll( criteriaInfo, pageRequest);
        verify(mapper).toDTO(o);
    }

    @Test
    public void create_shouldThrownException() {
        assertThatThrownBy(() -> orderService.update(new OrderDto()))
                .isInstanceOf(UnsupportedOperationException.class);
    }
    @Test
    public void delete_shouldThrownException() {
        assertThatThrownBy(() -> orderService.delete(1L))
                .isInstanceOf(UnsupportedOperationException.class);
    }
    @Test
    public void update_shouldThrownException() {
        assertThatThrownBy(() -> orderService.update(new OrderDto()))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
