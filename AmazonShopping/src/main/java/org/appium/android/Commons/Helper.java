package org.appium.android.Commons;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Helper {

	static String defaultFile = "ApplicationConfig";
	
	/**
	 * 
	 * @param elementName :- property file keys
	 * @param propertiFileName : properties file name without extensions
	 * @return String :- return key values in string from and if string contain pre and post space it will be remove.
	 * @throws Exception
	 * @author shubham verma 
	 */
	public static String getProjectProperties(String propertiFileName,String elementName) throws Exception {
		Properties properties = new Properties();
		InputStream inputStream = new FileInputStream(
				System.getProperty("user.dir") + "/DataResource/"+propertiFileName.trim()+".properties");
		properties.load(inputStream);
		return properties.getProperty(elementName).toString().trim();

	}
	
	
	/**
	 * This Method used ApplicationConfig as by default 
	 * @param elementName :- property file keys
	 * @param propertiFileName : properties file name without extensions
	 * @return String :- return key values in string from and if string contain pre and post space it will be remove.
	 * @throws Exception
	 * @author shubham verma 
	 */
	public static String getProjectProperties(String elementName) throws Exception {
		Properties properties = new Properties();
		InputStream inputStream = new FileInputStream(
				System.getProperty("user.dir") + "/DataResource/"+defaultFile.trim()+".properties");
		properties.load(inputStream);
		return properties.getProperty(elementName).toString().trim();

	}
	
}
