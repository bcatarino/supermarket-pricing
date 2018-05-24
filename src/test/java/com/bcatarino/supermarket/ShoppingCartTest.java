package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Receipt;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import static testobjects.OrderItems.*;
import static testobjects.Products.*;

public class ShoppingCartTest {

    private ShoppingCart cart = new ShoppingCart();

    @Test
    public void emptyOrderShouldReturnReceiptWithZero() {
        Receipt receipt = cart.checkout(new Order(new ArrayList<OrderItem>()));
        assertEquals(new Receipt(new ArrayList<OrderItem>(), BigDecimal.ZERO), receipt);
    }

    @Test
    public void orderWithItemPerQuantityShouldReturnThatAsTotal() {

        List<OrderItem> orderItems = Collections.singletonList(oneCanOfBeans());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(beans().getPricePerUnit(), receipt.getTotal());
    }

}
