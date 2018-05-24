package com.bcatarino.supermarket.model;

import com.bcatarino.supermarket.DiscountType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
public class Saving {

    private String productName;

    private DiscountType discountType;

    private BigDecimal savingValue;

    public Saving(String productName, DiscountType discountType, BigDecimal savingValue) {

        if (StringUtils.isEmpty(productName)) {
            throw new IllegalArgumentException("Product Name is mandatory");
        }

        if (discountType == null) {
            throw new IllegalArgumentException("Discount Type is mandatory");
        }

        if (savingValue == null || savingValue.compareTo(BigDecimal.valueOf(0.01)) < 0) {
            throw new IllegalArgumentException("Discount Value is mandatory and must be at least 0.01");
        }

        this.productName = productName;
        this.discountType = discountType;
        this.savingValue = savingValue;
    }
}
