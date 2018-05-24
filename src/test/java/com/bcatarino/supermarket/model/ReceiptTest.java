package com.bcatarino.supermarket.model;

import com.bcatarino.supermarket.DiscountType;
import org.junit.Test;
import testobjects.OrderItems;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReceiptTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfListOfOrderItemsIsNull() {
        new Receipt(null, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfListOfOrderItemsIsEmpty() {
        new Receipt(Collections.emptyList(), BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfTotalPriceIsNull() {
        new Receipt(Collections.singletonList(OrderItems.oneKgOfOranges()), null);
    }

    @Test
    public void shouldCreateReceiptIfAllDataIsValid() {
        List<OrderItem> orderItems = Collections.singletonList(OrderItems.oneKgOfOranges());
        Receipt receipt = new Receipt(orderItems, BigDecimal.ONE);
        assertEquals(orderItems, receipt.getItems());
        assertEquals(BigDecimal.ONE, receipt.getSubtotal());
        assertEquals(Collections.emptyList(), receipt.getSavings());
    }

    @Test
    public void shouldCreateReceiptIfAllDataIsValidWithSavings() {
        List<Saving> expectedSavings = Collections.singletonList(new Saving("beans", DiscountType.THREE_FOR_TWO, BigDecimal.ONE));
        List<OrderItem> orderItems = Collections.singletonList(OrderItems.oneKgOfOranges());
        Receipt receipt = new Receipt(orderItems, BigDecimal.ONE, expectedSavings);
        assertEquals(orderItems, receipt.getItems());
        assertEquals(BigDecimal.ONE, receipt.getSubtotal());
        assertEquals(expectedSavings, receipt.getSavings());
    }
}
