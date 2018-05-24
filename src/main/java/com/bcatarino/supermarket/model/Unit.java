package com.bcatarino.supermarket.model;

import java.math.BigDecimal;

public enum Unit {
    QUANTITY {
        @Override
        public boolean isValidAmount(BigDecimal value) {
            return value != null && value.compareTo(BigDecimal.ZERO) > 0;
        }
    }, WEIGHT {
        @Override
        public boolean isValidAmount(BigDecimal value) {
            return value != null && value.compareTo(BigDecimal.valueOf(0.01d)) >= 0;
        }
    };

    public abstract boolean isValidAmount(BigDecimal value);
}