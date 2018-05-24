package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Receipt;
import com.bcatarino.supermarket.model.Saving;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {

    private SavingsCalculator savingsCalculator;

    public ShoppingCart(SavingsCalculator savingsCalculator) {
        this.savingsCalculator = savingsCalculator;
    }

    public Receipt checkout(Order order) {

        BigDecimal subtotal = order.getItems().stream().map(this::getTotalForOrderItem)
                                                       .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Saving> savings = savingsCalculator.calculateSavings(order.getItems());

        return new Receipt(order.getItems(), subtotal, savings);
    }

    private BigDecimal getTotalForOrderItem(OrderItem item) {
        return item.getProduct().getPricePerUnit().multiply(item.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}