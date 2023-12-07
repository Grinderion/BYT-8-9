package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		//simple getter test
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		//simple getter test
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
	Hashtable<String, Account> accountList = SweBank.getAccountList();
		if (accountList.containsKey("Ulrika")) {
			assertTrue(!(accountList.get("Ulrika") == null));
		}
		else {
			throw new AccountDoesNotExistException();
		}

	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		fail("Write test case here");
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		fail("Write test case here");
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		fail("Write test case here");
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		fail("Write test case here");
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		fail("Write test case here");
	}
}
