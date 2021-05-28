package com.epam.esm.dto;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.OrderConst;
import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Objects;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CartItem {
    @Positive
    @Min(Regex.MIN_ID)
    private long idCertificate;
    @Min(value = OrderConst.MIN_COUNT, message = Message.ENTER_COUNT)
    @Max(value = OrderConst.MAX_COUNT_ITEM, message = Message.ORDER_ITEM_MAX_COUNT)
    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return idCertificate == cartItem.idCertificate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCertificate, count);
    }
}
