package com.oracle.cegbu.aconex.pages.SetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.cegbu.aconex.commons.CommonMethods;
import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.utility.DriverManager;

public class OrganizationRegistration {

	WebDriver driver;
	 PropertyHandler props= new PropertyHandler();
	 CommonMethods common= new CommonMethods();
	 
	
	public OrganizationRegistration() {
		this.driver=DriverManager.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath="//input[@name='ORG_NAME']")
	WebElement orgName;
	
	@FindBy(xpath="//input[@name='POSTAL_ADDRESS1']")
	WebElement address;
	
	@FindBy(xpath="//input[@name='POSTAL_SUBURB']")
	WebElement city;
	
	@FindBy(xpath="//input[@name='POSTAL_STATE']")
	WebElement county;
	
	@FindBy(xpath="//select[@name='POSTAL_COUNTRY']")
	WebElement country;
	
	@FindBy(xpath="//input[@name='POSTAL_POSTCODE']")
	WebElement postcode;
	
	@FindBy(xpath="//input[@id='trading-name']")
	WebElement tradingName;
	
	@FindBy(xpath="//input[@id='organization-code']")
	WebElement organizationAbbreviation;
	
	@FindBy(xpath="//input[@name='USER_FIRST_NAME']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@name='USER_LAST_NAME']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@name='EMAIL']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='USER_PHONE']")
	WebElement phone;
	
	@FindBy(xpath="//input[@name='USER_NAME']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@name='passwordConfirm']")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='acceptTermsOfService']")
	WebElement acceptCheckbox;
	
	@FindBy(xpath="//button[@class='auiButton primary']")
	WebElement registerButton;
	
	@FindBy(xpath="//div[@class=\"acxOrgRegistrationSuccess-content\"]//p[1]")
	WebElement successMsg;
	public void enterDetails()
	{
		
		
		orgName.sendKeys(props.getProperty("orgName"));
		address.sendKeys(props.getProperty("orgName"));
		city.sendKeys(props.getProperty("orgName"));
		county.sendKeys(props.getProperty("orgName"));
		common.selectFromDropdown(country, "India");
		postcode.sendKeys(props.getProperty("postalCode"));
		tradingName.clear();
		tradingName.sendKeys(props.getProperty("orgName"));
		organizationAbbreviation.clear();
		organizationAbbreviation.sendKeys(createLoginName());
		
		
		firstName.sendKeys(props.getProperty("orgName"));
		lastName.sendKeys(props.getProperty("orgName"));
		
		email.sendKeys(props.getProperty("email"));
		phone.sendKeys(props.getProperty("postalCode"));
		username.sendKeys(props.getProperty("orgName"));
		password.sendKeys(props.getProperty("password"));
		confirmPassword.sendKeys(props.getProperty("password"));
		common.waitForElement(driver,acceptCheckbox,1000);
		acceptCheckbox.click();
		
	}
	
	public String getSuccessMessage()
	{
		common.waitForElementVisibility(this.driver,successMsg,20);
		System.out.println(successMsg.getText());
		return successMsg.getText();
	}
	
	public void clickRegisterBtn()
	{
		common.waitForElement(driver,registerButton,1000);
		registerButton.click();
	}
	
	public String getLoginName()
	{
		return organizationAbbreviation.getText(); 
	}
	
	public String createLoginName()
	{
		String str=props.getProperty("orgName");
		String substring = str.length() > 2 ? str.substring(str.length() - 2) : str;
		return (str.substring(0,5)+substring);
	}
	
}
