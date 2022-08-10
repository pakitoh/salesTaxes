package com.lastminute.sales;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShoppingBasket {

    private final TaxCalculator taxCalculator;

    public ShoppingBasket() {
        this.taxCalculator = new TaxCalculator();
    }

    public Receipt purchase(List<Item> items) {
        final List<ReceiptLine> lines = validate(items)
                .map(this::purchaseItem)
                .collect(Collectors.toList());
        return new Receipt(lines);
    }

    private Stream<Item> validate(List<Item> items) {
        if(items == null) {
            return Stream.of();
        }
        return items.stream().filter(Item::isValid);
    }


    private ReceiptLine purchaseItem(Item item) {
        final BigDecimal taxRate = taxCalculator.getTaxRate(item);
        final BigDecimal salesTax = taxCalculator.calculateSalesTax(item, taxRate);
        return new ReceiptLine(
                item,
                taxRate,
                salesTax,
                item.price().add(salesTax));
    }

}
