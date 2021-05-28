package com.epam.esm.persistence.impl;

import com.epam.esm.criteria_info.OrderCriteriaInfo;
import com.epam.esm.entity.Order;
import com.epam.esm.persistence.OrderDao;
import com.epam.esm.persistence.dataspecification.OrderSpecification;
import com.epam.esm.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(OrderCriteriaInfo criteriaInfo, Pageable pageable) {
        OrderSpecification orderSpecification = new OrderSpecification(criteriaInfo);
        return orderRepository.findAll(orderSpecification, pageable);
    }
}
