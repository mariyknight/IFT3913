package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import currencyConverter.MainWindow;
import currencyConverter.Currency;

public class CurrencyConverterWhiteBoxTests {

    @Test 
    public void testCurrencyUSDToEURConversion() {
        double convertedAmount = Currency.convert(100.0, 0.93);
        assertEquals(93.0, convertedAmount);
    }

    @Test 
    public void testCurrencyInvalidAmount() {
        double convertedAmount = Currency.convert(-100.0, 0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testCurrencyZeroExchangeRate() {
        double convertedAmount = Currency.convert(100.0, 0.0);
        assertEquals(0.0, convertedAmount);
    }

    @Test
    public void testCurrencyInvalidExchangeRate() {
        double convertedAmount = Currency.convert(100.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testCurrencyMinAmountInvalidExchangeRate() {
        double convertedAmount = Currency.convert(0.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testCurrencyMaxAmountInvalidExchangeRate() {
        double convertedAmount = Currency.convert(1000000.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testCurrencyInvalidAmountInvalidExchangeRate() {
        double convertedAmount = Currency.convert(-100.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

}