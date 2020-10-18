package com.oracle.cegbu.aconex.pages.SetUp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.oracle.cegbu.aconex.commons.CommonMethods;
import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.pages.Document.Login;
import com.oracle.cegbu.aconex.utility.DriverManager;

public class SetUpPage {
	
	WebDriver driver;
	WebDriverWait wait;
	OrganizationRegistration reg=new OrganizationRegistration();
	PropertyHandler props= new PropertyHandler();
	CommonMethods common= new CommonMethods();
	Login log=new Login();

	public SetUpPage() {
		this.driver=DriverManager.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
		wait=new WebDriverWait(this.driver, 25);
	}
	
	@FindBy(xpath="//*[contains(text(),'Setup')]")
	WebElement setUpBtn;
	
	@FindBy(id="nav-bar-SETUP-SETUP-ADMINSEARCH")
	WebElement searchLink;
	
	@FindBy(xpath="//input[@value='User']")
	WebElement userChkBox;
	
	@FindBy(xpath="//button[@title='Search']")
	WebElement searchButton;
	
	@FindBy(xpath="//div[@id='searchResultsWrapper']//table[@class='formTable']")
	WebElement searchResult;
	
	@FindBy(xpath="//tbody[@id='generalTabContent']//div[1]")
	WebElement accSecurityChkBoxes;
	
	@FindBy(xpath="//input[@name='SRCH_KEYWORDS']")
	WebElement searchTextbox;
	
	@FindBy(xpath="//a[@title='Edit user information' ]")
	List<WebElement> userLinks;
	
	@FindBy(xpath="//input[@name='USER_LOCKED']")
	WebElement accLocked;
	
	@FindBy(xpath="//input[@name='USER_DISABLED']")
	WebElement accDisabled;
	
	@FindBy(xpath="//div[contains(text(),'Save')]")
	WebElement saveButton;
	
	@FindBy(xpath="//li[@class='message success']//div//descendant::div")
	WebElement userMessage;
	
	public void unlockUser() throws Exception {
		setUpBtn.click();
		searchLink.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='frameMain']")));
		searchTextbox.sendKeys(props.getProperty("username"));
		userChkBox.click();
		searchButton.click();
		for(WebElement link:userLinks)
		{
			if(link.getText().contains(props.getProperty("username")))
			{
				link.click();
				break;
			}
		}
		if(accLocked.isSelected())
		accLocked.click();
		if(accDisabled.isSelected())
		accDisabled.click();
		saveButton.click();
		
		}
	public String getUserMessage() {
		common.waitForElementVisibility(driver, userMessage, 10);
		return userMessage.getText();
	}
	
	
}
