package com.oracle.cegbu.aconex.pages.Document;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
//import  java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.oracle.cegbu.aconex.commons.CommonMethods;
import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.utility.DriverManager;

public class DocumentRegisterPage {
	
	WebDriver driver;
	CommonMethods common= new CommonMethods();
	UploadDocumentPage upload= new UploadDocumentPage();
	PropertyHandler props= new PropertyHandler();
	
    public DocumentRegisterPage(){ 
    	this.driver=DriverManager.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
    }
    
    @FindBy(xpath="//div[text()='Documents']") 
    WebElement documentLink;
    
    @FindBy(xpath="//div[@class='navBarPanel']//descendant::div[@id='nav-bar-DOC-DOC-SEARCH']") 
    WebElement documentRegisterLink;
    
    @FindBy(xpath="//input[@id='search-keywords-id']") 
    WebElement searchTextBox;
    
    @FindBy(xpath="//div[@col-id='docno']") 
    List<WebElement> docsTable;
    
    
    public void clickDocumentLink() {
    	documentLink.click();
    }
    public void clickDocumentRegisterLink() {
    	documentRegisterLink.click();
    	driver.switchTo().frame(driver.findElement(By.id(props.getProperty("MainFrame_ID"))));
    	common.waitForElement(driver,searchTextBox,2000);
    }
    
    public void enterStringInSearchTextbox() {
    	searchTextBox.sendKeys(props.getProperty("docNumber"));
    	searchTextBox.click();
    	
    	
    }
    
    public List<WebElement> getDocumentTable() {
    	
    	return docsTable;
    }
    
    public void Test() throws MalformedURLException, IOException, AWTException, ClassNotFoundException {
    	
    	List<WebElement> list=driver.findElements(By.tagName("a"));
    	Iterator<WebElement> it=list.iterator();
    	while(it.hasNext())
    	{
    		String url=it.next().getAttribute("href");
    		if(url==null || url.isEmpty())
    		{
    			continue;
    		}
    		HttpURLConnection conn= (HttpURLConnection)(new URL(url).openConnection());
    		conn.setRequestMethod("HEAD");
    		conn.connect();
    		
    		if(conn.getResponseCode()>=400)
    		{
    			System.out.println("error");
    		}
    		else
    		{
    			System.out.println("Valid Link");
    		}
    	}
    	
    	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	Wait wait=new FluentWait(driver).withTimeout(Duration.ofSeconds(40)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
    	
    	WebDriverWait wait1=new WebDriverWait(driver,40);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("docsTable")));
    	
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click",docsTable);
    	js.executeScript("arguments[0].scrollIntoView", searchTextBox);
    	 
    	Robot rob=new Robot();
    	rob.keyPress(KeyEvent.VK_CONTROL);
    	rob.keyPress(KeyEvent.VK_END);
    	rob.keyRelease(KeyEvent.VK_CONTROL);
    	rob.keyRelease(KeyEvent.VK_END);
    	
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	File srcFile=ts.getScreenshotAs(OutputType.FILE);
    	File destFile=new File("//");
    	FileUtils.copyFile(srcFile, destFile);
    	
    	//WebDriverEventListener interface , EventFiringWebDriver class 
    	
    	DesiredCapabilities cap=DesiredCapabilities.chrome();
    	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
    	System.setProperty("webdriver.chrome.driver", "./chromedriver");
    	WebDriver driver=new ChromeDriver(cap);
    	
    	Proxy p=new Proxy();
    	p.setHttpProxy("localhost:7777");
    	DesiredCapabilities cap1=DesiredCapabilities.chrome();
    	cap1.setCapability(CapabilityType.PROXY, p);
    	WebDriver dr=new FirefoxDriver(cap1);
    	
    	Actions act=new Actions(driver);
    	act.contextClick(documentLink).perform();
    	act.dragAndDrop(documentRegisterLink, documentLink).perform();
    	
    	act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
    	act.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
    	
    	act.sendKeys(Keys.TAB).perform();
    	
    	Properties obj=new Properties();
    	FileInputStream fis=new FileInputStream("path");
    	obj.load(fis);
    	driver.findElement(By.xpath(obj.getProperty("key"))).click();
    	
    	ChromeOptions opt=new ChromeOptions();
    	opt.addArguments("headless");
    	WebDriver dr1=new ChromeDriver(opt);
    	dr1.get("url");
    	
    	JavascriptExecutor js1=(JavascriptExecutor)driver;
    	js1.executeScript("arguments[0].setAttribute('style','background:yellow;border:3px red')",driver.findElement(By.id("id")));
    	
    	//Tooltip
    	
    	Actions act1=new Actions(driver);
    	
    	act1.moveToElement(documentLink).perform();
    	driver.findElement(By.id("id")).getText();
    	
    	StringSelection str=new StringSelection("path");
    	Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
    	clip.setContents(str, null);
    	
    	//Download file
    	Robot robo=new Robot();
    	robo.keyPress(KeyEvent.VK_TAB);
    	robo.keyRelease(KeyEvent.VK_TAB);
    	
    	robo.keyPress(KeyEvent.VK_ENTER);
    	robo.keyRelease(KeyEvent.VK_ENTER);
    	
    	//Load JDBC driver
		/*
		 * Class.forName("com.oracle.jdbc.Driver");
		 * 
		 * 
		 * String dbUrl="jdbc:oracle://localhost:3036/mydb"; String username="aconex";
		 * String password="aconex"; String query="select * from employee;"; try {
		 * //Create connection to database Connection
		 * con=DriverManager.getConnection(dbUrl,username,password); //Create Statement
		 * Object Statement state=con.createStatement(); //Execute SQL query. Store
		 * results in ResultSet ResultSet rs=state.executeQuery(query); while(rs.next())
		 * { System.out.println(rs.getString(1)); } con.close(); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
    	
    	
    	
    	
    }
    

}
