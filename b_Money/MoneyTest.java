package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000.0, SEK);
		EUR10 = new Money(1000.0, EUR);
		SEK200 = new Money(20000.0, SEK);
		EUR20 = new Money(2000.0, EUR);
		SEK0 = new Money((double) 0, SEK);
		EUR0 = new Money((double) 0, EUR);
		SEKn100 = new Money((double) -10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("Get Amount Test 1",10000.0, SEK100.getAmount(), 0.001);
		assertEquals("Get Amount Test 2",20000.0, SEK200.getAmount(), 0.001);
		assertEquals("Get Amount Test 3",-10000.0, SEKn100.getAmount(), 0.001);
		assertEquals("Get Amount Test 4",0.0, SEK0.getAmount(), 0.001);
		assertEquals("Get Amount Test 5",0.0, EUR0.getAmount(), 0.001);
		assertEquals("Get Amount Test 6",2000.0, EUR20.getAmount(), 0.001);
	}

	@Test
	public void testGetCurrency() {
		//assignment Tests
		assertEquals("Get Currency Test1", EUR, EUR0.getCurrency());
		assertEquals("Get Currency Test2", EUR, EUR10.getCurrency());
		assertEquals("Get Currency Test3", SEK, SEK200.getCurrency());
		//comparison Tests
		assertTrue(EUR0.getCurrency() == EUR20.getCurrency());
		assertFalse(EUR0.getCurrency() == SEK0.getCurrency());
	}

	@Test
	public void testToString() {
		//for 0
		assertEquals("toString Test1", "0.0 EUR", EUR0.toString());
		//For minus
		assertEquals("toString Test2", "-10000.0 SEK", SEKn100.toString());
		//Normal case
		assertEquals("toString Test2", "1000.0 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		//was already mostly tested in currency tests
		assertEquals("Global Value Test1", 1500.0, EUR10.universalValue(), 0.001);
		//For negative values
		assertEquals("Global Value Test2", -1500.0, SEKn100.universalValue(), 0.001);
	}

	@Test
	public void testEqualsMoney() {
		// One test for false and two for true (zero and non-zero)
		assertFalse(EUR20.equals(EUR10));
		assertTrue(SEK100.equals(EUR10));
		assertTrue(SEK0.equals(EUR0));
	}

	@Test
	public void testAdd() {
		//simple addition tests
		Money money1 = new Money(2000.0, EUR);
		assertTrue(money1.equals(EUR10.add(SEK100)));
		assertFalse(money1.equals(EUR10.add(SEK200)));
	}

	@Test
	public void testSub() {
		//simple substraction tests
		Money money1 = new Money(0.0, EUR);
		assertTrue(money1.equals(EUR10.sub(SEK100)));
		assertFalse(money1.equals(EUR10.sub(SEK200)));
	}

	@Test
	public void testIsZero() {
		//simple true and false case
		assertFalse(EUR10.isZero());
		assertTrue(EUR0.isZero());
	}

	@Test
	public void testNegate() {
		//best way to check is to compare
		//one with different currencies and one with same
		assertTrue(EUR10.equals(SEKn100.negate()));
		assertTrue(SEKn100.equals(SEK100.negate()));
		//one example for false
		assertFalse(EUR20.equals(SEKn100.negate()));
	}

	@Test
	public void testCompareTo() {
		//one case of each possibility
		assertEquals(0, EUR0.compareTo(SEK0));
		assertEquals(-1, EUR0.compareTo(SEK100));
		assertEquals(1, SEK200.compareTo(EUR10));
	}
}
