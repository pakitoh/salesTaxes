package com.lastminute.sales;

import java.math.BigDecimal;
import java.util.List;

public record Receipt(List<ReceiptLine> lines, BigDecimal salesTax, BigDecimal total) {
}
