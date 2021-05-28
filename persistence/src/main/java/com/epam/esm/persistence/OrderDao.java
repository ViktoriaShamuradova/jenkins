package com.epam.esm.persistence;

import com.epam.esm.criteria_info.OrderCriteriaInfo;
import com.epam.esm.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderDao {
    Optional<Order> findById(Long id);

    Order save(Order order);

    Page<Order> findAll(OrderCriteriaInfo criteriaInfo, Pageable pageable);
}
