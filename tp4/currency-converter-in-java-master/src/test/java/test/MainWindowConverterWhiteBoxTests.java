package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import currencyConverter.MainWindow;
import currencyConverter.Currency;

public class MainWindowConverterWhiteBoxTests {
    private ArrayList<Currency> currencies = Currency.init();

    @Test
    public void testMainWindowUSDToEuroConversion() {
        double convertedAmount = MainWindow.convert("Chinese Yuan Renminbi", "Japanese Yen", currencies, 100.0);
        assertNotNull(convertedAmount);
    }

    @Test
    public void testMainWindowInvalidCurrencyConversion() {
        double convertedAmount = MainWindow.convert("ABC", "XYZ", currencies, 100.0);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testMainWindowInvalidAmountConversion() {
        double convertedAmount = MainWindow.convert("Chinese Yuan Renminbi", "Japanese Yen", currencies, -10000.0);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testMainWindowMinAmountConversion() {
        double convertedAmount = MainWindow.convert("Chinese Yuan Renminbi", "Japanese Yen", currencies, 0.0);
        assertNull(convertedAmount);
    }

    @Test
    public void testMainWindowMaxAmountConversion() {
        double convertedAmount = MainWindow.convert("Chinese Yuan Renminbi", "Japanese Yen", currencies, 1000000.0);
        assertNull(convertedAmount);
    }

    @Test
    public void testMainWindowAmountOverLimitConversion() {
        double convertedAmount = MainWindow.convert("Chinese Yuan Renminbi", "Japanese Yen", currencies, 1000001.0);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testMainWindowInvalidCurrenciesAndInvalidAmount() {
        double convertedAmount = MainWindow.convert("ABC", "XYZ", currencies, -1.51);
        assertEquals(-1.0, convertedAmount);
    }

    @Test
    public void testMainWindowMaxAmount() {
        double convertedAmount = MainWindow.convert("Chinese Yuan Renminbi", "Japanese Yen", currencies, 1000000.0);
        assertNotNull(convertedAmount);
    }
}
