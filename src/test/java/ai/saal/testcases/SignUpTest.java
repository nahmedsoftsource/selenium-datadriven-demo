package ai.saal.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Hashtable;

import org.testng.annotations.Test;

import ai.saal.base.Page;
import ai.saal.pages.actions.CustomerPage;
import ai.saal.utilities.TestUtil;

public class SignUpTest extends BaseTest {

	CustomerPage CustomerPage = null;
	@Test(priority=1, dataProviderClass=TestUtil.class, dataProvider="dp" )
	public void doRegister(Hashtable<String, String> data)
	{
		CustomerPage =  Page.nav.gotoCustomerLoginPage();
		
		CustomerPage.enterEmailForRegisterAccount(data.get("Email"));
		CustomerPage.clickSubmitBtnForCreateAccount();
		String ActualPageURL = CustomerPage.registerNewCustomer(data);
		System.out.println(ActualPageURL);
		assertTrue(ActualPageURL.contains(data.get("ExpectedURL")));
		
		Hashtable<String, String> AccountData = 	CustomerPage.getAccountDetails();
		assertEquals(data.get("ExpectedText"), AccountData.get("ExpectedWelcomeName"));
		assertTrue(AccountData.get("WelcomeText").contains(data.get("ExpectedWelcomeTxt")));
		assertTrue(AccountData.get("AccountHeading").contains(data.get("ExpectedAccountHeading")));
		Page.nav.logout();
		
	
	}
	
}
