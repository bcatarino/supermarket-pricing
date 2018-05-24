package com.bcatarino.supermarket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class Receipt {

    private List<OrderItem> items;

    @Getter
    private BigDecimal subtotal;

    private List<Saving> savings;

    @Getter
    private BigDecimal totalSaving;

    public Receipt(List<OrderItem> items, BigDecimal subtotal) {
        this(items, subtotal, Collections.emptyList());
    }

    public Receipt(List<OrderItem> items, BigDecimal subtotal, List<Saving> savings) {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("The list of order items cannot be null");
        }

        if (subtotal == null) {
            throw new IllegalArgumentException("The subtotal cannot be null");
        }

        this.items = items;
        this.subtotal = subtotal;
        this.savings = savings != null ? savings : Collections.emptyList();

        this.totalSaving = this.savings.stream().map(Saving::getSavingValue).reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<Saving> getSavings() {
        return Collections.unmodifiableList(savings);
    }

    public BigDecimal getTotal() {
        return subtotal.subtract(totalSaving).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
