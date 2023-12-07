package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	//Basic Test setters and getters
	@Test
	public void testGetName() {
		assertEquals("SEK Name Test", "SEK", SEK.getName());
		assertEquals("DKK Name Test", "DKK", DKK.getName());
		assertEquals("EUR Name Test", "EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals("Name Test 1", 0.15, SEK.getRate(), 0.001);
		assertEquals("Name Test 2", 0.20, DKK.getRate(), 0.001);
		assertEquals("Name Test 3", 1.5, EUR.getRate(), 0.001);
	}
	
	@Test
	public void testSetRate() {
		EUR.setRate(2.0);
		assertEquals("Set Rate Test", 2.0, EUR.getRate(), 0.01);
	}

	/*
		Testing universal values
		simply test whether multiplication works correctly
	 */
	@Test
	public void testGlobalValue() {
		assertEquals("Universal Value Test 1", 15.0, EUR.universalValue(10.0), 0.001);
		assertEquals("Universal Value Test 2", -15.0, EUR.universalValue(-10.0), 0.001);
		assertEquals("Universal Value Test 3", 2, DKK.universalValue(10.0), 0.001);
		// Test for non-integer number
		assertEquals("Universal Value Test 4", 0.44, DKK.universalValue(2.2), 0.001);
		//Test for non-positive number
		assertEquals("Universal Value Test 5", -47.12, DKK.universalValue(-235.6), 0.001);

	}
	@Test
	public void testValueInThisCurrency() {
		//Test for positive value
		assertEquals("Value in Currency Test 1",
				1.333, EUR.valueInThisCurrency(10.0, DKK), 0.001);
		//Test for negative value
		assertEquals("Value in Currency Test 2",
				-100, SEK.valueInThisCurrency(-10.0, EUR), 0.001);
		// Test for non-integer value
		assertEquals("Value in Currency Test 3",
				407.25, DKK.valueInThisCurrency(54.3, EUR), 0.001);

	}

}
