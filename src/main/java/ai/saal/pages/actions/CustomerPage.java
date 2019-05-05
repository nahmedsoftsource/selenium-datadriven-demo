package ai.saal.pages.actions;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import ai.saal.base.Page;
import ai.saal.pages.locators.CustomerLocators;

public class CustomerPage extends Page  {
	
public CustomerLocators CustomerLocators;
	
	public CustomerPage()
	{
		this.CustomerLocators= new CustomerLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this.CustomerLocators);
	}
	
	public String customerLogin(String email, String password)
	{
		CustomerLocators.email.sendKeys(email);
		CustomerLocators.passwd.sendKeys(password);
		CustomerLocators.SubmitLogin.click();
		/*type(CustomerLocators.email, email);
		type(CustomerLocators.passwd, password);
		clickElement(CustomerLocators.SubmitLogin);*/
		return Page.getCurrentPageURL();
		
	}
	
	public Hashtable<String, String>  getAccountDetails()
	{
		Hashtable<String,String> ReturnValues = new Hashtable<String,String>();
		String AccountHeading = Page.wait.until(ExpectedConditions.visibilityOf(CustomerLocators.accountHeading)).getText();
		String AccountName = CustomerLocators.acocuntTxt.getText();
		String WelcomeText = CustomerLocators.acocuntWelcomeTxt.getText();
		
		ReturnValues.put("AccountHeading", AccountHeading);
		ReturnValues.put("AccountName", AccountName);
		ReturnValues.put("WelcomeText", WelcomeText);
		
		return ReturnValues;
	}
	
	
	public void enterEmailForRegisterAccount(String Email)
	{
		CustomerLocators.emailAddress.sendKeys(Email);
	}
	
	public void clickSubmitBtnForCreateAccount()
	{
		CustomerLocators.submitCreateBtn.click();
	}
	
	public  String registerNewCustomer(Hashtable<String, String> data)
	{
		String Title = data.get("Title");
		if(Title.equalsIgnoreCase("Mr"))
		{
			CustomerLocators.mr.click();
		}else
		{
			CustomerLocators.miss.click();
		}
			
		CustomerLocators.firstName.sendKeys(data.get("FirstName"));
		CustomerLocators.lastName.sendKeys(data.get("LastName"));
		CustomerLocators.accountPasswd.sendKeys(data.get("Password"));
		String [] DOB = data.get("DOB").split("-");
		Select Days = new Select(CustomerLocators.days);
		Days.selectByValue(DOB[0]);
		Select Months = new Select(CustomerLocators.months);
		Months.selectByValue(DOB[1]);
		Select Year = new Select(CustomerLocators.years);
		Year.selectByValue(DOB[2]);
		
		CustomerLocators.company.sendKeys(data.get("Company"));
		CustomerLocators.address1.sendKeys(data.get("Address1"));
		CustomerLocators.address2.sendKeys(data.get("Address2"));
		CustomerLocators.city.sendKeys(data.get("City"));
		Select State = new Select(CustomerLocators.state);
		State.selectByVisibleText(data.get("State"));
		CustomerLocators.postcode.sendKeys(data.get("ZipCode"));
		CustomerLocators.other.sendKeys(data.get("AdditionatInfo"));
		CustomerLocators.phone.sendKeys(data.get("HomePhone"));
		CustomerLocators.mobileNumber.sendKeys(data.get("MobilePhone"));
		CustomerLocators.alias.clear();
		CustomerLocators.alias.sendKeys(data.get("AssignAddress_Alis"));
		CustomerLocators.registerAccountBtn.click();
		return Page.getCurrentPageURL();
	}
	
}
