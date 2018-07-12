package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("JaganMohanReddy", "9789755573",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Chandrababu", "9704463293",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Lokesh", "9440743785",new Wallet(new BigDecimal(7000)));
				
		 data.put("9789755573", cust1);
		 data.put("9704463293", cust2);	
		 data.put("9440743785", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9789755574", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9789755574", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("KalyanRam", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("JaganMohanReddy", "9789755573", new BigDecimal(9000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount6() 
	{
		service.createAccount("JaganMohanReddy", "9789755573", new BigDecimal(-100));
	}
	
	
	@Test
	public void testCreateAccount7() 
	{
		Customer actual=service.createAccount("RajaSekhar", "9789744472", new BigDecimal(5000));
		Customer expected=new Customer("RajaSekhar", "9789744472", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testCreateAccount8() 
	{
		Customer actual=service.createAccount("Amaravathi", "9491521796", new BigDecimal(0));
		Customer expected=new Customer("Amaravathi", "9491521796", new Wallet(new BigDecimal(0)));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateAccount9() 
	{
		Customer actual=service.createAccount("Amaravathi", "9491521796", new BigDecimal(5000.75));
		Customer expected=new Customer("Amaravathi", "9491521796", new Wallet(new BigDecimal(5000.75)));
		
		assertEquals(expected, actual);
	}


	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance10() 
	{
		service.showBalance(null);		
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance11() 
	{
		service.showBalance("67891");		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance12() 
	{
		service.showBalance("9789755563");		
	}
	
	
	@Test
	public void testShowBalance13() 
	{
		Customer customer=service.showBalance("9789755573");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer14() 
	{
		service.fundTransfer("9440743785", "9440743785", new BigDecimal(5000));		
	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer15() 
	{
		service.fundTransfer("9789755573", "", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer16() 
	{
		service.fundTransfer("", "9440743785", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer17() 
	{
		Customer customer=service.fundTransfer("9789755573", "9440743785", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer18() 
	{
		Customer customer=service.fundTransfer("9789755573", "9440743785", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer19() 
	{
		service.fundTransfer("", "9440743785", new BigDecimal(-100));		
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount21() 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount22() 
	{
		service.depositAmount("", new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount23() 
	{
		service.depositAmount("9789755574", new BigDecimal(500));
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount24() 
	{
		service.depositAmount("9440743785", new BigDecimal(-1000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount25() 
	{
		service.depositAmount("9440743785", new BigDecimal(200000));
	}
	

	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount26() 
	{
		service.withdrawAmount("9789755573", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount27() 
	{
		service.withdrawAmount("9491521796", new BigDecimal(5000));	
	}

}
