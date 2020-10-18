package com.oracle.cegbu.aconex.pages.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.utility.DriverManager;
import com.oracle.cegbu.aconex.commons.CommonMethods;

public class UploadDocumentPage {
	
	WebDriver driver;
	 PropertyHandler props= new PropertyHandler();
	 CommonMethods common= new CommonMethods();
	 WebDriverWait wait;
	
	
	public UploadDocumentPage() {
		this.driver=DriverManager.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath="//div[@class='uiButton-label'][text()='Documents']")
	WebElement documentTab;
	
	@FindBy(xpath="//div[@class='navBarPanel']//descendant::div[@id='nav-bar-DOC-DOC-NEW']")
	WebElement docUploadLink;
	
	@FindBy(xpath="//input[@id='docno']")
	WebElement docNumberTxtbox;
	
	@FindBy(xpath="//input[@name='revision']")
	WebElement revisionTxtbox	;
	
	@FindBy(xpath="//select[@id='doctype']")
	WebElement docTypeDropdown;
	
	@FindBy(xpath="//input[@name='title']")
	WebElement titleTxtBox;
	
	@FindBy(xpath="//span[@id='clickToUpload']")
	WebElement computerSourceLink;
	
	@FindBy(xpath="//div[@class='uiMenu-label' and text()='Upload file from Dropbox']")
	WebElement dropboxLink;
	
	@FindBy(xpath="//div[@class='uiMenu-label' and text()='Upload file from Box']")
	WebElement boxLink;
	
	@FindBy(xpath="//div[@id='revisiondate_0-dateField' ]//descendant::div [@class='bicon ic-calendar']")
	WebElement revisionDateCalender;
	
	@FindBy(xpath="//input[@id='revisiondate']")
	WebElement revisionDateTextBox;
	
	@FindBy(xpath="//div[@id='comments_0']")
	WebElement commentsTextBox;
	
	@FindBy(xpath="//button[@class='auiButton primary ng-binding']")
	WebElement registerButton;
	
	@FindBy(xpath="//div[@class='auiModal-title ng-binding']")
	WebElement successTextBox;
	
	@FindBy(xpath="//div[@class='auiModal-close']")
	WebElement successCloseButton;
	 
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	public void uploadDocument() throws InterruptedException {
		Actions action = new Actions(driver);
		wait = new WebDriverWait(driver,30);
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate= formatter.format(date);
		
		documentTab.click(); 
		docUploadLink.click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(props.getProperty("MainFrame_ID"))));
		common.waitForElement(driver, docTypeDropdown, 20);
	//    wait.until(ExpectedConditions.visibilityOf(docTypeDropdown)); 
		common.selectFromDropdown(docTypeDropdown,props.getProperty("docType"));
		docNumberTxtbox.sendKeys(props.getProperty("docNumber"));
		revisionTxtbox.sendKeys(props.getProperty("docNumber"));
		titleTxtBox.sendKeys(props.getProperty("docNumber"));
		revisionDateTextBox.click();
		revisionDateTextBox.sendKeys(strDate);
		computerSourceLink.click();
		common.uploadFile(props.getProperty("uploadFilelink")); 
		registerButton.click();
		}
	
	public String getSuccessMessage() {
		
		Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
        Iterator<String> itererator = windowId.iterator();   

        String mainWinID = itererator.next();

        String message= successTextBox.getText();
        System.out.println(message);
        successCloseButton.click();
        driver.switchTo().defaultContent();
        return message;
	}
		

}
