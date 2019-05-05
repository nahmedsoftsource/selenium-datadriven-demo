package ai.saal.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import ai.saal.base.Page;
import ai.saal.utilities.TestUtil;


public class CustomListeners extends Page implements ITestListener{

	public void onTestStart(ITestResult result) {
		extTest = extrep.startTest(result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		extTest.log(LogStatus.PASS, result.getName().toUpperCase()+" PASSED");
		extrep.endTest(extTest);	
		extrep.flush();
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Capturing screenshot for "+result.getName());
		
		//extTest.log(LogStatus.FAIL, result.getName(), "No record added");
		extTest.log(LogStatus.FAIL, result.getName().toUpperCase()+" Failed with exception "+result.getThrowable());
		try {
			extTest.log(LogStatus.FAIL, extTest.addScreenCapture(TestUtil.captureScreenShot(result.getName())) );
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		try {
			String fileSrc = TestUtil.captureScreenShot(result.getName());
			System.out.println("Screenshot logs "+fileSrc);
			Reporter.log("<a href=\""+fileSrc+"\" target=\"_blank\">check screenshot</a>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extrep.endTest(extTest);	
		extrep.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		extTest.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skip with exception  "+result.getThrowable());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
