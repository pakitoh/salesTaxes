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
        return new Receipt(lines, calculateSalesTax(lines), calculateTotal(lines));
    }

    private Stream<Item> validate(List<Item> items) {
        if(items == null) {
            return Stream.of();
        }
        return items.stream().filter(this::isValid);
    }

    public boolean isValid(Item item) {
        return item.description() != null
                && !item.description().isEmpty()
                && item.price() != null
                && item.price().compareTo(BigDecimal.ZERO) >= 0;
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

    private BigDecimal calculateSalesTax(List<ReceiptLine> lines) {
        return lines.stream()
                .map(ReceiptLine::salesTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotal(List<ReceiptLine> lines) {
        return lines.stream()
                .map(ReceiptLine::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
