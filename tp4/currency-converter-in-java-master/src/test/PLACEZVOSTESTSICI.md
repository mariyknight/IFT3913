Places vos tests ici. Utilisation de JUnit5 requise.

//Test boite noire MainWindow

@Test
public void testConvertMethodInMainWindow() {
    // Choisissez des devises pour le test
    String currency1 = "US Dollar";
    String currency2 = "Euro";

    // Appel de la méthode convert dans MainWindow
    Double result = MainWindow.convert(currency1, currency2, currencies, 100.0);

    // Calcul manuel du résultat attendu
    Double expected = Currency.convert(100.0, currencies.get(0).getExchangeValues().get("EUR"));

    // Assurez-vous que le résultat est conforme aux attentes
    assertEquals(expected, result);
}

//Test boite blanc MainWindow

//Test boite noire Currency

@Test
public void testConvertUSDtoEUR() {
    // USD to EUR exchange values is 0.93
    Double result = Currency.convert(100.0, usDollar.getExchangeValues().get("EUR"));
    assertEquals(93.0, result);
}

@Test
public void testConvertUSDtoGBP() {
    // USD to GBP exchange values is 0.66
    Double result = Currency.convert(100.0, usDollar.getExchangeValues().get("GBP"));
    assertEquals(66.0, result);
}

@Test
public void testConvertUSDtoCHF() {
    // USD to CHF exchange values is 1.01
    Double result = Currency.convert(100.0, usDollar.getExchangeValues().get("CHF"));
    assertEquals(101.0, result);
}

@Test
public void testConvertUSDtoCNY() {
    // USD to CNY exchange values is 6.36
    Double result = Currency.convert(100.0, usDollar.getExchangeValues().get("CNY"));
    assertEquals(636.0, result);
}

@Test
public void testConvertUSDtoJPY() {
    // USD to JPY exchange values is 123.54
    Double result = Currency.convert(100.0, usDollar.getExchangeValues().get("JPY"));
    assertEquals(12354.0, result);
}

@Test
public void testConvertEURtoUSD() {
    // EUR to USD exchange values is 123.54
    Double result = Currency.convert(100.0, euro.getExchangeValues().get("USD"));
    assertEquals(107.3, result);
}

//Test boite blanc Currency