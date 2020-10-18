package com.oracle.cegbu.aconex.stepDefs;

import org.testng.Assert;

import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.pages.Document.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Testlogin {

	Login login=new Login();
	PropertyHandler props= new PropertyHandler();
	
	  @Given("Enter username and password") public void
	  enter_username_and_password() { login.enterUsernamePassword(); }
	  
	  @Given("Click Login") public void click_Login() {
	  
	  login.clickLogin(); }
	  
	  @Then("User should land on Homepage") public void
	  user_should_land_on_Homepage() throws InterruptedException {
	  
	  Thread.sleep(1000);
	  Assert.assertTrue(login.getTitle().contentEquals("Aconex"));
	  }
	  
	  
	  @Given("User is on the Login Page") public void user_is_on_the_Login_Page() {
	  Assert.assertTrue(login.getTitle().contentEquals("Aconex Europe Login")); }
	 
	
	  @Given("User should be logged into the application") public void
      user_should_be_logged_into_the_application() throws Exception {
	  user_is_on_the_Login_Page(); 
	  enter_username_and_password(); 
	  click_Login();
	  user_should_land_on_Homepage(); }
	 

}
