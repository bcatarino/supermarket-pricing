package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Receipt;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ShoppingCart {

    public Receipt checkout(Order order) {

        BigDecimal total = order.getItems().stream().map(this::getTotalForOrderItem).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Receipt(order.getItems(), total);
    }

    private BigDecimal getTotalForOrderItem(OrderItem item) {
        return item.getProduct().getPricePerUnit().multiply(item.getQuantity());
    }
}
