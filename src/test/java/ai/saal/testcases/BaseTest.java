package ai.saal.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ai.saal.base.Page;

public class BaseTest {
	
	
	@BeforeSuite
	public void setup()
	{
		Page.initConfiguration();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException
	{
		//Page.extrep.flush();
		//Page.extrep.close();
		Page.driver.close();
		Thread.sleep(4000);
		Page.quitBrowser();
	}

}
