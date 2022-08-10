package com.lastminute.sales;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingBasket {
    public Receipt purchase(List<Item> items) {
        return new Receipt(items.stream()
                .map(this::purchase)
                .collect(Collectors.toList()));
    }

    public ReceiptLine purchase(Item item) {
        return new ReceiptLine(
                item.description(),
                item.price(),
                calculateTaxRate(item),
                calculateSalesTax(item)
        );
    }

    private BigDecimal calculateTaxRate(Item item) {
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateSalesTax(Item item) {
        return BigDecimal.ZERO;
    }
}
