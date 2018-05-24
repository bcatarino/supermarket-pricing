package com.bcatarino.supermarket.model;

import com.bcatarino.supermarket.DiscountType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@EqualsAndHashCode(of = "name")
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Product {

    private String name;

    private BigDecimal pricePerUnit;

    private Unit unit;

    private Optional<DiscountType> discount;

    public Product(String name, BigDecimal pricePerUnit, Unit unit) {
        this(name, pricePerUnit, unit, Optional.empty());
    }

    public Product(String name, BigDecimal pricePerUnit, Unit unit, Optional<DiscountType> discount) {

        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name is mandatory");
        }

        if (pricePerUnit == null || pricePerUnit.compareTo(BigDecimal.valueOf(0.01d)) <= 0) {
            throw new IllegalArgumentException("Price per Unit is Mandatory and must be higher than 0.01");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit is mandatory");
        }

        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
        this.discount = discount;
    }
}
