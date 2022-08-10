package com.lastminute.sales;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShoppingBasketTest {

    @Test
    public void purchaseShouldReturnEmptyReceiptWhenNullItems() {
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(null);

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnEmptyReceiptWhenNoItems() {
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(List.of());

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnEmptyReceiptWhenItemWithNullDescription() {
        BigDecimal bookPrice = new BigDecimal("12.49");
        Item book = new Item(null, bookPrice);
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(List.of(book));

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnEmptyReceiptWhenItemWithEmptyDescription() {
        BigDecimal bookPrice = new BigDecimal("12.49");
        Item book = new Item("", bookPrice);
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(List.of(book));

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnEmjptyReceiptWhenItemWithNullPrice() {
        Item book = new Item("Book", null);
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(List.of(book));

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnEmptyReceiptWhenItemWithNegativePrice() {
        BigDecimal bookPrice = new BigDecimal("-12.49");
        Item book = new Item("Book", bookPrice);
        BigDecimal expectedTotal = BigDecimal.ZERO;

        Receipt receipt = new ShoppingBasket().purchase(List.of(book));

        assertThat(receipt.lines().isEmpty(), equalTo(true));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnReceiptWithTotalAndNoTaxWhenExemptedItem() {
        BigDecimal bookPrice = new BigDecimal("12.49");
        Item book = new Item("Book", bookPrice);
        BigDecimal expectedTotal = bookPrice;
        BigDecimal expectedTaxRate = BigDecimal.ZERO;
        BigDecimal expectedSalesTax = new BigDecimal("0.00");

        Receipt receipt = new ShoppingBasket().purchase(List.of(book));

        assertThat(receipt.lines().size(), equalTo(1));
        assertThat(receipt.lines().get(0).taxRate(), equalTo(expectedTaxRate));
        assertThat(receipt.lines().get(0).salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.lines().get(0).item().price(), equalTo(bookPrice));
        assertThat(receipt.lines().get(0).total(), equalTo(expectedTotal));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnReceiptWithTotalAndGeneralTaxWhenGeneralItem() {
        BigDecimal cdPrice = new BigDecimal("14.99");
        Item cd = new Item("Music CD", cdPrice);
        BigDecimal expectedTotal = new BigDecimal("16.49");
        BigDecimal expectedTaxRate = new BigDecimal("10");
        BigDecimal expectedSalesTax = new BigDecimal("1.50");

        Receipt receipt = new ShoppingBasket().purchase(List.of(cd));

        assertThat(receipt.lines().size(), equalTo(1));
        assertThat(receipt.lines().get(0).taxRate(), equalTo(expectedTaxRate));
        assertThat(receipt.lines().get(0).salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.lines().get(0).item().price(), equalTo(cdPrice));
        assertThat(receipt.lines().get(0).total(), equalTo(expectedTotal));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnReceiptAddingTotalAndTaxesWhenMultipleItems() {
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
        assertThat(receipt.total(), equalTo(expectedTotal));
    }

    @Test
    public void purchaseShouldReturnReceiptIncludingImportTaxWhenImportedItem() {
        BigDecimal chocoPrice = new BigDecimal("10.00");
        Item choco = new Item("Imported box of chocolates", chocoPrice);
        BigDecimal expectedTotal = new BigDecimal("10.50");
        BigDecimal expectedSalesTax = new BigDecimal("0.50");

        Receipt receipt = new ShoppingBasket().purchase(List.of(choco));

        assertThat(receipt.lines().size(), equalTo(1));
        assertThat(receipt.salesTax(), equalTo(expectedSalesTax));
        assertThat(receipt.total(), equalTo(expectedTotal));
    }
}
