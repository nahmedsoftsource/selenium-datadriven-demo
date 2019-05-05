package ai.saal.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Hashtable;

import org.testng.annotations.Test;

import ai.saal.base.Page;
import ai.saal.pages.actions.CheckoutPage;
import ai.saal.pages.actions.CustomerPage;
import ai.saal.utilities.TestUtil;

public class CustomerCheckoutTest  extends BaseTest{

	CustomerPage CustomerPage = null;
	CheckoutPage CheckoutPage  = null;
	@Test(priority=2, dataProviderClass=TestUtil.class, dataProvider="dp" )
	public void doCheckout(Hashtable<String, String> data)
	{
		Page.initConfiguration();
		
		CustomerPage =  Page.nav.gotoCustomerLoginPage();
		CheckoutPage = new CheckoutPage();
		String AccountPageURL = CustomerPage.customerLogin(data.get("Email"), data.get("Password"));
		assertTrue(AccountPageURL.contains(data.get("ExpectedURL")));
		Hashtable<String, String> AccountData = 	CustomerPage.getAccountDetails();
		assertEquals(data.get("ExpectedText"), AccountData.get("AccountName"));
		assertTrue(AccountData.get("WelcomeText").contains(data.get("ExpectedWelcomeTxt")));
		assertTrue(AccountData.get("AccountHeading").contains(data.get("ExpectedAccountHeading")));
		
		Page.nav.gotoWomenProductsPage();
		CheckoutPage.doCustomerCheckout();
		String ActualURl = Page.getCurrentPageURL();
		assertTrue(ActualURl.contains(data.get("OrderConfirmExpectedURL")));
		
		String ActualHeadingTxt = CheckoutPage.getOrderConfirmPageHeadingTxt();
		assertTrue(ActualHeadingTxt.contains(data.get("ExpectedPageTitle")));
		assertTrue(CheckoutPage.checkIfShippingTabIsDisplayed());
		assertTrue(CheckoutPage.checkIfPaymentTabIsDisplayed());
		String ActualOrderCompleteTxt = CheckoutPage.getOrderConfirmMessage();
		assertTrue(ActualOrderCompleteTxt.contains(data.get("ExpectedMsg")));
		
		Page.nav.logout();
		
		
		
	}
	
}
