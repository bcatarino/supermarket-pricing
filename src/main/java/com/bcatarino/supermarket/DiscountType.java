package com.bcatarino.supermarket;

import java.math.BigDecimal;

public enum DiscountType {
    THREE_FOR_TWO {
        @Override
        public BigDecimal calculateActualDiscount(BigDecimal quantity, BigDecimal unitPrice) {
            BigDecimal two = BigDecimal.valueOf(2);
            BigDecimal three = BigDecimal.valueOf(3);

            BigDecimal paidOnDiscount = quantity.divideToIntegralValue(three).multiply(two);
            BigDecimal nonEligible = quantity.remainder(three);

            return quantity.multiply(unitPrice).subtract(paidOnDiscount.add(nonEligible).multiply(unitPrice))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

//            return paidOnDiscount.add(nonEligible).multiply(unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }, TWO_FOR_1_POUND {
        @Override
        public BigDecimal calculateActualDiscount(BigDecimal quantity, BigDecimal unitPrice) {
            BigDecimal two = BigDecimal.valueOf(2);

            BigDecimal paidOnDiscount = quantity.divideToIntegralValue(two);
            BigDecimal nonEligible = quantity.subtract(paidOnDiscount.multiply(two));

            BigDecimal toPay = paidOnDiscount.add(nonEligible.multiply(unitPrice));

            return quantity.multiply(unitPrice).subtract(toPay).setScale(2, BigDecimal.ROUND_HALF_UP);

//            return paidOnDiscount.multiply(BigDecimal.ONE).add(nonEligible.multiply(unitPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    };

    public BigDecimal calculateDiscount(BigDecimal quantity, BigDecimal unitPrice) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is mandatory");
        }

        if (unitPrice == null || unitPrice.compareTo(BigDecimal.valueOf(0.01d)) < 0) {
            throw new IllegalArgumentException("The Unit Price must be defined and higher than 0.01");
        }

        return calculateActualDiscount(quantity, unitPrice);
    }

    protected abstract BigDecimal calculateActualDiscount(BigDecimal quantity, BigDecimal unitPrice);
}
