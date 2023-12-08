package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import currencyConverter.Currency;

import java.util.ArrayList;

public class CurrencyConverterBlackBoxTests {

    @Test
    public void testCurrencyConvertForAllPairs() {

        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyFrom : currencies) {
            for (Currency currencyTo : currencies) {
                if (currencyFrom != currencyTo) {
                    Double amount = 100.0;
                    Double exchangeValue = currencyFrom.getExchangeValues().get(currencyTo.getShortName());
                    Double convertedAmount = Currency.convert(amount, exchangeValue);

                    assertNotNull(convertedAmount);
                }
                else {
                    Double amount = 100.0;
                    Double exchangeValue = currencyFrom.getExchangeValues().get(currencyTo.getShortName());
                    Double convertedAmount = Currency.convert(amount, exchangeValue);

                    assertEquals(amount, convertedAmount);
                }
            }
        }
    }

    @Test
    public void testCurrencyConvertForMaxAndMinAmount() {

        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyTest : currencies) {
            for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
                Double minAmount = 0.0;
                Double convertedMinAmount = Currency.convert(minAmount, exchangeValue);

                assertNull(convertedMinAmount);

                Double maxAmount = 1000000.0;
                Double convertedMaxAmount = Currency.convert(maxAmount, exchangeValue);

                assertNotNull(convertedMaxAmount);
            }
        }
    }

    @Test
    public void testCurrencyConvertAmountOverLimit() {

        ArrayList<Currency> currencies = Currency.init();

        for (Currency currency : currencies) {
            for (Double exchangeValue : currency.getExchangeValues().values()) {
                
                Double amountOverBottomLimit = -100.0;
                assertNull(Currency.convert(amountOverBottomLimit, exchangeValue));

                    
                Double amountOverTopLimit = 1000000000.0;
                assertNull(Currency.convert(amountOverTopLimit, exchangeValue));

            }
        }
    }

    @Test
    public void testCurrencyConvertInvalidExchangeRate() {
        double amount = 100.0;
        double exchangeRate = -1.51; // Taux de change invalide
        double convertedAmount = Currency.convert(amount, exchangeRate);
        assertEquals(-1, convertedAmount);
    }
}