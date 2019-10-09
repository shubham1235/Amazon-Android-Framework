package org.appium.android.Commons;

import org.appium.android.Pages.SignInPage;
import org.appium.android.Tests.WelcomeTest;

public class TestScriptHelper {

	/**
	 * login process like  fresh install app
	 * @param userName
	 * @param password
	 * @throws Exception
	 * @author shubham verma 
	 */
	public static void closeAndLogin(String userName, String password) throws Exception {
		WelcomeTest welcomeTest = new WelcomeTest();
		SignInPage singInPage = new SignInPage();
		welcomeTest.androidDriver.resetApp();
		welcomeTest.androidDriver.launchApp();
		singInPage.clickOnSignInButton();
		welcomeTest.enterUserName(userName.trim());
		welcomeTest.clickOnContinueButton();
		welcomeTest.enterPassword(password.trim());
		welcomeTest.clickOnLoginButton();
		Thread.sleep(4000);
		welcomeTest.selectEnglishforApp();
		welcomeTest.clickOnSaveChangeButton();
	}
}
