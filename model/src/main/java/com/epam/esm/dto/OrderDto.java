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
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"orderItemDto"})
public class OrderDto extends EntityDto<Long, OrderDto>{

    private Set<OrderItemDto> orderItemDto;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer= OrderConst.PRICE_INTEGER, fraction=OrderConst.PRICE_FRACTION)
    private BigDecimal totalSum;
    @Min(OrderConst.MIN_COUNT)
    @Max(OrderConst.MAX_COUNT)
    private int count;
    @Positive
    @Min(Regex.MIN_ID)
    private long userId;
    @Null(message = Message.DATE_CANNOT_DEFINE)
    private Instant createDate;
}
