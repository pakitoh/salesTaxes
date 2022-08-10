package com.lastminute.sales;

import java.math.BigDecimal;

public record ReceiptLine(
        Item item,
        BigDecimal taxRate,
        BigDecimal salesTax,
        BigDecimal total) {
}
