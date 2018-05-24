package com.bcatarino.supermarket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@EqualsAndHashCode
public class Receipt {

    private List<OrderItem> items;

    private BigDecimal subtotal;

    public Receipt(List<OrderItem> items, BigDecimal subtotal) {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("The list of order items cannot be null");
        }

        if (subtotal == null) {
            throw new IllegalArgumentException("The subtotal cannot be null");
        }

        this.items = items;
        this.subtotal = subtotal;
    }
}
