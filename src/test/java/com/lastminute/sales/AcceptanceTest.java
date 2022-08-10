package com.lastminute.sales;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AcceptanceTest {

    @Test
    public void at1() {
        BigDecimal cdPrice = new BigDecimal("14.99");
        Item cd = new Item("Music CD", cdPrice);
        BigDecimal bookPrice = new BigDecimal("12.49");
        Item book = new Item("Book", bookPrice);
        BigDecimal chocoBarPrice = new BigDecimal("0.85");
        Item chocoBar = new Item("Chocolate bar", chocoBarPrice);
        BigDecimal expectedTotal = new BigDecimal("29.83");
        BigDecimal expectedSalesTax = new BigDecimal("1.50");

        Receipt receipt = new ShoppingBasket().purchase(List.of(cd, book, chocoBar));

        assertThat(receipt.lines().size(), equalTo(3));
        assertThat(receipt.salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }

    @Test
    public void at2() {
        BigDecimal chocolatesPrice = new BigDecimal("10.00");
        Item chocolates = new Item("Imported box of chocolates", chocolatesPrice);
        BigDecimal perfumePrice = new BigDecimal("47.50");
        Item perfume = new Item("Imported bottle of perfume", perfumePrice);
        BigDecimal expectedTotal = new BigDecimal("65.15");
        BigDecimal expectedSalesTax = new BigDecimal("7.65");

        Receipt receipt = new ShoppingBasket().purchase(List.of(chocolates, perfume));

        assertThat(receipt.lines().size(), equalTo(2));
        assertThat(receipt.salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }

    @Test
    public void at3() {
        BigDecimal importedPerfumePrice = new BigDecimal("27.99");
        Item importedPerfume = new Item("Imported bottle of perfume", importedPerfumePrice);
        BigDecimal perfumePrice = new BigDecimal("18.99");
        Item perfume = new Item("Bottle of perfume", perfumePrice);
        BigDecimal pillsPrice = new BigDecimal("9.75");
        Item pills = new Item("Packet of headache pills", pillsPrice);
        BigDecimal chocolatesPrice = new BigDecimal("11.25");
        Item chocolates = new Item("Imported chocolates", chocolatesPrice);
        BigDecimal expectedTotal = new BigDecimal("74.68");
        BigDecimal expectedSalesTax = new BigDecimal("6.70");

        Receipt receipt = new ShoppingBasket().purchase(List.of(importedPerfume, perfume, pills, chocolates));

        assertThat(receipt.lines().size(), equalTo(4));
        assertThat(receipt.salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.getTotal(), equalTo(expectedTotal));
    }
}
