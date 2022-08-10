package com.lastminute.sales;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Set;

public class TaxCalculator {
    public static final BigDecimal GENERAL_TAX_RATE = BigDecimal.valueOf(10);
    public static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(5);
    public static final Set<String> EXEMPT_KEYWORDS = Set.of("book", "chocolate", "pills");
    public static final Set<String> IMPORTED_KEYWORDS = Set.of("import");

    public BigDecimal getTaxRate(Item item) {
        final String[] descriptionWords = item.description().toLowerCase().split("\s");
        if(isImported(descriptionWords) && !isExempt(descriptionWords)) {
            return IMPORT_TAX_RATE.add(GENERAL_TAX_RATE);
        } else if(isImported(descriptionWords) && isExempt(descriptionWords)) {
            return IMPORT_TAX_RATE;
        } else if(!isImported(descriptionWords) && !isExempt(descriptionWords)) {
            return GENERAL_TAX_RATE;
        }
        return BigDecimal.ZERO;
    }

    private boolean isExempt(String[] descriptionWords) {
        return EXEMPT_KEYWORDS.stream()
                .anyMatch(exemptWord -> Arrays.stream(descriptionWords)
                        .anyMatch(description -> description.contains(exemptWord)));
    }

    private boolean isImported(String[] descriptionWords) {
        return IMPORTED_KEYWORDS.stream()
                .anyMatch(importWord -> Arrays.stream(descriptionWords)
                        .anyMatch(description -> description.contains(importWord)));
    }

    public BigDecimal calculateSalesTax(Item item, BigDecimal taxRate) {
        return round(item.price()
                .multiply(taxRate)
                .divide(BigDecimal.valueOf(100)));
    }

    public BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
