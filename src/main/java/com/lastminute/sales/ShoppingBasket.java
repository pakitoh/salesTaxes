package com.lastminute.sales;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingBasket {
    public static final BigDecimal GENERAL_TAX_RATE = BigDecimal.valueOf(10);
    public static final BigDecimal ROUNDING_PACE = new BigDecimal("0.05");

    public Receipt purchase(List<Item> items) {
        return new Receipt(items.stream()
                .map(this::purchase)
                .collect(Collectors.toList()));
    }

    public ReceiptLine purchase(Item item) {
        final BigDecimal taxRate = calculateTaxRate(item);
        return new ReceiptLine(
                item.description(),
                item.price(),
                taxRate,
                calculateSalesTax(item, taxRate)
        );
    }

    private BigDecimal calculateTaxRate(Item item) {
        if(item.description().toLowerCase().contains("book")) {
            return BigDecimal.ZERO;
        }
        return GENERAL_TAX_RATE;
    }

    private BigDecimal calculateSalesTax(Item item, BigDecimal taxRate) {
        return round(item.price()
                .multiply(taxRate)
                .divide(BigDecimal.valueOf(100)));
    }

    public BigDecimal round(BigDecimal value) {
        return value.divide(ROUNDING_PACE, 0, RoundingMode.UP)
                .multiply(ROUNDING_PACE);
    }
}
