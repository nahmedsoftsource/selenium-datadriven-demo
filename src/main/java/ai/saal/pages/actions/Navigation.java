package ai.saal.pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ai.saal.base.Page;
import ai.saal.pages.locators.NavigationLocators;



public class Navigation {

	public NavigationLocators NavigationLocators ;
	public Navigation(WebDriver driver){
		
		this.NavigationLocators = new NavigationLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this.NavigationLocators);
	}
	
	public CustomerPage gotoCustomerLoginPage()
	{
		Page.wait.until(ExpectedConditions.visibilityOf(NavigationLocators.loginBtn)).click();
		NavigationLocators.loginBtn.click();
		return new CustomerPage();
	}
	
	public void gotoWomenProductsPage()
	{
	
		new WebDriverWait(Page.driver, 180).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) Page.js.executeScript("return document.readyState").equals("complete");
			}
		});
		
		NavigationLocators.womenProductMenu.click();
		
	}
	

	
	public  boolean elementHasClass(WebElement element, String desiredClass) {
		 return element.getAttribute("class").contains(desiredClass);
	}
	
	public boolean checkIfLogoutBtnIsDisplayed()
	{
		return NavigationLocators.logout.isDisplayed();
	}
	
	public void logout()
	{
		if(NavigationLocators.logout.isDisplayed())
		{
			NavigationLocators.logout.click();
		}
		 
	}
	
	
	
}
