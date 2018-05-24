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
import static testobjects.OrderItems.threeCansOfBeans;
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

        OrderItem beansItem = threeCansOfBeans();
        List<OrderItem> orderItems = Collections.singletonList(beansItem);
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(BigDecimal.valueOf(1.5d).setScale(2, BigDecimal.ROUND_HALF_UP), receipt.getTotal());
    }

    @Test
    public void orderWithMultipleQuantitiesOfItemShouldReturnTotal() {

        List<OrderItem> orderItems = Arrays.asList(threeCansOfBeans(), threeCansOfCoke());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(BigDecimal.valueOf(3.6d).setScale(2, BigDecimal.ROUND_HALF_UP), receipt.getTotal());
    }

}
