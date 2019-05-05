package ai.saal.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerLocators {
	
	// General login menu button
		@FindBy(className = "login")
		public WebElement loginBtn;

		// Customer's login form elements
		@FindBy(id = "email")
		public WebElement email;
		@FindBy(id = "passwd")
		public WebElement passwd;
		@FindBy(id = "SubmitLogin")
		public WebElement SubmitLogin;
		

		// Customer's sign up form elements
		@FindBy(id = "id_gender1")
		public WebElement mr;
		@FindBy(id = "id_gender2")
		public WebElement miss;
		@FindBy(id = "email_create")
		public WebElement emailAddress;
		@FindBy(id = "customer_firstname")
		public WebElement firstName;
		@FindBy(id = "customer_lastname")
		public WebElement lastName;
		@FindBy(id = "SubmitCreate")
		public WebElement submitCreateBtn;
		@FindBy(id = "passwd")
		public WebElement accountPasswd;
		@FindBy(id = "days")
		public WebElement days;
		@FindBy(id = "months")
		public WebElement months;
		@FindBy(id = "years")
		public WebElement years;
		@FindBy(id = "company")
		public WebElement company;
		@FindBy(id = "address1")
		public WebElement address1;
		@FindBy(id = "address2")
		public WebElement address2;
		@FindBy(id = "city")
		public WebElement city;
		@FindBy(id = "id_state")
		public WebElement state;
		@FindBy(id = "postcode")
		public WebElement postcode;
		@FindBy(id = "other")
		public WebElement other;
		@FindBy(id = "phone")
		public WebElement phone;
		@FindBy(id = "phone_mobile")
		public WebElement mobileNumber;
		@FindBy(id = "alias")
		public WebElement alias;
		@FindBy(id = "submitAccount")
		public WebElement registerAccountBtn;
		
		
		//Home page error message elements 
		@FindBy(xpath="//div[contains(@class,'alert-danger')]//ol/li")
		public WebElement loginErrorMsg;
		
		// Customer's logged in account page elements
		@FindBy(xpath = "//h1[@class='page-heading']")
		public WebElement accountHeading;
		@FindBy(className = "account")
		public WebElement acocuntTxt;
		@FindBy(className = "info-account")
		public WebElement acocuntWelcomeTxt;
		@FindBy(className = "logout")
		public WebElement logoutBtn;
		
}
