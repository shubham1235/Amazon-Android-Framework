package org.appium.android.Tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.appium.android.Commons.Helper;
import org.appium.android.Commons.TestScriptHelper;
import org.appium.android.Pages.SearchAndProductPage;
import org.appium.android.Pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchAndProductTest extends SearchAndProductPage {

	SoftAssert softAssert;
	private static final Logger logger = Logger.getLogger(SearchAndProductTest.class);
	
	public SearchAndProductTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public void igniter() throws Exception {
		TestScriptHelper.closeAndLogin(Helper.getProjectProperties("AppCredentials", "userName"),Helper.getProjectProperties("AppCredentials", "password"));
	}

	/**
	 * @summary :- Test case search the product name(which is got from
	 *          searchPrductTest.properties file) Test Automation steps -> search
	 *          items -> search product in items result -> if product found -> click
	 *          on it -> get price and product info -> click on add to cart button
	 *          -> click on cart button -> comparing added product details with cart
	 *          product details -> and click to process to buy
	 * @throws Exception
	 */
	@Test
	public void prodcutSarchAddTocart(Method method) throws Exception {
		softAssert = new SoftAssert();
		logger.info("Start execution" + method.getName());
		// Get product information price and product info which is used full for cart
		// product comparison
		String prodcutInfo = "", productPrice = "";
		softAssert.assertTrue(clickOnSearchBar(), "Error 1 Search bar not Visible or not clickable");
		softAssert.assertTrue(searchProduct(Helper.getProjectProperties("SearchAndProduct", "searchText")),
				"Error 2 search bar not accepting char Sequence");
		Assert.assertTrue(
				findProductInSearchResult(Helper.getProjectProperties("SearchAndProduct", "productName"), 20000),
				"Error 3 element not fond in search Result");

		prodcutInfo = getproductInfo();
		if (prodcutInfo.equals("")) {
			softAssert.assertTrue(false, "Error 4 product Info Lable Not Visible or page not loaded");
		}

		productPrice = getproductPrice();
		if (productPrice.equals("")) {
			softAssert.assertTrue(false, "Eroor 5 product price Lable Not Visible or page not loaded");
		}
		softAssert.assertTrue(clickOnAddToCart(), "Error 6 add to cart button not visble or clickable");
		softAssert.assertTrue(clickOnCartIcon(), "Error 7 Cart icon Button not visible");
		softAssert.assertTrue(getCartProductInfo().contains(productPrice), "Error 8 product info not matched ");
		softAssert.assertTrue(getCartProductPrice().contains(productPrice), "Error 9 product price not matched");
		softAssert.assertTrue(clickOnBuyButton(), "Error 10 Process to Buy button not click able or not visible ");
		softAssert.assertAll();

	}
}
