package com.oracle.cegbu.aconex.runner;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		DesiredCapabilities capabilities=DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		ChromeOptions co= new ChromeOptions();
		co.merge(capabilities);
		ChromeDriver driver = new ChromeDriver(co);
		driver.get("https://lb1a.sniadpraconex1.gbucdsint02iad.oraclevcn.com/Logon");
		driver.quit();
		
		
	}

}
