package com.lastminute.sales;

import java.math.BigDecimal;
import java.util.List;

public record Receipt(List<ReceiptLine> lines) {

    public BigDecimal getTotal() {
        return lines.stream()
                .map(ReceiptLine::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
