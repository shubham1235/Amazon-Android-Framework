package org.appium.android.Utils;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class TestMain {

	private static final Logger logger = Logger.getLogger(TestMain.class);


	public static void main(String[] args) {
		// BasicConfigurator.configure();
		System.setProperty("current.date", new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		System.setProperty("current.type", "RockAggfdLy");
		logger.info("hello");
		logger.error("hello");
		logger.info("hello");
		logger.info("hello");
		logger.info("hello");
		
		
//		IOUtils ioUtils = new IOUtils();
//		ioUtils.startAppLogsThread("QDT4C18104000209","Amazon");
//		

	}

}
