package com.oracle.cegbu.aconex.stepDefs;

import org.testng.Assert;

import com.oracle.cegbu.aconex.pages.Document.Login;
import com.oracle.cegbu.aconex.pages.SetUp.OrganizationRegistration;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TestRegister {
	
	Testlogin testLogin= new Testlogin();
	Login login=new Login();
	OrganizationRegistration register= new OrganizationRegistration();
	
	@Given("User is on Login Page")
	public void user_is_on_Login_Page() {
		testLogin.user_is_on_the_Login_Page();
	}
	@Given("Click Register")
	public void click_Register() {
		
	    login.clickRegister();
	}

	@Given("User should enter details")
	public void user_should_enter_details() {
		register.enterDetails();
		register.clickRegisterBtn(); 
	}

	@Then("User should land on Successful Registration page")
	public void user_should_land_on_Successful_Registration_page() throws InterruptedException {
	    
		Assert.assertTrue(register.getSuccessMessage().contentEquals("Thank you, your registration is now being processed."));
		Thread.sleep(1000);
	}
}
