package com.bcatarino.supermarket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = "product")
public class OrderItem {

    private Product product;

    /**
     * Can be integer for unit quantity or decimal for weight.
     */
    private BigDecimal quantity;

    public OrderItem(Product product, BigDecimal quantity) {

        if (product == null) {
            throw new IllegalArgumentException("Product is mandatory");
        }

        if (!product.getUnit().isValidAmount(quantity)) {
            throw new IllegalArgumentException("Invalid quantity for the product");
        }

        this.product = product;
        this.quantity = quantity;
    }
}
