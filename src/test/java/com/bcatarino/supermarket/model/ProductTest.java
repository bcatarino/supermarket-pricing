package com.bcatarino.supermarket.model;

import com.bcatarino.supermarket.DiscountType;
import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfNameIsNull() {
        new Product(null, BigDecimal.ONE, Unit.QUANTITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfNameIsEmpty() {
        new Product("", BigDecimal.ONE, Unit.QUANTITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPriceIsNull() {
        new Product("beans", null, Unit.QUANTITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPriceIsZero() {
        new Product("beans", BigDecimal.ZERO, Unit.QUANTITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLowerThanOnePence() {
        new Product("beans", BigDecimal.valueOf(0.009d), Unit.QUANTITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfNegative() {
        new Product("beans", BigDecimal.valueOf(-1d), Unit.QUANTITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfUnitIsNull() {
        new Product("beans", BigDecimal.ONE, null);
    }

    @Test
    public void shouldCreateValidProduct() {
        Product beans = new Product("beans", BigDecimal.ONE, Unit.QUANTITY);
        assertEquals("beans", beans.getName());
        assertEquals(BigDecimal.ONE, beans.getPricePerUnit());
        assertEquals(Unit.QUANTITY, beans.getUnit());
        assertEquals(Optional.empty(), beans.getDiscount());
    }

    @Test
    public void shouldCreateValidProductWithDiscount() {
        Product beans = new Product("beans", BigDecimal.ONE, Unit.QUANTITY, Optional.of(DiscountType.THREE_FOR_TWO));
        assertEquals("beans", beans.getName());
        assertEquals(BigDecimal.ONE, beans.getPricePerUnit());
        assertEquals(Unit.QUANTITY, beans.getUnit());
        assertEquals(Optional.of(DiscountType.THREE_FOR_TWO), beans.getDiscount());
    }
}
