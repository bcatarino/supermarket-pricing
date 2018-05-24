package com.bcatarino.supermarket.model;

import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class Order {
    private List<OrderItem> items;

    public Order(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("List of orderItems cannot be empty");
        }
        this.items = items;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
