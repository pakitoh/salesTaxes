package com.lastminute.sales;

import java.math.BigDecimal;

public record Item(String description, BigDecimal price) {

    public boolean isValid() {
        return description() != null
                && !description().isEmpty()
                && price() != null
                && price().compareTo(BigDecimal.ZERO) >= 0;
    }
}
