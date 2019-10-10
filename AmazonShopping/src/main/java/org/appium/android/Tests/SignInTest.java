package org.appium.android.Tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.appium.android.Commons.Helper;
import org.appium.android.Pages.SignInPage;
import org.appium.android.Utils.IOUtils;
import org.appium.android.Utils.TestMain;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignInTest extends SignInPage {
	public SignInTest() throws Exception {
		super();
	}

	SoftAssert softAssert;
	IOUtils ioUtils = new IOUtils();
	private static final Logger logger = Logger.getLogger(SignInTest.class);
	
	@BeforeSuite
	public void initStart()
	{
		logger.info("Start App Log Thread");
	    ioUtils.startAppLogsThread(androidDriver.getCapabilities().getCapability("UDID").toString(),"Amazon");
	}
	@AfterSuite
	public void initStop()
	{
		logger.info("Stop App Log Thread");
	    ioUtils.stopAppLogsThread();
	}

	
	/**
	 * Test summary : Verify all The element Text and verify Click able element is
	 * disable or enable
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	@Test(priority = 1)
	public void verifySingInPageElement(Method method) throws Exception {
		logger.info("Start execution" + method.getName());
		softAssert = new SoftAssert();
		softAssert.assertTrue(isSignInElementIsEnable(), "Sign in to your account label Not Enable");
	
		softAssert.assertEquals(getSignInPageText(),
				Helper.getProjectProperties("SignInPage", "singInToYourAccountLabel"), "Lable Text not match");
		
		softAssert.assertEquals(getViewYourWishListText(),
				Helper.getProjectProperties("SignInPage", "viewWishListLabel"), "View your wish list text not match");
		
		softAssert.assertEquals(getFindPurchaseText(), Helper.getProjectProperties("SingInPage", "findRecordLabel"),
				"Find & reorder past purchases text not match ");
		
		softAssert.assertEquals(getTrackYourPackagesText(),
				Helper.getProjectProperties("SignInPage", "trackYourPurchase"), "Track your purchases text not match");
		
		softAssert.assertEquals(getSingInButtonText(), Helper.getProjectProperties("SignInPage", "signInButtontext"),
				"Sign in button text not match");
		
		softAssert.assertTrue(isSignInButtonIsEnable(), "SignIn button not enable");
		
		softAssert.assertAll();
	}

	/**
	 * Test summary : Verify SignInButton Click Functionality
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 2)
	public void veriySignInButtonFunc(Method method) throws InterruptedException {
		logger.info("Start execution" + method.getName());
		softAssert = new SoftAssert();
		softAssert.assertTrue(clickOnSignInButton(), " SignIn button clicking Functionlity not working");
		softAssert.assertTrue(verdiyWelcomeText());
		softAssert.assertAll();
	}

}
