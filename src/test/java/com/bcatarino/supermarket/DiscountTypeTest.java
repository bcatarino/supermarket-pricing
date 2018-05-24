package com.bcatarino.supermarket;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

import static testutils.MathUtils.bigDecimalScaleTwo;

public class DiscountTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfThreeForTwoQuantityIsNull() {
        DiscountType.THREE_FOR_TWO.calculateDiscount(null, BigDecimal.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfThreeForTwoPricePerUnitIsNull() {
        DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.ONE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfThreeForTwoPricePerUnitIsZero() {
        DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.ONE, BigDecimal.ZERO);
    }

    @Test
    public void shouldReturnZeroIfQuantityIsZero() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.ZERO, BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(0), finalPrice);
    }

    @Test
    public void shouldReturnNoDiscountIfOnlyTwoInThreeForTwo() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.valueOf(2), BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(0), finalPrice);
    }

    @Test
    public void shouldReturnDiscountIfThreeItems() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.valueOf(3), BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(10), finalPrice);
    }

    @Test
    public void shouldReturnDiscountForOneIfFourItems() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.valueOf(4), BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(10), finalPrice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfTwoForOnePoundQuantityIsNull() {
        DiscountType.TWO_FOR_1_POUND.calculateDiscount(null, BigDecimal.valueOf(0.6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfTwoForOnePoundPricePerUnitIsNull() {
        DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.ONE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfTwoForOnePoundPricePerUnitIsZero() {
        DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.ONE, BigDecimal.ZERO);
    }

    @Test
    public void shouldReturnZeroIfQuantityIsZeroForTwoForOnePound() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.ZERO, BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(0), finalPrice);
    }

    @Test
    public void shouldReturnNoDiscountIfOnlyOneUnit() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(1), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(0), finalPrice);
    }

    @Test
    public void shouldReturnDiscountForTwoItems() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(2), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(0.2), finalPrice);
    }

    @Test
    public void shouldReturnDiscountIfThreeItemsInTwoForOne() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(3), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(0.2), finalPrice);
    }

    @Test
    public void shouldReturnDiscountTimesThreeIfSevenItems() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(7), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(0.6), finalPrice);
    }
}
