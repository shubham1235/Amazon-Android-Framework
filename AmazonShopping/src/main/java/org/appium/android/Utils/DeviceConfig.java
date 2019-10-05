package org.appium.android.Utils;

import org.appium.android.Commons.Helper;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;

public class DeviceConfig {
	static DesiredCapabilities capabilities = new DesiredCapabilities();
	static DesiredCapabilities getDeviceCapability() throws Exception {
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Helper.getProjectProperties("PLATFORM_NAME"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Helper.getProjectProperties("DEVICE_NAME"));
		capabilities.setCapability(MobileCapabilityType.UDID,Helper.getProjectProperties("UDID"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Helper.getProjectProperties("PLATFORM_VERSION"));
		capabilities.setCapability("appPackage",Helper.getProjectProperties("APPPACKAGE"));
		capabilities.setCapability("automationName", Helper.getProjectProperties("AUTOMATION_NAME"));
		capabilities.setCapability("newCommandTimeout", Helper.getProjectProperties("NEWCOMMANDTIMEOUT"));
		capabilities.setCapability("appActivity", Helper.getProjectProperties("APPACTIVITY"));
		//capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
		return capabilities;

	}

}


