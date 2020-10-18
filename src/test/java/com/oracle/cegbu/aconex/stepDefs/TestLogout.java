package com.oracle.cegbu.aconex.stepDefs;

import org.testng.Assert;

import com.oracle.cegbu.aconex.pages.Document.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TestLogout {
	Login login=new Login();
	
	@Given("User is on Homepage")
	public void user_is_on_Homepage() {
		Assert.assertTrue(login.getTitle().contentEquals("Aconex"));
	}

	@Given("Click Logout")
	public void click_Logout() {
		
		login.getUserDetails().click();
		login.clickLogout();
	}

	@Then("User should land on Logout Page")
	public void user_should_land_on_Logout_Page() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue(login.getTitle().contentEquals("Log off"));
	}
	
	
	@Given("User should logout of the application")
	public void user_should_logout_of_the_application() throws Exception {
		user_is_on_Homepage();
		click_Logout();
		user_should_land_on_Logout_Page();
	}

}
