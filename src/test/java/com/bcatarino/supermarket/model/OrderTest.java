package com.bcatarino.supermarket.model;

import org.junit.Test;
import testobjects.OrderItems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfListOfOrderItemsIsNull() {
        new Order(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfListOfOrderItemsIsEmpty() {
        new Order(Collections.emptyList());
    }

    @Test
    public void shouldCreateOrderIfListContainsOneElement() {
        List<OrderItem> expected = Collections.singletonList(OrderItems.oneCanOfBeans());
        Order order = new Order(expected);
        assertEquals(expected, order.getItems());
    }

    @Test
    public void shouldCreateOrderIfListContainsMultipleElements() {
        List<OrderItem> expected = Arrays.asList(OrderItems.oneCanOfBeans(), OrderItems.oneKgOfOranges());
        Order order = new Order(expected);
        assertEquals(expected, order.getItems());
    }
}
