package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.Receipt;

import java.math.BigDecimal;

public class ShoppingCart {

    public Receipt checkout(Order order) {
        return new Receipt(order.getItems(), BigDecimal.ZERO);
    }
}
