package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.Receipt;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingCartTest {

    private ShoppingCart cart = new ShoppingCart();

    @Test
    public void emptyOrderShouldReturnEmptyReceipt() {
        Receipt receipt = cart.checkout(new Order());
        assertEquals(new Receipt(), receipt);
    }
}
