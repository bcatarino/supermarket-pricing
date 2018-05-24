package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Receipt;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    private ShoppingCart cart = new ShoppingCart();

    @Test
    public void emptyOrderShouldReturnReceiptWithZero() {
        Receipt receipt = cart.checkout(new Order(new ArrayList<OrderItem>()));
        assertEquals(new Receipt(new ArrayList<OrderItem>(), BigDecimal.ZERO), receipt);
    }

}
