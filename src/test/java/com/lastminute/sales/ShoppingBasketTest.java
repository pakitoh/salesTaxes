package com.lastminute.sales;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShoppingBasketTest {

    @Test
    public void purchaseShouldReturnReceiptWithTotal0WhenNoItems() {
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(List.of());

        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }
}
