package com.oracle.cegbu.aconex.pages.Document;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.utility.DriverManager;

public class Login {

	WebDriver driver;
	 PropertyHandler props= new PropertyHandler();
	 WebDriverWait wait;
	
	public Login() {
		this.driver=DriverManager.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
	//	wait=new WebDriverWait(DriverManager.getInstance().getWebDriver(), 30);
	}
	
	@FindBy(id="userName")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(xpath="//span[@class='nav-user']")
	WebElement loggedUserNameTab;
	
	@FindBy(linkText="Logout")
	WebElement logoutLink;
	
	@FindBy(linkText="Register")
	WebElement register;
	
	public String getTitle() {
		
		return driver.getTitle();
	}
	
	public void  enterUsernamePassword()
	{
		username.sendKeys(props.getProperty("username"));
		password.sendKeys(props.getProperty("password"));
	}
	
	public void  enterAdminUsernamePassword()
	{
		username.sendKeys(props.getProperty("adminuser"));
		password.sendKeys(props.getProperty("adminpassword"));
	}
	public void clickLogin()
	{
		loginBtn.click();
		
	}
	
	  public WebElement getUserDetails() 
	  { 
		  return loggedUserNameTab;
		  
	  }
	 
	
	public void clickLogout() {
		
		logoutLink.click();
		
	}
	
	public void clickRegister() {
		
		register.click();
	}

	public void waitForPageLoad(String string) {
		if(string.equalsIgnoreCase("Homepage"))
			wait=new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.titleContains("Aconex"));
		
	}
	
}
