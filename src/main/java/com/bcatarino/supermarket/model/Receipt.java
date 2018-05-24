package com.bcatarino.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Receipt {

    private List<OrderItem> items;

    private BigDecimal total;
}
