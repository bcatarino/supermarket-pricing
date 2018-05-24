package com.bcatarino.supermarket;

import com.bcatarino.supermarket.model.OrderItem;
import com.bcatarino.supermarket.model.Product;
import com.bcatarino.supermarket.model.Saving;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SavingsCalculator {

    public List<Saving> calculateSavings(List<OrderItem> items) {

        List<ProductCount> quantityPerProduct = calculateQuantityPerProduct(items);

        Map<Product, BigDecimal> discountPerProduct = calculateDiscountPerProduct(quantityPerProduct);

        return discountPerProduct.entrySet().stream().map(this::toSaving).collect(Collectors.toList());
    }

    private List<ProductCount> calculateQuantityPerProduct(List<OrderItem> items) {
        return items.stream().map(i -> new ProductCount(i.getProduct(), i.getQuantity())).collect(Collectors.toList());
    }

    private Map<Product, BigDecimal> calculateDiscountPerProduct(List<ProductCount> quantityPerProduct) {
        return quantityPerProduct.stream()
                .filter(i -> i.getProduct().getDiscount().isPresent())
                .collect(Collectors.toMap(ProductCount::getProduct, this::calculateDiscount));
    }

    private BigDecimal calculateDiscount(ProductCount item) {
        return item.getProduct().getDiscount().map(discountType -> discountType.calculateDiscount(
                item.getCount(), item.getProduct().getPricePerUnit())).orElse(BigDecimal.ZERO);
    }

    private Saving toSaving(Map.Entry<Product, BigDecimal> discount) {
        return new Saving(discount.getKey().getName(), discount.getKey().getDiscount().get(), discount.getValue());
    }

    @AllArgsConstructor
    @Getter
    private class ProductCount {
        private Product product;
        private BigDecimal count;
    }
}
