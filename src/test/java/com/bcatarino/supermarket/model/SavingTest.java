package com.bcatarino.supermarket.model;

import com.bcatarino.supermarket.DiscountType;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SavingTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfNameIsNull() {
        new Saving(null, DiscountType.THREE_FOR_TWO, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfNameIsEmpty() {
        new Saving("", DiscountType.THREE_FOR_TWO, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDiscountTypeIsNull() {
        new Saving("beans", null, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDiscountValueIsNull() {
        new Saving("beans", DiscountType.THREE_FOR_TWO, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDiscountValueIsZero() {
        new Saving("beans", DiscountType.THREE_FOR_TWO, BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDiscountValueIsNegative() {
        new Saving("beans", DiscountType.THREE_FOR_TWO, BigDecimal.valueOf(-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDiscountValueIsUnderMinimum() {
        new Saving("oranges", DiscountType.TWO_FOR_1_POUND, BigDecimal.valueOf(0.009));
    }

    @Test
    public void shouldCreateDiscountIfAllDataIsValid() {
        Saving saving = new Saving("oranges", DiscountType.TWO_FOR_1_POUND, BigDecimal.valueOf(0.01));
        assertEquals("oranges", saving.getProductName());
        assertEquals(DiscountType.TWO_FOR_1_POUND, saving.getDiscountType());
        assertEquals(BigDecimal.valueOf(0.01), saving.getSavingValue());
    }
}
