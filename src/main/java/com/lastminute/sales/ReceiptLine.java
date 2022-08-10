package com.lastminute.sales;

import java.math.BigDecimal;

public record ReceiptLine(String description, BigDecimal price, BigDecimal taxRate, BigDecimal salesTax) {
    public BigDecimal total() {
        return price.add(salesTax);
    }
}
