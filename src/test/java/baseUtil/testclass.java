package baseUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import library.utility;

public class testclass {
	public WebDriver driver;

	@BeforeTest
	public void Launch() {
		System.setProperty(commons.cKey, commons.cPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void Kill(ITestResult result) throws IOException {
		if(ITestResult.FAILURE== result.getStatus() ) {
			utility.captureScreenshot(driver, result.getName());
		}
		
	}
	@AfterTest
	public void qi() {
		driver.quit();
	}
}