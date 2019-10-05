package org.appium.android.Utils;

import java.net.URL;

import org.appium.android.Commons.Helper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverGenerator {
	public static AndroidDriver<MobileElement> appiumDriver;
	AppiumServer appiumServer = new AppiumServer();

	public static void main(String[] args) throws Exception {
		DriverGenerator driverGenerator = new DriverGenerator();
		driverGenerator.connectToAppiumServer();
	}

	/**
	 * This method establishing the connection between framework and Appium server
	 * which is invoke by the appiumServer.startAppiumServer()
	 * 
	 * @throws Exception
	 */
	public void connectToAppiumServer() throws Exception {

		// invoke appium server
		appiumServer.startAppiumServer();
		// wait for Appium server start internal
		Thread.sleep(2000);
		appiumDriver = new AndroidDriver<MobileElement>(
				new URL("http://0.0.0.0:" + Helper.getProjectProperties("appiumPortNumber") + "/wd/hub"),
				DeviceConfig.getDeviceCapability());
		
		appiumDriver.launchApp();

	}

}
