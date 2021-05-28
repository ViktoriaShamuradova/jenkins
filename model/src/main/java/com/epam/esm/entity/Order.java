package com.epam.esm.entity;

import com.epam.esm.listener.GeneralEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "orders", schema="mjs")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"orderItems", "user"})
@EntityListeners(GeneralEntityListener.class)
public class Order extends com.epam.esm.entity.Entity<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "total_sum")
    private BigDecimal totalSum;
    @Column(name = "count")
    private Integer count;
    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private Instant createDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order",
            orphanRemoval = true)
    private Set<OrderItem> orderItems;

    public Order(long userId) {
        super();
        this.user = new User();
        user.setId(userId);
        createDate = Instant.now();
    }

    public void add(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new HashSet<>();
        }
        boolean isExistOrderItem = false;
        for (OrderItem orderItem1 : orderItems) {
            if (orderItem1.getCertificate().getId().equals(orderItem.getCertificate().getId())) {
                orderItem1.setCount(orderItem1.getCount() + orderItem.getCount());
                isExistOrderItem = true;
            }
        }
        if (!isExistOrderItem) {
            orderItem.setOrder(this);
            orderItems.add(orderItem);
        }
        refreshDataSumAndCount();
    }

    private void refreshDataSumAndCount() {
        count = 0;
        totalSum = BigDecimal.ZERO;
        for (OrderItem item : getOrderItems()) {
            count = count + item.getCount();
            totalSum = totalSum.add(item.getPriceOfCertificate().multiply(BigDecimal.valueOf(item.getCount())));
        }
    }
}
