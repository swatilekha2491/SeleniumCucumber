package com.oracle.cegbu.aconex.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandler {

	Properties pro = new Properties();
	
	public PropertyHandler() {
		File file = new File("./src/test/resources/application.properties");
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public String getProperty(String key) {
		return pro.getProperty(key);
	}
	
	
}
