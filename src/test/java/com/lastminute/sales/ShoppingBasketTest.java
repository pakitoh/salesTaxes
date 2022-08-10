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

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnReceiptWithTotalAndNoTaxWhenOneBook() {
        BigDecimal bookPrice = new BigDecimal("12.49");
        Item book = new Item("Book", bookPrice);
        BigDecimal expectedTotal = bookPrice;
        BigDecimal expectedTaxRate = BigDecimal.ZERO;
        BigDecimal expectedSalesTax = new BigDecimal("0.00");

        Receipt receipt = new ShoppingBasket().purchase(List.of(book));

        assertThat(receipt.lines().size(), equalTo(1));
        assertThat(receipt.lines().get(0).taxRate(), equalTo(expectedTaxRate));
        assertThat(receipt.lines().get(0).salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnReceiptWithTotalAndGeneralTaxWhenOneCD() {
        BigDecimal cdPrice = new BigDecimal("14.99");
        Item cd = new Item("Music CD", cdPrice);
        BigDecimal expectedTotal = new BigDecimal("16.49");
        BigDecimal expectedTaxRate = new BigDecimal("10");
        BigDecimal expectedSalesTax = new BigDecimal("1.50");

        Receipt receipt = new ShoppingBasket().purchase(List.of(cd));

        assertThat(receipt.lines().size(), equalTo(1));
        assertThat(receipt.lines().get(0).taxRate(), equalTo(expectedTaxRate));
        assertThat(receipt.lines().get(0).salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }

}
