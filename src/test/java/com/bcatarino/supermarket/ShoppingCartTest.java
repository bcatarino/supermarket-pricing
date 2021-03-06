package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.Order;
import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Receipt;
import com.bcatarino.supermarket.model.Saving;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testobjects.OrderItems.*;
import static testobjects.Products.beans;
import static testobjects.Savings.beansSaving;
import static testobjects.Savings.cokeSaving;
import static testutils.MathUtils.bigDecimalScaleTwo;

public class ShoppingCartTest {

    private ShoppingCart cart = new ShoppingCart(new SavingsCalculator());

    @Test
    public void shouldReturnUnitPriceIfSingleItem() {

        List<OrderItem> orderItems = Collections.singletonList(oneCanOfBeans());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(beans().getPricePerUnit().setScale(2, BigDecimal.ROUND_HALF_UP), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnSumOfValuesIfTwoDifferentItems() {

        List<OrderItem> orderItems = Arrays.asList(oneCanOfBeans(), oneCanOfCoke());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(bigDecimalScaleTwo(1.2d), receipt.getSubtotal());
    }

    @Test
    public void shouldMultiplyPerQuantityIfThreeOfSameItem() {

        OrderItem beansItem = threeCansOfBeans();
        List<OrderItem> orderItems = Collections.singletonList(beansItem);
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(bigDecimalScaleTwo(1.5d), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnTotalIfMultipleQuantitiesOfItems() {

        List<OrderItem> orderItems = Arrays.asList(threeCansOfBeans(), threeCansOfCoke());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(bigDecimalScaleTwo(3.6d), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnTotalIfMultipleInstancesOfSameItem() {

        List<OrderItem> orderItems = Arrays.asList(oneCanOfBeans(), oneCanOfBeans(), oneCanOfBeans());
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(bigDecimalScaleTwo(1.5d), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnPricePerKgIfOneKg() {

        OrderItem orangesItem = oneKgOfOranges();
        List<OrderItem> orderItems = Collections.singletonList(orangesItem);
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(orangesItem.getProduct().getPricePerUnit(), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnOneFifthOfKgPriceIfTwoHundredGramsOfProduct() {

        OrderItem orangesItem = twoHundredGramsOfOranges();
        List<OrderItem> orderItems = Collections.singletonList(orangesItem);
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(bigDecimalScaleTwo(0.4d), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnThreeTimeKgPriceIfThreeKgs() {

        OrderItem orangesItem = threeKgsOfOranges();
        List<OrderItem> orderItems = Collections.singletonList(orangesItem);
        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(bigDecimalScaleTwo(5.97d), receipt.getSubtotal());
    }

    @Test
    public void shouldReturnReceiptWithSameTotalAndSubtotalIfNoSavings() {

        SavingsCalculator mockCalculator = mock(SavingsCalculator.class);
        cart = new ShoppingCart(mockCalculator);

        OrderItem beansItem = threeCansOfBeansWithDiscounts();

        List<OrderItem> orderItems = Collections.singletonList(beansItem);
        when(mockCalculator.calculateSavings(orderItems)).thenReturn(Collections.emptyList());

        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(Collections.emptyList(), receipt.getSavings());
        assertEquals(bigDecimalScaleTwo(1.5), receipt.getSubtotal());
        assertEquals(bigDecimalScaleTwo(0), receipt.getTotalSaving());
        assertEquals(bigDecimalScaleTwo(1.5), receipt.getTotal());
    }

    @Test
    public void shouldReturnReceiptWithSavingsIfPresent() {

        SavingsCalculator mockCalculator = mock(SavingsCalculator.class);
        cart = new ShoppingCart(mockCalculator);

        OrderItem beansItem = threeCansOfBeansWithDiscounts();
        List<Saving> expectedSavings = Collections.singletonList(beansSaving());

        List<OrderItem> orderItems = Collections.singletonList(beansItem);
        when(mockCalculator.calculateSavings(orderItems)).thenReturn(expectedSavings);

        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(expectedSavings, receipt.getSavings());
        assertEquals(bigDecimalScaleTwo(1.5), receipt.getSubtotal());
        assertEquals(bigDecimalScaleTwo(0.5), receipt.getTotalSaving());
        assertEquals(bigDecimalScaleTwo(1), receipt.getTotal());
    }

    @Test
    public void shouldReturnReceiptWithMultipleSavingsIfPresent() {

        SavingsCalculator mockCalculator = mock(SavingsCalculator.class);
        cart = new ShoppingCart(mockCalculator);

        OrderItem beansItem = threeCansOfBeansWithDiscounts();
        OrderItem cokeItem = threeCansOfCokeWithDiscounts();

        List<Saving> expectedSavings = Arrays.asList(beansSaving(), cokeSaving());

        List<OrderItem> orderItems = Arrays.asList(beansItem, cokeItem);
        when(mockCalculator.calculateSavings(orderItems)).thenReturn(expectedSavings);

        Receipt receipt = cart.checkout(new Order(orderItems));

        assertEquals(orderItems, receipt.getItems());
        assertEquals(expectedSavings, receipt.getSavings());
        assertEquals(bigDecimalScaleTwo(3.6), receipt.getSubtotal());
        assertEquals(bigDecimalScaleTwo(0.8), receipt.getTotalSaving());
        assertEquals(bigDecimalScaleTwo(2.8), receipt.getTotal());
    }
}