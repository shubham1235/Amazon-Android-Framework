package org.appium.android.Utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseUtil {

	public static AndroidDriver<MobileElement> androidDriver;

	public BaseUtil() throws Exception {
		startAndConnect();
		this.androidDriver = DriverGenerator.getAppiumDriver();
	}

	public void startAndConnect() throws Exception {
		DriverGenerator.connectToAppiumServer();
	}

	public boolean clickOnElement(WebElement element) {
		try {
			WaitForElement(element);
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public boolean isElementEnabled(WebElement element)
	{
		WaitForElement(element);
		return element.isEnabled();
	}

	public String getElementText(WebElement element) {
		WaitForElement(element);
		return element.getText().toString().trim();

	}

	public WebElement WaitForElement(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(androidDriver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element;
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}
}
