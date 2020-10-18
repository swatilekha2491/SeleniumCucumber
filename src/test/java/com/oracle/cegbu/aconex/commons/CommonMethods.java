package com.oracle.cegbu.aconex.commons;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.oracle.cegbu.aconex.utility.DriverManager;

public class CommonMethods {

	public WebDriver waitForElement(WebDriver driver, WebElement locator, int seconds) {
		
		
        try {
    //        waitForElementExplicitly(2000);
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOf(locator));
           
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        }
        return driver;
    }
 
 public void setClipboardData(String path) {
		//StringSelection is a class that can be used for copy and paste operations.
		  StringSelection stringSelection = new StringSelection(path);
		  Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		
		  clipboard.setContents(stringSelection, null);
		}
    public void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            
            //Press Enter	
            robot.keyPress(KeyEvent.VK_ENTER);
            
            //Release Enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(2000); 
            
            //Press Ctrl+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            
            //Release Ctrl+V
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL); //paste the copied string into the dialog box
            
            //Press Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            
            //Release Enter
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
		}
	
	  public int getTableSize(WebDriver driver, WebElement locator) {
	  
	  List<WebElement> rows = locator.findElements(By.tagName("tr")); return
	  rows.size();
	  
	  }
	 
    
    public Boolean returnColumnData(WebDriver driver,WebElement locator,String colDataName) {
    	
    
    	List<WebElement> rows = locator.findElements(By.tagName("tr"));
    	Boolean flag=false;
    	String celtext = null;
    	System.out.println("Number of Rows " + rows.size());
    	for (int row = 0; row < rows.size(); row++) 
    	{
    		
    	    //To locate columns(cells) of that specific row.
    	    List <WebElement> Columns_row = rows.get(row).findElements(By.tagName("td"));
    	    int columns_count = Columns_row.size();
    	    
    	    //Loop will execute till the last cell of that specific row.
    	    for (int column = 0; column < columns_count; column++) 
    	    {
    	    	
    	        // To retrieve text from that specific cell.
    	        celtext = Columns_row.get(column).getText();
    	        Columns_row.get(column).click();
    	        if(celtext.contentEquals(colDataName))
    	        {
    	        	flag=true;
    	        	
    	        	break;
    	        }
    	    }
    	  if(flag==true) 
    	  {
    		  break;
    	  }
	}
    	return flag;
    }
    
    public void selectFromDropdown(WebElement locator,String value) {
    	
    	Select element=new Select(locator);
    	element.selectByVisibleText(value);
    }

	public static void takeScreenshot(String name) {
		File src= ((TakesScreenshot)DriverManager.getInstance().getWebDriver()).getScreenshotAs(OutputType.FILE);
		String location=System.getProperty("user.dir")+File.separator+"Failed Screenshot"+File.separator+name+".png";
		try {
		 // now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File(location));
		}
		 
		catch (Exception e)
		 {
		  System.out.println("Errors while taking screenshot "+e.getMessage());
		 
		 }
	}

	public void waitForElementVisibility(WebDriver driver, WebElement successMsg, int seconds) {
		  WebDriverWait wait = new WebDriverWait(driver, seconds);
          wait.until(ExpectedConditions.visibilityOf(successMsg));
	}
}
