package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Saving;
import org.junit.Test;
import testutils.MathUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static testobjects.OrderItems.*;
import static testutils.MathUtils.bigDecimalScaleTwo;

import static org.junit.Assert.assertEquals;

public class SavingsCalculatorTest {

    private SavingsCalculator calculator = new SavingsCalculator();

    @Test
    public void shouldReturnNoSavingsForEmptyOrders() {
        List<Saving> savings = calculator.calculateSavings(Collections.emptyList());
        assertEquals(Collections.emptyList(), savings);
    }

    @Test
    public void shouldReturnNoSavingsForOrdersWithNoDiscounts() {
        List<Saving> savings = calculator.calculateSavings(Collections.singletonList(oneCanOfBeans()));
        assertEquals(Collections.emptyList(), savings);
    }

    @Test
    public void shouldReturnSavingForBeansOrderWithDiscount() {
        Saving expected = new Saving("beans", DiscountType.THREE_FOR_TWO, bigDecimalScaleTwo(0.5));
        List<Saving> savings = calculator.calculateSavings(Collections.singletonList(threeCansOfBeansWithDiscounts()));

        assertEquals(1, savings.size());
        assertEquals(expected, savings.get(0));
    }

    @Test
    public void shouldReturnSavingForCokeOrderWithDiscount() {
        Saving expected = new Saving("coke", DiscountType.TWO_FOR_1_POUND, bigDecimalScaleTwo(0.4));
        List<Saving> savings = calculator.calculateSavings(Collections.singletonList(threeCansOfCokeWithDiscounts()));

        assertEquals(1, savings.size());
        assertEquals(expected, savings.get(0));
    }

    @Test
    public void shouldReturnSavingForMixedOrderWithDiscount() {
        Saving beansSaving = new Saving("beans", DiscountType.THREE_FOR_TWO, bigDecimalScaleTwo(1));
        Saving cokeSaving = new Saving("coke", DiscountType.TWO_FOR_1_POUND, bigDecimalScaleTwo(0.4));
        List<Saving> savings = calculator.calculateSavings(Arrays.asList(threeCansOfBeansWithDiscounts(),
                oneCanOfBeansWithDiscounts(), threeCansOfCokeWithDiscounts(), oneCanOfBeansWithDiscounts(),
                oneCanOfBeansWithDiscounts()));

        assertEquals(2, savings.size());
        assertEquals(beansSaving, savings.get(0));
        assertEquals(cokeSaving, savings.get(1));
    }
}
