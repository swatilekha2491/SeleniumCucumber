package com.oracle.cegbu.aconex.pages.SetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.cegbu.aconex.commons.CommonMethods;
import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.utility.DriverManager;

public class NewAccountPage {
	CommonMethods common=new CommonMethods();
	PropertyHandler props= new PropertyHandler();
	
	@FindBy(xpath="//select[@id='jobFunction']")
	WebElement jobFuncDropdown;
	
	@FindBy(xpath="//div[@id='position']//input[@class='uiTextField-input']")
	WebElement jobTitleTextbox;
	
	@FindBy(id="btnSave")
	WebElement saveBtn;
	
	WebDriver driver;
	
	public NewAccountPage() {
		this.driver=DriverManager.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
	}
	
	public void enterMandatoryDetails() {
		
		common.selectFromDropdown(jobFuncDropdown,"13");
		jobTitleTextbox.sendKeys(props.getProperty("orgName"));
		saveBtn.click();
	}

}
