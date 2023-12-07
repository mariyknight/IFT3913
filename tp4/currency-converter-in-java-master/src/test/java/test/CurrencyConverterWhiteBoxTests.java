package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import currencyConverter.Currency;

public class CurrencyConverterWhiteBoxTests {

    @Test // instructions
    public void testNormalConversion() {
        double convertedAmount = Currency.convert(100.0, 1.2);
        assertEquals(120.0, convertedAmount);
    }

    @Test // arcs
    public void testNegativeAmount() {
        double convertedAmount = Currency.convert(-100.0, 1.2);
        assertEquals(-1.0, convertedAmount);
    }

    @Test // chemins
    public void testZeroExchangeRate() {
        double convertedAmount = Currency.convert(100.0, 0.0);
        assertEquals(0.0, convertedAmount);
    }

    @Test // conditions
    public void testNegativeExchangeRate() {
        double convertedAmount = Currency.convert(100.0, -1.2);
        assertEquals(-1.0, convertedAmount);
    }

    @Test // i-chemins
    public void testZeroAmountNegativeExchangeRate() {
        double convertedAmount = Currency.convert(0.0, -1.2);
        assertEquals(-1.0, convertedAmount);
    }

}