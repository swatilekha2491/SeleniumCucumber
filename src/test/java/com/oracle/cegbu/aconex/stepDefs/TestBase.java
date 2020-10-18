package com.oracle.cegbu.aconex.stepDefs;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebElement;

import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.commons.listenrs.CustomListener;

@Listeners(CustomListener.class)
public class TestBase {


	public static WebDriver driver;
	static WebElement dropdown=null;
	static PropertyHandler props ;
	DesiredCapabilities capabilities;
	
	public static WebDriver getDriver() {
		return driver;
	}
	
//	@BeforeSuite
	public void beforeSuite() {
		props = new PropertyHandler();
	}
	
	public String url = "";
	public String browser = "";
	
//	@BeforeSuite
	public void launchBrowser() {

		browser = props.getProperty("browser");
		url  = props.getProperty("url");
		
		this.capabilities=new DesiredCapabilities();
		this.capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		this.capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		if(browser.equals("chrome")) {
			
			ChromeOptions co= new ChromeOptions();
			co.merge(capabilities);
			System.setProperty("webdriver.chrome.driver", "D:\\jars\\chromedriver.exe");
			driver = new ChromeDriver(co);
			driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(50,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
		}else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\jars\\geckodriver.exe");
		//	FirefoxProfile profile=new FirefoxProfile();
		//	profile.setAcceptUntrustedCertificates(true);
		//	FirefoxProfile ffprofile = new FirefoxProfile(new File("D:\\Selenium"));
		//	FirefoxOptions fo=new FirefoxOptions(capabilities);
		//	fo.setProfile(ffprofile);
		//	driver=new FirefoxDriver(fo);
		//	driver = new FirefoxDriver();
		}
		
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
//	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
	public void login(String username,String password) {
		driver.findElement(By.id("userName")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	}
    public boolean isElementPresent(By by) {
		
		try {
		driver.findElement(by);
		return true;
		}	
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	public void select(String location,String text) {
		
		if(location.endsWith("CSS"))
		{
		dropdown=driver.findElement(By.cssSelector(props.getProperty(location)));
		Select sel=new Select(dropdown);
		List<WebElement> allOptions = sel.getOptions();
		
		for(WebElement i:allOptions) {
		if(i.getText().equals(text)) {	
		sel.selectByVisibleText(text);
		}
		}
		
		}else if(location.endsWith("XPATH")){
			
		dropdown=driver.findElement(By.xpath(props.getProperty(location)));
		Select sel=new Select(dropdown);
		List<WebElement> allOptions = sel.getOptions();
		
		for(WebElement i:allOptions) {
		if(i.getText().equals(text)) {	
		sel.selectByVisibleText(text);
		}
		}
		
		}else if(location.endsWith("ID")){
			
		dropdown=driver.findElement(By.id(props.getProperty(location)));
		Select sel=new Select(dropdown);
		List<WebElement> allOptions = sel.getOptions();
		
		for(WebElement i:allOptions) {
		if(i.getText().equals(text))
		{
		System.out.println(i.getText());	
		sel.selectByVisibleText(text);
		}
		}
		}
	}
	
	public void click(String location) {
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		if(location.endsWith("CSS"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(props.getProperty(location)))).click();
		}else if(location.endsWith("XPATH")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty(location)))).click();
		}else if(location.endsWith("ID")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(props.getProperty(location)))).click();
		}
	}
	
	public void sendKeys(String location,String value) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		if(location.endsWith("CSS"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(props.getProperty(location)))).sendKeys(value);
		}else if(location.endsWith("XPATH")) {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty(location)))).sendKeys(value);
		}else if(location.endsWith("ID")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(props.getProperty(location)))).sendKeys(value);
		}
	}
	
	public int getRows(String toGetRows) {
		
		List<WebElement> rows = driver.findElements(By.xpath(props.getProperty(toGetRows)));
		return rows.size();
	    
	}
    public int getColumns(String toGetColumns) {
		
		List<WebElement> columns = driver.findElements(By.xpath(props.getProperty(toGetColumns)));
		return columns.size();
	    
	}
   
}

