package com.oracle.cegbu.aconex.stepDefs;

import org.testng.Assert;

import com.oracle.cegbu.aconex.pages.Document.Login;
import com.oracle.cegbu.aconex.pages.SetUp.SetUpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TestUnlockUser {
	Login login=new Login();
	SetUpPage set=new SetUpPage();
	
	@Given("User is again on Login Page")
	public void user_is_again_on_Login_Page() {
		 Assert.assertTrue(login.getTitle().contentEquals("Aconex Europe Login"));
	}

	@And("Enter admin username and password")
	public void enter_admin_username_and_password() {
	    login.enterAdminUsernamePassword();
	}

	@And("admin clicks Login")
	public void admin_clicks_Login() {
	    login.clickLogin();
	}

	@Then("Admin unlocks the user")
	public void admin_unlocks_the_user() throws Exception {
		set.unlockUser();
	    Assert.assertTrue(set.getUserMessage().contentEquals("User's details have been saved successfully"));
	}
	
}
