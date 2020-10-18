package com.oracle.cegbu.aconex.utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	private static DriverManager drivermanager=null;
	private WebDriver driver;
	private DriverManager() {
		
		}

	public static DriverManager getInstance() {
		
		if(drivermanager==null) {
			drivermanager= new DriverManager();
		}
		return drivermanager;
	}

	public void setWebDriver(WebDriver driver) {
		
		this.driver=driver;
		
	}
	public WebDriver getWebDriver() {
		
		return this.driver;
	}
}
