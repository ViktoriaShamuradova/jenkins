package com.epam.esm.dto;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.OrderConst;
import com.epam.esm.constant.Regex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItemDto  extends EntityDto<Long, OrderItemDto>{

    @Positive
    @Min(Regex.MIN_ID)
    private long idCertificate;
    @Min(OrderConst.MIN_COUNT)
    @Max(OrderConst.MAX_COUNT_ITEM)
    private int count;
    @Digits(integer = OrderConst.PRICE_INTEGER, fraction = OrderConst.PRICE_FRACTION)
    @DecimalMin(value = "0", message = Message.ENTER_PRICE)
    private BigDecimal priceOfCertificate;

    public OrderItemDto(long idCertificate, int count, BigDecimal priceOfCertificate) {
        this.idCertificate = idCertificate;
        this.count = count;
        this.priceOfCertificate = priceOfCertificate;
    }
}
