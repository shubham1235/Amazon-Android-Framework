package org.appium.android.Tests;

import org.appium.android.Pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignInTest extends SignInPage {
	
	SoftAssert softAssert;
	
	
	public SignInTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	
//	@Test(priority = 1)
//	public void verifySingInPageElement()
//	{
//		softAssert = new SoftAssert();		
//		softAssert.assertTrue(isSignInElementIsEnable(),"Sign in to your account label Not Enable");
//		softAssert.assertEquals(getSignInPageText(), "Sign in to your account","Lable Text not match");
//		softAssert.assertEquals(getViewYourWishListText(), "View your wish list" ,"View your wish list text not match");
//		softAssert.assertEquals(getFindPurchaseText(), "Find & reorder past purchases","Find & reorder past purchases text not match ");
//		softAssert.assertEquals(getTrackYourPackagesText(), "Track your purchases","Track your purchases text not match");
//		softAssert.assertEquals(getSingInButtonText(), "Already a customer? Sign in","Sign in button text not match");
//		softAssert.assertTrue(isSignInButtonIsEnable(),"SignIn button not enable");
//		softAssert.assertAll();
//	}
	
	
	@Test(priority =2)
	public void veriySignInButtonFunc() throws InterruptedException
	{
		softAssert = new SoftAssert();	
	//	Assert.assertTrue(clickOnSignInButton()," SignIn button clicking Functionlity not working");
			clickOnSignInButton();
			clickonyoyo();
			
			
	}
	
	
}
