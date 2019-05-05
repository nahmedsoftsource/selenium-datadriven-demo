package ai.saal.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ai.saal.pages.actions.Navigation;
import ai.saal.utilities.ExcelReader;
import ai.saal.utilities.ExtentManager;
import ai.saal.utilities.TestUtil;

public class Page {

	public static WebDriver driver;

	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports extrep = ExtentManager.getInstance();
	public static ExtentTest extTest;
	public static String browser;
	public static JavascriptExecutor js = null;
	public static Navigation nav;

	public static void initConfiguration() {
		if (driver == null) {

			if (Constants.browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\chromedriver.exe");
				driver = new ChromeDriver();

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				log.debug("Launching Chrome");

			} else if (Constants.browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				log.debug("Launching FireFox");
			} else if (Constants.browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\IEDriverServer.exe");

				driver = new InternetExplorerDriver();
				log.debug("Launching IE");
			}

			driver.get(Constants.siteurl);

			driver.manage().window().maximize();
			// driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Constants.implictWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Constants.pageLoadTimeOut, TimeUnit.SECONDS);
			nav = new Navigation(driver);
			js = (JavascriptExecutor) driver;
			wait = new WebDriverWait(driver, 5);
		}

	}

	public static void verifyEquals(String actual, String expected) throws IOException {

		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {

			Random rand = new Random();
			int randomName = rand.nextInt(100000) + 10000;
			String screenShotName = String.valueOf(randomName);
			extTest.log(LogStatus.FAIL, "verification failure " + e.getMessage());
			extTest.log(LogStatus.FAIL, extTest.addScreenCapture(TestUtil.captureScreenShot(screenShotName)));

		}

	}

	public static void verifyContains(String actual, String expected) throws IOException {
		try {
			Assert.assertTrue(actual.contains(expected));
		} catch (Throwable e) {
			Random rand = new Random();
			int randomName = rand.nextInt(100000) + 10000;
			String screenShotName = String.valueOf(randomName);

			extTest.log(LogStatus.FAIL, "verification failure " + e.getMessage());
			extTest.log(LogStatus.FAIL, extTest.addScreenCapture(TestUtil.captureScreenShot(screenShotName)));

		}

	}

	public static void clickElement(WebElement element) {

		element.click();
		// log.debug("Clicking on an Element : "+element);
		// extTest.log(LogStatus.INFO, "Clicking on : " + element);
	}

	public static void type(WebElement element, String value) {

		if (!element.getText().isEmpty()) {
			element.clear();
		}

		element.sendKeys(value);

		 log.debug("Typing in an Element : "+element+" entered value as : "+value);
		extTest.log(LogStatus.INFO, "Typing in : " + element + " entered value as " + value);

	}

	public static void selectElement(WebElement element, String value) {

		Select selectObj = new Select(element);
		selectObj.selectByValue(value);

		// log.debug("Select Element : "+element+" selected value as : "+value);
		extTest.log(LogStatus.INFO, "Selecting in : " + element + " selected value as " + value);
	}

	public static boolean elementHasClass(WebElement element, String desiredClass) {
		return element.getAttribute("class").contains(desiredClass);
	}

	public static String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}

	}

}
