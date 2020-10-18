package com.oracle.cegbu.aconex.commons.listenrs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.oracle.cegbu.aconex.stepDefs.TestBase;

public class CustomListener implements ITestListener {

	
	
		

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test is being started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("My test is successful");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String name = result.getMethod().getMethodName();
		WebDriver driver = TestBase.getDriver();
		System.out.println("My test is failure");
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File desti = new File("D:\\"+name+".jpeg");
		
		try {
			FileUtils.copyFile(file, desti);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}
	
	
}
