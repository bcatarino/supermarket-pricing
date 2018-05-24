package com.bcatarino.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "name")
public class Product {

    private String name;

    private BigDecimal pricePerUnit;

    private Unit unit;
}
