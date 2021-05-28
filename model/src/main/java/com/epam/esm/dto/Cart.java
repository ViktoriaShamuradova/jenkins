package com.epam.esm.dto;

import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
public class Cart {

    @Valid
    private Set<CartItem> cartItems;
    @Positive
    @Min(Regex.MIN_ID)
    private long userId;

    public Cart() {
        cartItems = new HashSet<>();
    }
}
