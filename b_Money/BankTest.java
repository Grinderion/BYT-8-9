package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	Money SEK100, SEKm100;
	Hashtable<String, Account> accountList;
	
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
		SEK100 = new Money(10000.0, SEK);
		SEKm100 = new Money(-10000.0, SEK);
		accountList = SweBank.getAccountList();
	}

	//simple getter test
	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}

	//getCurrency test
	@Test
	public void testGetCurrency() {
		//simple getter test
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	//Checking whether opening account works properly
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

	//deposit test
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", SEK100);
		assertTrue(SEK100.equals(accountList.get("Ulrika").getBalance()));
	}

	//withdraw test
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Ulrika", SEKm100);
		assertTrue(SEKm100.equals(accountList.get("Ulrika").getBalance()));
	}

	//getBalance test
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", SEK100);
		assertTrue(SEK100.equals(accountList.get("Ulrika").getBalance()));
	}

	//transfer test
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Ulrika", "Bob", SEK100);
		assertTrue(SEK100.equals(accountList.get("Bob").getBalance()));
	}

	//timedPayment test
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Ulrika", "1", 0, 0, SEK100, SweBank, "Bob");
		SweBank.tick();
		assertTrue(SEK100.equals(accountList.get("Bob").getBalance()));
	}
}
