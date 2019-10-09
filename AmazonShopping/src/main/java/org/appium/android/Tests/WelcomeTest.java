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
	
	
	@BeforeClass
	public void baseClassTest()
	{
	// if Need to Run This calls so please UnCoomen below code. below code click on sign in button and move to next page
	//	singInPage.clickOnSignInButton();

	}
	
	
	/**
	 * @summary :- verify Login Page and Welcome Page Element and perform invalid Username functionality 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void inValidUserName(){
		softAssert = new SoftAssert();
		String invalidError ="";
		softAssert.assertTrue(clickOnRadioLoginButton(),"login radio button not selected some thing wrong with click event");
		softAssert.assertTrue(enterUserName("invalidUserName"),"not able to enter text in User name text box");
		softAssert.assertTrue(clickOnContinueButton(), "Not able to click on conttinue button");
		invalidError = getWrongUsrNameErrorMgs();
		softAssert.assertTrue(invalidError.contains("There was a problem"));
		softAssert.assertTrue(invalidError.contains("We cannot find an account with that email address"));
			
	}
	
	/**
	 * @summary :- verify welcome page element and perform  valid  UserName functionality  
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void validUserName(){
		softAssert = new SoftAssert();
		softAssert.assertTrue(clickOnRadioLoginButton(),"login radio button not selected some thing wrong with click event");
		softAssert.assertTrue(enterUserName(userName),"UserName Text box not accepting Input");
		softAssert.assertTrue(clickOnContinueButton(),"continue button click functionality not working");
		softAssert.assertTrue(isPasswordTextfieldDisplayed());
		softAssert.assertAll();
	}
	
	/**
	 * @summary :-Login Into App and verify login functionality  
	 * @throws Exception
	 */

	@Test(priority = 3)
	public void loginInAmazonApp(){
		softAssert = new SoftAssert();
		softAssert.assertTrue(enterPassword(password),"Password Text box not accepting Inputs");
		softAssert.assertTrue(clickOnLoginButton(),"login button not click able");
		softAssert.assertTrue(selectEnglishforApp(),"PopUp not visible on screen");
		softAssert.assertTrue(clickOnSaveChangeButton(),"save change button is disbale");
		softAssert.assertAll();
	}
	
	
	
	
	
	
}
