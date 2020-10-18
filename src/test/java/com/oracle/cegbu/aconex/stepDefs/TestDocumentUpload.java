package com.oracle.cegbu.aconex.stepDefs;

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.awt.*;

import com.oracle.cegbu.aconex.commons.CommonMethods;
import com.oracle.cegbu.aconex.commons.PropertyHandler;
import com.oracle.cegbu.aconex.pages.Document.DocumentRegisterPage;
import com.oracle.cegbu.aconex.pages.Document.UploadDocumentPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDocumentUpload extends TestBase {
	
	UploadDocumentPage upload = new UploadDocumentPage();
	DocumentRegisterPage doc= new DocumentRegisterPage();
	CommonMethods common= new CommonMethods();
	Testlogin testLogin= new Testlogin();
	
	@Given("upload document for user")
	public void upload_document_for_user() throws Exception {
		testLogin.user_should_be_logged_into_the_application();
	    upload.uploadDocument();
	    Assert.assertTrue(upload.getSuccessMessage().contains("Document Uploaded Successfully"));
	}

	@When("search for the document")
	public void search_for_the_document() {
	    doc.clickDocumentLink();
	    doc.clickDocumentRegisterLink();
	    doc.enterStringInSearchTextbox(); 
	}

	@Then("Verify Document is present")
	public void verify_Document_is_present() {
		
	   List<WebElement> docs= doc.getDocumentTable();
	   for(WebElement doc:docs) {
		   Assert.assertTrue(doc.getText().contains(props.getProperty("docNumber")), "Document is present");
	   }
	   
	 }
	
	 
	 }
