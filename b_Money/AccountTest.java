package b_Money;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	Money SEK100;
	Hashtable<String, Account> accountList;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		SweBank.openAccount("Hans");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000.0, SEK));
		SweBank.deposit("Alice", new Money(10000000.0, SEK));
		SEK100 = new Money(10000.0, SEK);
		accountList = SweBank.getAccountList();
	}

	//Checking if add timed payment works for both the sender and receiver
	@Test
	public void testAddRemoveTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 1, 0, new Money(1000000.0, SEK), SweBank, "Alice");
		testAccount.tick();
		testAccount.tick();
		assertEquals(11000000.0, SweBank.getBalance("Alice").doubleValue(), 0.001);
		assertTrue(new Money(9000000.0, SEK).equals(testAccount.getBalance()));
	}

	//Checking timed payment
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Alice", "2", 0, 0, new Money(1000000.0, SEK), SweBank, "Hans");
		SweBank.tick();
		SweBank.tick();
		assertEquals(8000000.0, SweBank.getBalance("Alice").doubleValue(), 0.001);
	}

	//Checking if withdrawing and depositing works
	@Test
	public void testAddWithdraw() {
		//from positive to negative value
		testAccount.withdraw(new Money(17000000.0, SEK));
		assertTrue(new Money(-7000000.0, SEK).equals(testAccount.getBalance()));
		//from negative set to zero
		testAccount.deposit(new Money(7000000.0, SEK));
		assertTrue(new Money(0.0, SEK).equals(testAccount.getBalance()));
	}

	//Checking GetBalance
	@Test
	public void testGetBalance() {
		//basic check
		assertTrue(new Money(10000000.0, SEK).equals(testAccount.getBalance()));
	}
}
