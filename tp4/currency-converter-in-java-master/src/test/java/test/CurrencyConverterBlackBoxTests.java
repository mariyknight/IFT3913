package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyConverterBlackBoxTests {

    private CurrencyConversion conversion;

    // Helper method to set up conversion rates for testing (setUp in JUnit)
    @Before
    public void setUpConversionRates() {
        conversion = new CurrencyConversion();
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("CAD", 1.25);
        rates.put("GBP", 0.75);
        rates.put("EUR", 0.90);
        rates.put("CHF", 0.95);
        rates.put("AUD", 1.40);
        conversion.setRates(rates);
    }

    // Method to validate the currency type
    private boolean validateCurrencyType(String currency) {
        List<String> validCurrencies = Arrays.asList("USD", "CAD", "GBP", "EUR", "CHF", "AUD");
        return validCurrencies.contains(currency);
    }

    // Method to validate the amount
    private boolean validateAmount(double amount) {
        return amount >= 0.0 && amount <= 1000000.0;
    }

    // Test validateCurrencyType for valid currencies
    @Test
    public void testValidateCurrencyType_ValidCurrencies() {
        assertTrue("USD should be a valid currency", validateCurrencyType("USD"));
        assertTrue("CAD should be a valid currency", validateCurrencyType("CAD"));
        assertTrue("GBP should be a valid currency", validateCurrencyType("GBP"));
        assertTrue("EUR should be a valid currency", validateCurrencyType("EUR"));
        assertTrue("CHF should be a valid currency", validateCurrencyType("CHF"));
        assertTrue("AUD should be a valid currency", validateCurrencyType("AUD"));
    }

    // Test validateCurrencyType for invalid currency
    @Test
    public void testValidateCurrencyType_InvalidCurrency() {
        assertFalse("XYZ should not be a valid currency", validateCurrencyType("XYZ"));
    }

    // Test validateAmount for valid amounts (lower boundary)
    @Test
    public void testValidateAmount_ValidLowerBoundary() {
        assertTrue("0.0 should be a valid amount", validateAmount(0.0));
    }

    // Test validateAmount for valid amounts (upper boundary)
    @Test
    public void testValidateAmount_ValidUpperBoundary() {
        assertTrue("1000000.0 should be a valid amount", validateAmount(1000000.0));
    }

    // Test validateAmount for invalid negative amount
    @Test
    public void testValidateAmount_InvalidNegativeAmount() {
        assertFalse("-0.01 should not be a valid amount", validateAmount(-0.01));
    }

    // Test validateAmount for excessively large amount
    @Test
    public void testValidateAmount_ExcessivelyLargeAmount() {
        assertFalse("1000000.01 should not be a valid amount", validateAmount(1000000.01));
    }

    // Test CurrencyConvertor logic for valid input
    @Test
    public void testCurrencyConvertor_ValidInput() throws ParseException {
        assertEquals("Conversion from USD to CAD with amount 100 should be 125.0",
                125.0, CurrencyConvertor.convert(100, "USD", "CAD", conversion), 0.001);
    }

    // Test CurrencyConvertor logic for invalid currency code
    @Test(expected = ParseException.class)
    public void testCurrencyConvertor_InvalidCurrencyCode() throws ParseException {
        CurrencyConvertor.convert(100, "USD", "XXX", conversion);
    }

    // Test CurrencyConvertor logic for invalid negative amount
    @Test(expected = ParseException.class)
    public void testCurrencyConvertor_InvalidNegativeAmount() throws ParseException {
        CurrencyConvertor.convert(-100, "USD", "CAD", conversion);
    }

    // Test CurrencyConvertor logic for amount exceeding upper limit
    @Test(expected = ParseException.class)
    public void testCurrencyConvertor_AmountExceedingUpperLimit() throws ParseException {
        CurrencyConvertor.convert(1000000.01, "USD", "CAD", conversion);
    }
}

// Helper classes/models to simulate the actual CurrencyConversion and CurrencyConvertor classes
class CurrencyConversion {
    private Map<String, Double> rates = new HashMap<>();

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}

class CurrencyConvertor {
    public static double convert(double amount, String from, String to, CurrencyConversion conversion) throws ParseException {
        if (!conversion.getRates().containsKey(to) || !conversion.getRates().containsKey(from)) {
            throw new ParseException("Currency code is not valid", 0);
        }
        double rateTo = conversion.getRates().get(to);
        double rateFrom = conversion.getRates().get(from);
        return amount * (rateTo / rateFrom);
    }
}