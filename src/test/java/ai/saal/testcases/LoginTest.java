package ai.saal.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Hashtable;

import org.testng.annotations.Test;

import ai.saal.base.Page;
import ai.saal.pages.actions.CustomerPage;
import ai.saal.utilities.TestUtil;

public class LoginTest extends BaseTest {

	CustomerPage CustomerPage = null;
	@Test(priority=0, dataProviderClass=TestUtil.class, dataProvider="dp" )
	public void doLogin(Hashtable<String, String> data)
	{
		CustomerPage =  Page.nav.gotoCustomerLoginPage();
		String AccountPageURL = CustomerPage.customerLogin(data.get("Email"), data.get("Password"));
		assertTrue(AccountPageURL.contains(data.get("ExpectedURL")));
		Hashtable<String, String> AccountData = 	CustomerPage.getAccountDetails();
		assertEquals(data.get("ExpectedText"), AccountData.get("AccountName"));
		assertTrue(AccountData.get("WelcomeText").contains(data.get("ExpectedWelcomeTxt")));
		assertTrue(AccountData.get("AccountHeading").contains(data.get("ExpectedAccountHeading")));
		Page.nav.logout();
		
	
	}
}
