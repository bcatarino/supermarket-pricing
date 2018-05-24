package com.bcatarino.supermarket.model;

import org.junit.Test;
import org.mockito.Mock;
import testobjects.Products;

import java.math.BigDecimal;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfProductIsNull() {
        new OrderItem(null, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfQuantityIsNull() {
        new OrderItem(Products.beans(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfQuantityIsInvalid() {
        Unit mockUnit = mock(Unit.class);
        when(mockUnit.isValidAmount(BigDecimal.ONE)).thenReturn(false);
        new OrderItem(new Product("beans", BigDecimal.TEN, mockUnit), BigDecimal.ONE);
    }

    @Test
    public void shouldCreateOrderItemIfAllDataIsValid() {

        Unit mockUnit = mock(Unit.class);
        when(mockUnit.isValidAmount(BigDecimal.ONE)).thenReturn(true);

        Product beans = Products.beans();

        OrderItem orderItem = new OrderItem(beans, BigDecimal.ONE);
        assertEquals(beans, orderItem.getProduct());
        assertEquals(BigDecimal.ONE, orderItem.getQuantity());
    }
}
