package com.epam.esm.criteria_info;

import com.epam.esm.constant.OrderConst;
import com.epam.esm.constant.Regex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrderCriteriaInfo extends CriteriaInfo {
    @Positive
    @Min(Regex.MIN_ID)
    private Long idUser;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer= OrderConst.MAX_COUNT_ITEM, fraction=OrderConst.PRICE_FRACTION)
    private BigDecimal totalSum;

}
