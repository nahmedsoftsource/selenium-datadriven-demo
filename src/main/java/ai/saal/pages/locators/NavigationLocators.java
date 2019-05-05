package ai.saal.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationLocators {

	// general login menu and logout button
		@FindBy(className = "login")
		public WebElement loginBtn;
		@FindBy(className="logout")
		public WebElement logout;
		
		//top menu
		@FindBy(xpath="//div[@id='block_top_menu']//a[@title='Women' and contains(text(),'Women')]")
		public WebElement womenProductMenu;
		
}
