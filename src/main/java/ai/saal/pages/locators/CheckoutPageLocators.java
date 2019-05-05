package ai.saal.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageLocators {
	
	//product add to cart and checkout elements
		@FindBy(xpath="//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
		public WebElement customerProduct;
		@FindBy(xpath="//ul[contains(@class,'product_list grid row')]//li[1]//a[@title='Add to cart']")
		public WebElement customProductAddToCartBtn;
		@FindBy(name = "Submit")
		public WebElement addToCartBtn;
		@FindBy(xpath="//div[@id='layer_cart']//a[@title='Proceed to checkout']")
		public WebElement proceedToCheckoutFirst;
		@FindBy(xpath="//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
		public WebElement proceedToCheckoutSecond;
		@FindBy(name = "processAddress")
		public WebElement proceedToCheckoutThird;
		@FindBy(id = "uniform-cgv")
		public WebElement termsAgreeCheckBox;
		@FindBy(name = "processCarrier")
		public WebElement proceedToCheckoutFinal;
		@FindBy(className = "bankwire")
		public WebElement payByBankWire;
		@FindBy(xpath = "//*[@id='cart_navigation']/button")
		public WebElement confirmOrderBtn;
		@FindBy(xpath = "//h1[@class='page-heading']")
		public WebElement orderConfirmHeadingTxt;
		@FindBy(xpath = "//li[@class='step_done step_done_last four']")
		public WebElement shippingTab;
		@FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
		public WebElement paymentTab;
		@FindBy(xpath = "//p[@class='cheque-indent']/strong")
		public WebElement orderCompleteMessageTxt;
		
		
		
		
		
}
