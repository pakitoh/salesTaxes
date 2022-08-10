package com.lastminute.sales;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TaxCalculatorTest {

    @Test
    public void getTaxRateShouldReturnZeroTaxRateWhenBook() {
        BigDecimal bookPrice = new BigDecimal("12.49");
        Item book = new Item("Book", bookPrice);

        BigDecimal taxRate = new TaxCalculator().getTaxRate(book);

        assertThat(taxRate, equalTo(BigDecimal.ZERO));
    }

    @Test
    public void getTaxRateShouldReturnZeroTaxRateWhenFood() {
        BigDecimal chocoBarPrice = new BigDecimal("0.85");
        Item chocoBar = new Item("Chocolate bar", chocoBarPrice);

        BigDecimal taxRate = new TaxCalculator().getTaxRate(chocoBar);

        assertThat(taxRate, equalTo(BigDecimal.ZERO));
    }

    @Test
    public void getTaxRateShouldReturnZeroTaxRateWhenMedicalProducts() {
        BigDecimal pillsPrice = new BigDecimal("9.75");
        Item pills = new Item("Packet of headache pills", pillsPrice);

        BigDecimal taxRate = new TaxCalculator().getTaxRate(pills);

        assertThat(taxRate, equalTo(BigDecimal.ZERO));
    }

    @Test
    public void getTaxRateShouldReturnGeneralTaxRateWhenGeneralItem() {
        BigDecimal cdPrice = new BigDecimal("14.99");
        Item cd = new Item("Music CD", cdPrice);

        BigDecimal taxRate = new TaxCalculator().getTaxRate(cd);

        assertThat(taxRate, equalTo(TaxCalculator.GENERAL_TAX_RATE));
    }

    @Test
    public void getTaxRateShouldReturnImportTaxRateWhenFoodImportedFood() {
        BigDecimal chocolatesPrice = new BigDecimal("11.85");
        Item chocolates = new Item("Imported chocolates", chocolatesPrice);

        BigDecimal taxRate = new TaxCalculator().getTaxRate(chocolates);

        assertThat(taxRate, equalTo(TaxCalculator.IMPORT_TAX_RATE));
    }

    @Test
    public void getTaxRateShouldReturnGeneralTaxRatePlusImportTaxRateWhenImportedGeneralItem() {
        BigDecimal perfumePrice = new BigDecimal("47.50");
        Item perfume = new Item("Imported bottle of perfume", perfumePrice);
        BigDecimal expectedTaxRate = TaxCalculator.GENERAL_TAX_RATE.add(TaxCalculator.IMPORT_TAX_RATE);

        BigDecimal taxRate = new TaxCalculator().getTaxRate(perfume);

        assertThat(taxRate, equalTo(expectedTaxRate));
    }
}
