package ai.saal.pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ai.saal.base.Page;
import ai.saal.pages.locators.CheckoutPageLocators;

public class CheckoutPage extends Page  {
	
		
	CheckoutPageLocators CheckoutPageLocators = null;
	public CheckoutPage()
	{
		this.CheckoutPageLocators= new CheckoutPageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this.CheckoutPageLocators);
	}
	
	
	public void doCustomerCheckout() {
		
		new WebDriverWait(Page.driver, 180).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) Page.js.executeScript("return document.readyState").equals("complete");
			}
		});
		
		
		Actions actions = new Actions(Page.driver);
		actions.moveToElement(CheckoutPageLocators.customerProduct).perform();
		Page.wait.until(ExpectedConditions.elementToBeClickable(CheckoutPageLocators.customProductAddToCartBtn)).click();
		
		
		Page.wait.until(ExpectedConditions.elementToBeClickable(CheckoutPageLocators.proceedToCheckoutFirst)).click();
		
		CheckoutPageLocators.proceedToCheckoutSecond.click();
		CheckoutPageLocators.proceedToCheckoutThird.click();
		CheckoutPageLocators.termsAgreeCheckBox.click();
		CheckoutPageLocators.proceedToCheckoutFinal.click();
		CheckoutPageLocators.payByBankWire.click();
		CheckoutPageLocators.confirmOrderBtn.click();
		
	}
	
	public boolean checkIfShippingTabIsDisplayed()
	{
		return CheckoutPageLocators.shippingTab.isDisplayed();
	}
	
	public boolean checkIfPaymentTabIsDisplayed()
	{
		return CheckoutPageLocators.paymentTab.isDisplayed();
	}
	
	public String getOrderConfirmMessage()
	{
		return CheckoutPageLocators.orderCompleteMessageTxt.getText(); 
	}
	
	public String getOrderConfirmPageHeadingTxt()
	{
		return CheckoutPageLocators.orderConfirmHeadingTxt.getText(); 
	}
	
	
	
}
