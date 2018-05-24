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
    public void shouldReturnRegularPriceIfOnlyTwoInThreeForTwo() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.valueOf(2), BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(20), finalPrice);
    }

    @Test
    public void shouldReturnPriceOfTwoForThreeItems() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.valueOf(3), BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(20), finalPrice);
    }

    @Test
    public void shouldReturnPriceOfThreeForFourItems() {
        BigDecimal finalPrice = DiscountType.THREE_FOR_TWO.calculateDiscount(BigDecimal.valueOf(4), BigDecimal.TEN);
        assertEquals(bigDecimalScaleTwo(30), finalPrice);
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
    public void shouldReturnRegularPriceIfOnlyOneUnit() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(1), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(0.6), finalPrice);
    }

    @Test
    public void shouldReturnOneIfTwoItems() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(2), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(1), finalPrice);
    }

    @Test
    public void shouldReturnOnePlusPriceOfOneIfThreeItems() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(3), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(1.6), finalPrice);
    }

    @Test
    public void shouldReturnPriceForSevenItems() {
        BigDecimal finalPrice = DiscountType.TWO_FOR_1_POUND.calculateDiscount(BigDecimal.valueOf(7), BigDecimal.valueOf(0.6));
        assertEquals(bigDecimalScaleTwo(3.6), finalPrice);
    }
}
