package ai.saal.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import ai.saal.base.Page;



public class TestUtil extends Page {
	
	public static String captureScreenShot(String ScreenShotName) throws IOException
	{
	
	
		 
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+ScreenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
		
		/*File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String fileName = testName+".jpeg"; 
		//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+fileName));
		return fileName;*/
		
	}
	
	/*@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		String sheetName = m.getName();
		Integer rowCount = excel.getRowCount(sheetName);
		Integer colCount = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rowCount-1][colCount];
		
		for(Integer rowNum = 2; rowNum <= rowCount ; rowNum++ )
		{
			for(Integer colNum = 0 ; colNum < colCount ; colNum++)
			{
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		
		return data;
		
	}*/
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		String sheetName = m.getName();
		Integer rowCount = excel.getRowCount(sheetName);
		Integer colCount = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rowCount-1][1];
			
		Hashtable<String,String> table = null;
		for(Integer rowNum = 2; rowNum <= rowCount ; rowNum++ )
		{
			table = new Hashtable<String,String>();
			for(Integer colNum = 0 ; colNum < colCount ; colNum++)
			{
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table; //excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		
		
		return data;
		
	}
	
	
	public static boolean isRunnable(String testName, ExcelReader excel)
	{
		
		String sheetName = "suite";
		Integer rowCount = excel.getRowCount(sheetName);
		
		for(Integer rowNum = 2; rowNum <= rowCount ; rowNum++ )
		{
			
			String TID	= excel.getCellData(sheetName, "TID", rowNum);
			
			if(TID.equalsIgnoreCase(testName))
			{
				String isRunnable	= excel.getCellData(sheetName, "IsRunnable", rowNum);
				
				if(isRunnable.equalsIgnoreCase("Y"))
				{
					return true;
				}else
				{
					return false;
				}
			}
			
		}
		
		
		return false;
		
	}
	

}
