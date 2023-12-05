Places vos tests ici. Utilisation de JUnit5 requise.

//Test boite noire Currency

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
        }
    }
}

//Test boite blanc Currency

@Test
public void testCurrencyConvertForMaxAndMinAmount() {
    ArrayList<Currency> currencies = Currency.init();

    for (Currency currencyTest : currencies) {
        for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
            Double minAmount = 0;
            Double convertedMinAmount = Currency.convert(minAmount, exchangeValue);

            assertNull(convertedMinAmount);

            Double maxAmount = 1000000;
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
            
            Double amountOverBottomLimit = -100;
            assertNull(Currency.convert(amountOverBottomLimit, exchangeValue));

                
            Double amountOverTopLimit = 1000000000;
            assertNull(Currency.convert(amountOverTopLimit, exchangeValue));

        }
    }
}

//Test boite noire MainWindow

@Test
public void testMainWindowConvertForAllPairs(){

}

//Test boite blanc MainWindow

public void testMainWindowConvertForMaxAndMinAmount(){
    
}

public void testMainWindowConvertForAmountOverLimit(){
    
}