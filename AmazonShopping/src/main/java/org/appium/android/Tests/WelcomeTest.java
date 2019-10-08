package org.appium.android.Tests;

import org.appium.android.Pages.SignInPage;
import org.appium.android.Pages.WelcomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WelcomeTest extends WelcomePage {

	public WelcomeTest() throws Exception {
	}

	SoftAssert softAssert;
	String userName ="Shubverma5@gmail.com";
	String password ="Shubham1.";
	SignInPage singInPage = new SignInPage();
	
	@Test(priority = 1)
	public void inValidUserName(){
		softAssert = new SoftAssert();
		String invalidError ="";
		singInPage.clickOnSignInButton();
		softAssert.assertTrue(clickOnRadioLoginButton(),"login radio button not selected some thing wrong with click event");
		enterUserName("invalidUserName");
		clickOnContinueButton();
		invalidError = getWrongUsrNameErrorMgs();
		softAssert.assertTrue(invalidError.contains("There was a problem"));
		softAssert.assertTrue(invalidError.contains("We cannot find an account with that email address"));
			
	}
	
	@Test(priority = 2)
	public void validUserName(){
		softAssert = new SoftAssert();
		softAssert.assertTrue(clickOnRadioLoginButton(),"login radio button not selected some thing wrong with click event");
		enterUserName(userName);
		softAssert.assertTrue(clickOnContinueButton(),"continue button click functionality not working");
		softAssert.assertTrue(isPasswordTextfieldDisplayed());
		softAssert.assertAll();
	}
	
	@Test(priority = 3)
	public void loginInAmazonApp(){
		softAssert = new SoftAssert();
		enterPassword(password);
		softAssert.assertTrue(clickOnLoginButton(),"login button not click able");
		softAssert.assertTrue(selectEnglishforApp(),"PopUp not visible on screen");
		softAssert.assertTrue(clickOnSaveChangeButton(),"save change button is disbale");
		softAssert.assertAll();
	}
	
	
	
	
	
	
}
