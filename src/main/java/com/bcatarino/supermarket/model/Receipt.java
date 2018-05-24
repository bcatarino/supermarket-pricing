package com.bcatarino.supermarket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@EqualsAndHashCode
public class Receipt {

    private List<OrderItem> items;

    private BigDecimal total;

    public Receipt(List<OrderItem> items, BigDecimal total) {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("The list of order items cannot be null");
        }

        if (total == null) {
            throw new IllegalArgumentException("The total cannot be null");
        }

        this.items = items;
        this.total = total;
    }
}
