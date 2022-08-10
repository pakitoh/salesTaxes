package com.lastminute.sales;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingBasket {

    private final TaxCalculator taxCalculator;

    public ShoppingBasket() {
        this.taxCalculator = new TaxCalculator();
    }

    public Receipt purchase(List<Item> items) {
        return new Receipt(items.stream()
                .map(this::purchase)
                .collect(Collectors.toList()));
    }

    public ReceiptLine purchase(Item item) {
        final BigDecimal taxRate = taxCalculator.getTaxRate(item);
        return new ReceiptLine(
                item.description(),
                item.price(),
                taxRate,
                taxCalculator.calculateSalesTax(item, taxRate)
        );
    }

}
