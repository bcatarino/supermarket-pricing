package com.bcatarino.supermarket.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UnitTest {

    @Test
    public void shouldBeInvalidForNullQuantity() {
        assertFalse(Unit.QUANTITY.isValidAmount(null));
    }

    @Test
    public void shouldBeInvalidForQuantityZero() {
        assertFalse(Unit.QUANTITY.isValidAmount(BigDecimal.ZERO));
    }

    @Test
    public void shouldBeInvalidForNegativeQuantity() {
        assertFalse(Unit.QUANTITY.isValidAmount(BigDecimal.valueOf(-1)));
    }

    @Test
    public void shouldBeValidForQuantityOne() {
        assertTrue(Unit.QUANTITY.isValidAmount(BigDecimal.ONE));
    }

    @Test
    public void shouldBeInvalidForNullWeight() {
        assertFalse(Unit.WEIGHT.isValidAmount(null));
    }

    @Test
    public void shouldBeInvalidForWeightZero() {
        assertFalse(Unit.WEIGHT.isValidAmount(BigDecimal.ZERO));
    }

    @Test
    public void shouldBeInvalidForNegativeWeight() {
        assertFalse(Unit.WEIGHT.isValidAmount(BigDecimal.valueOf(-1)));
    }

    @Test
    public void shouldBeInvalidForWeightUnderMinimum() {
        assertFalse(Unit.WEIGHT.isValidAmount(BigDecimal.valueOf(0.009)));
    }

    @Test
    public void shouldBeValidForWeightOneGram() {
        assertTrue(Unit.WEIGHT.isValidAmount(BigDecimal.valueOf(0.01d)));
    }

    @Test
    public void shouldBeValidForWeightOne() {
        assertTrue(Unit.WEIGHT.isValidAmount(BigDecimal.ONE));
    }
}
