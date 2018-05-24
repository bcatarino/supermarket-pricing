package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Product;
import com.bcatarino.supermarket.model.Receipt;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import static testobjects.OrderItems.*;
import static testobjects.Products.*;

public class ShoppingCartTest {

    private ShoppingCart cart = new ShoppingCart();

    @Test
    public void emptyOrderShouldReturnZeroTotal() {
        Receipt receipt = cart.checkout(new Order(new ArrayList<>()));
        assertEquals(new Receipt(new ArrayList<>(), BigDecimal.ZERO), receipt);
    }

    @Test
    public void orderWithOneItemPerQuantityShouldReturnUnitPrice() {

        List<OrderItem> orderItems = Collections.singletonList(oneCanOfBeans());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(beans().getPricePerUnit(), receipt.getTotal());
    }

    @Test
    public void orderWithTwoDifferentItemsPerQuantityShouldSumValues() {

        List<OrderItem> orderItems = Arrays.asList(oneCanOfBeans(), oneCanOfCoke());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(beans().getPricePerUnit().add(coke().getPricePerUnit()), receipt.getTotal());
    }

    @Test
    public void orderWithThreeOfSameItemsShouldMultiplyValues() {

        Product beansRef = beans();
        BigDecimal expectedQuantity = BigDecimal.valueOf(3d);
        List<OrderItem> orderItems = Collections.singletonList(new OrderItem(beansRef, expectedQuantity));
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(beansRef.getPricePerUnit().multiply(expectedQuantity), receipt.getTotal());
    }

}
