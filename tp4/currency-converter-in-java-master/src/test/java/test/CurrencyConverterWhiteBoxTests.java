package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import currencyConverter.MainWindow;
import currencyConverter.Currency;

public class CurrencyConverterWhiteBoxTests {

    @Test 
    public void testUSDToEURConversion() {
        double convertedAmount = Currency.convert(100.0, 0.93);
        assertEquals(93.0, convertedAmount);
    }

    @Test 
    public void testInvalidAmount() {
        double convertedAmount = Currency.convert(-100.0, 0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testZeroExchangeRate() {
        double convertedAmount = Currency.convert(100.0, 0.0);
        assertEquals(0.0, convertedAmount);
    }

    @Test
    public void testInvalidExchangeRate() {
        double convertedAmount = Currency.convert(100.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testMinAmountInvalidExchangeRate() {
        double convertedAmount = Currency.convert(0.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testMaxAmountInvalidExchangeRate() {
        double convertedAmount = Currency.convert(1000000.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testInvalidAmountInvalidExchangeRate() {
        double convertedAmount = Currency.convert(-100.0, -0.93);
        assertEquals(-1.0, convertedAmount);
    }

}