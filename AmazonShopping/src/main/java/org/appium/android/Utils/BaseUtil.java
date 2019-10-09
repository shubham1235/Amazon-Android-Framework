package org.appium.android.Utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

public class BaseUtil {

	public static AndroidDriver<MobileElement> androidDriver;
	static int count = 0;

	public BaseUtil() throws Exception {

		if (count == 0) {
			this.androidDriver = DriverGenerator.connectToAppiumServer();
		}
		count++;
	}

	/**
	 * Click on any type of element(android, Web, IOS)
	 * @param element
	 * @return boolean
	 * if element click successfully return TRUE else FALSE
	 */
	public boolean clickOnElement(WebElement element) {
		try {
			WaitForElement(element);
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Enter text in any type of element (android, Web, IOS) text field 
	 * @param element Android , Web , IOS Elements
	 * @param text element acceptable text
	 */
	public boolean enterText(WebElement element, String text) {
		try {
			WaitForElement(element);
			element.clear();
			element.sendKeys(text.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * Is element is Displayed on screen
	 * @param element  Android , Web , IOS Elements
	 * @return boolean If element is on screen return true else false
	 */
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	/**
	 * Is element is enabled on screen
	 * @param element  Android , Web , IOS Elements
	 * @return boolean If element is enabled return true else false
	 */
	public boolean isElementEnabled(WebElement element) {
		WaitForElement(element);
		return element.isEnabled();
	}

	/**
	 * 
	 * get text from element
	 * @param element  Android , Web , IOS Elements
	 * @return string return element text
	 */
	public String getElementText(WebElement element) {
		WaitForElement(element);
		return element.getText().toString().trim();

	}

	/**
	 * Implicit Wait till element not visible
	 * @param element  Android , Web , IOS Elements
	 * @return WebElement 
	 * @MaxTime max time limit 30 second for waiting
	 */
	public WebElement WaitForElement(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(androidDriver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element;
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * compare character sequence with element text
	 * @param element element  Android , Web , IOS Elements
	 * @param text character sequence
	 * @return boolean if character sequence is equal to element text return true else false
	 */
	public boolean verifyTextIsVisible(WebElement element, String text) {
		WaitForElement(element);
		return element.getText().toString().trim().equals(text.toString().trim());
	}

	/**
	 * verify text contained Element Is visible on screen
	 * @param text Text need for Element finding on screen
	 * @return boolean if text element on screen return true else false
	 */
	public boolean verifyElementIsVisible(String text) {
		try {
			WebElement element = androidDriver.findElementByAndroidUIAutomator(
					"new UiSelector().textContains(\"" + text.toString().trim() + "\").instance(0)");
			WaitForElement(element);
			return element.isDisplayed();
		} catch (Exception e) { // use specific exception like no such element
			return false;
		}

	}

	/**
	 * press Android back button
	 */
	@SuppressWarnings("deprecation")
	public void pressBackButton() {
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
	}

	/**
	 * press Android Keyboard enter button
	 */
	@SuppressWarnings("deprecation")
	public void pressEnterButton() {
		androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
	}

	/**
	 * Scroll to element and than click on element
	 * @param text On Screen element Text
	 * @return boolean if element click able return true else flase
	 */
	public boolean scrollToAndElementClick(String text) {
		try {
			Thread.sleep(1500);
			WebElement scrolledToElement = androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ text + "\").instance(0))");
			return clickOnElement(scrolledToElement);
		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * get element by text 
	 * @param text On Screen element Text
	 * @return Web element
	 */
	public WebElement getelementByTextWithScroll(String text) {
		try {
			WebElement scrolledToElement = androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ text + "\").instance(0))");
			return scrolledToElement;
		} catch (NoSuchElementException e) {

			return null;
		}

	}

	/**
	 * scroll to element by text
	 * @param text
	 * @param miliSecond Max try timing for finding element 
	 * @return boolean if element click able return true else false;
	 */
	public boolean scrollToElementAndClickWithTimer(String text, int miliSecond) {
		WebElement scrolledToElement = null;

		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) <= miliSecond && scrolledToElement == null) {
			try {

				scrolledToElement = androidDriver.findElementByAndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).setMaxSearchSwipes(3).scrollIntoView(new UiSelector().textContains(\""
								+ text + "\").instance(0))");

			} catch (Exception e) {
				scrolledToElement = androidDriver.findElementByAndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).setMaxSearchSwipes(3).scrollIntoView(new UiSelector().textContains(\""
								+ text + "\").instance(0))");
			}
		}
		return clickOnElement(scrolledToElement);
	}

	/**
	 * Scroll To Element by Class name and resource id
	 * 
	 * @param text
	 * @param ClassName
	 * @exception NullPointerException
	 * @return
	 */
	public WebElement scrollToElementByResourceID(String resourceId) {
		WebElement scrolledToElement = androidDriver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\""
						+ resourceId + "\").instance(0))");
		return scrolledToElement;
	}
	/**
	 * scroll by pointer 
	 * @param ScrollPintervalue pinter value should be 0.001 to 0.1 for any size of device
	 */
	public void scrollByPointer(double ScrollPintervalue) {
		Dimension size = androidDriver.manage().window().getSize();
		int startx = size.width / 2;
		int starty = (int) (size.height * 0.80);
		int endy = (int) (size.height * ScrollPintervalue); // changed from0.005 to 0.1 
		TouchAction action = new TouchAction(androidDriver);

		
		action.press(new PointOption<>().point(startx, starty)).waitAction().moveTo(new PointOption<>().point(startx, endy)).release().perform();

	}
	
	
	
	

	/**
	 * scroll to element by pointer , Max try limit is 1 minute for try to find element
	 * @param element
	 */
	public void scrollToElementByPointer(WebElement element) {

		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 60000) {
			scrollByPointer(0.40);
			try {
				element.click();
				break;
			} catch (Exception e) {
			}
		}

	}

}
