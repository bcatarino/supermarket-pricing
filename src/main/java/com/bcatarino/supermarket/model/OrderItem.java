package com.bcatarino.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "product")
public class OrderItem {

    private Product product;

    /**
     * Can be integer for unit quantity or decimal for weight.
     */
    private BigDecimal quantity;
}
