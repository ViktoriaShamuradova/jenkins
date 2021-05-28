package com.epam.esm.entity;

import com.epam.esm.dto.CartItem;
import com.epam.esm.listener.GeneralEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "order_items", schema="mjs")
@Data
@NoArgsConstructor
@EntityListeners(GeneralEntityListener.class)
@EqualsAndHashCode(callSuper = true, exclude = {"order", "certificate"})
public class OrderItem extends com.epam.esm.entity.Entity<Long> {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_certificate")
    private Certificate certificate;
    @Column(name = "count")
    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;
    @Column(name = "price_certificate")
    private BigDecimal priceOfCertificate;

    public OrderItem(Certificate certificate, int count, Order order, BigDecimal priceOfCertificate) {
        super();
        this.certificate = certificate;
        this.count = count;
        this.order = order;
        this.priceOfCertificate = priceOfCertificate;
    }

    public OrderItem(CartItem cartItem) {
        super();
        this.certificate = new Certificate();
        certificate.setId(cartItem.getIdCertificate());
        count = cartItem.getCount();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "certificate=" + certificate +
                ", count=" + count +
                ", orderId=" + order.getId() +
                ", priceOfCertificate=" + priceOfCertificate +
                '}';
    }
}
