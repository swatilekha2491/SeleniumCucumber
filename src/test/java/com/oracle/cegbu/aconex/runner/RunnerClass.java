package com.oracle.cegbu.aconex.runner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import com.oracle.cegbu.aconex.commons.CommonMethods;
import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.pages.Document.Login;
import com.oracle.cegbu.aconex.stepDefs.TestLogout;
import com.oracle.cegbu.aconex.utility.DriverManager;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		features= {CucumberVariables.features},
		glue = {CucumberVariables.glue,"com.oracle.cegbu.aconex.runner"}, 
		monochrome = CucumberVariables.monochrome,
		strict=CucumberVariables.strict,
		dryRun = CucumberVariables.dryrun,
		plugin  = {"pretty","html:target/reports/htmlreport","json:target/reports/jsonreport/index.json",
				"junit:target/reports/xmlreport.xml"},
		tags={CucumberVariables.tags}
		)


public class RunnerClass extends AbstractTestNGCucumberTests{
  
  String browser;
  String url;	
  WebDriver driver;
  PropertyHandler props;
  Login log=new Login();
	
	/*
	 * @BeforeSuite public void beforeSuite() {
	 * 
	 * }
	 */
	 
  
  
	@Before
	public void start() throws Exception
	{
		props=new PropertyHandler();
		if(!CucumberVariables.dryrun)
		{
		browser = props.getProperty("browser");
		url  = props.getProperty("url");
		
		if(browser.equals("chrome")) {
			
			DesiredCapabilities capabilities=DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			
			ChromeOptions co= new ChromeOptions();
			co.merge(capabilities);
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver(co);
			
			
		}else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\jars\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		DriverManager.getInstance().setWebDriver(driver);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(50,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}
	}
	
	@AfterStep
	public void afterStep(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			//Take Screenshot
			CommonMethods.takeScreenshot(scenario.getName());
		}
	}

	
		@After
		public void shutDown() throws Exception
		{
			
		driver.switchTo().defaultContent();
		try {
			
		  if(log.getUserDetails().isDisplayed())
		  new TestLogout().user_should_logout_of_the_application();
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
			driver.quit();
		}

}
