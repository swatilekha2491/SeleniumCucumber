package com.oracle.cegbu.aconex.stepDefs;

import org.testng.Assert;

import com.oracle.cegbu.aconex.pages.Document.Login;
import com.oracle.cegbu.aconex.pages.SetUp.NewAccountPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

 public class TestNewUserLogin {
	 Login login=new Login();
	 NewAccountPage acc= new NewAccountPage();
	 
	@Given("Enter new username and password")
	public void enter_new_username_and_password() {
		login.enterUsernamePassword();
		login.clickLogin();
	}

	@Given("Enter all mandatory details")
	public void enter_all_mandatory_details() {
		
	    acc.enterMandatoryDetails();
	}
	@Then("User is redirected to homepage")
	public void user_is_redirected_to_homepage() {
		login.waitForPageLoad("homepage");
		System.out.println("---------"+login.getTitle());
		Assert.assertTrue(login.getTitle().contentEquals("Aconex"));
	}

}
