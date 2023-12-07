package test;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import currencyConverter.Currency;
import currencyConverter.MainWindow;

public class MainWindowConverterBlackBoxTests {
    private ArrayList<Currency> currencies = Currency.init();


    @Test
    public void testMainWindowConvertForAllPairs(){

        for (Currency currencyFrom : currencies) {
            for (Currency currencyTo : currencies) {
                if (currencyFrom != currencyTo) {
                    Double amount = 100.0;
                    
                    Double convertedAmount = MainWindow.convert(currencyFrom.getShortName(), currencyTo.getShortName(), currencies, amount);

                    assertNotNull(convertedAmount);
                }
                else{
                    Double amount = 100.0;
                    
                    Double convertedAmount = MainWindow.convert(currencyFrom.getShortName(), currencyTo.getShortName(), currencies, amount);

                    assertEquals(amount, convertedAmount);
                }
            }
        }

    }

    @Test
    public void testMainWindowConvertForMaxAndMinAmount(){

        for (Currency currencyFrom : currencies) {
            for (Currency currencyTo : currencies) {

                Double minAmount = 0.0;
                Double convertedMinAmount = MainWindow.convert(currencyFrom.getShortName(), currencyTo.getShortName(), currencies, minAmount);

                assertNull(convertedMinAmount);

                Double maxAmount = 1000000.0;
                Double convertedMaxAmount = MainWindow.convert(currencyFrom.getShortName(), currencyTo.getShortName(), currencies, maxAmount);

                assertNotNull(convertedMaxAmount);
            }
        }
    }

    @Test
    public void testMainWindowConvertForAmountOverLimit(){
        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyFrom : currencies) {
            for (Currency currencyTo : currencies) {

                Double amountOverBottomLimit = -10000;
                Double convertedAmount = MainWindow.convert(currencyFrom.getShortName(), currencyTo.getShortName(), currencies, amountOverBottomLimit);

                assertNull(convertedAmount);

                Double amountOverTopLimit = 9999999;
                Double convertedAmount = MainWindow.convert(currencyFrom.getShortName(), currencyTo.getShortName(), currencies, amountOverTopLimit);

                assertNull(convertedAmount);
            }
        }
    }

    @Test
    public void testConversionInvalidCurrency() {
        double amount = 100.0;
        double convertedAmount = MainWindow.convert("ABC", "XYZ", currencies, amount);
        assertEquals(-1, convertedAmount);
    }
}
