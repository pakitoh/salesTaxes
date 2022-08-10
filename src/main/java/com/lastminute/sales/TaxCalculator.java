package com.lastminute.sales;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {
    public static final BigDecimal GENERAL_TAX_RATE = BigDecimal.valueOf(10);
    public static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(5);
    public static final BigDecimal ROUNDING_PACE = new BigDecimal("0.05");

    public BigDecimal calculateTaxRate(Item item) {
        BigDecimal taxRate = BigDecimal.ZERO;
        if(item.description().toLowerCase().contains("import")) {
            taxRate = taxRate.add(IMPORT_TAX_RATE);
        }
        if(item.description().toLowerCase().contains("book")
                || item.description().toLowerCase().contains("chocolate")) {
            return taxRate;
        }
        return taxRate.add(GENERAL_TAX_RATE);
    }

    public BigDecimal calculateSalesTax(Item item, BigDecimal taxRate) {
        return round(item.price()
                .multiply(taxRate)
                .divide(BigDecimal.valueOf(100)));
    }

    public BigDecimal round(BigDecimal value) {
        return value.divide(ROUNDING_PACE, 0, RoundingMode.UP)
                .multiply(ROUNDING_PACE);
    }
}
