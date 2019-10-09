package org.appium.android.Pages;

import java.util.List;

import org.appium.android.Utils.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

public class SearchAndProductPage extends BaseUtil {

	public SearchAndProductPage() throws Exception {
		super();
		PageFactory.initElements(androidDriver, this);
	}

	@FindBy(id = "rs_search_src_text")
	public WebElement search_box;

	@FindBys({
			@FindBy(xpath = "//android.widget.ViewSwitcher[contains(@resource-id,'layoutSwitcher')]//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup") })
	public List<WebElement> searchProductsList;

	@FindBy(xpath = "//*[@text='Add to Cart']")
	public WebElement addTo_Cart_Button;

	String add_to_cart_ID = "add-to-cart-button";

	@FindBy(xpath = "//*[@resource-id='sc-buy-box']/android.view.View[1]/android.view.View[4]")
	public WebElement cart_price_value;

	@FindBy(id = "Cart")
	public WebElement cart_Icon_button;

	@FindBys({
			@FindBy(id = "//*[@resource-id='activeCartViewForm']//android.view.View[contains(@resource-id,'sc-item-')]/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View") })
	public List<WebElement> cart_product_info;

	@FindBys({
			@FindBy(id = "//android.view.View[contains(@resource-id,'sc-item')]//*[contains(@class,'android.widget.ListView')]/android.view.View[1]/android.view.View[2]") })
	public List<WebElement> cartProductPrice;

	@FindBy(id = "sc-mini-buy-box")
	public WebElement buy_button;

	/*
	 * click on Search bar
	 */
	public boolean clickOnSearchBar() {
		return clickOnElement(search_box);
	}

	/*
	 * search product in app
	 */
	public boolean searchProduct(String text) {
		boolean flag = enterText(search_box, text);
		if (flag) {
			androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get product details
	 * 
	 * @return
	 */
	public String getproductInfo() {
		return getElementText(androidDriver.findElementById("titleExpanderContent").findElementById("title_feature_div")
				.findElementByClassName("android.view.View"));

	}

	/**
	 * find in search Results
	 * 
	 * @param searchText :- what text need to search
	 * @param miliSecond : provide time in miliSecond
	 * @return
	 */
	public boolean findProductInSearchResult(String searchText, int miliSecond) {
		return scrollToElementAndClickWithTimer(searchText, miliSecond);
	}

	/**
	 * get product price
	 * 
	 * @return
	 */
	public String getproductPrice() {

		org.openqa.selenium.Dimension size = androidDriver.manage().window().getSize();
		int startx = size.width / 2;
		int starty = (int) (size.height / 2);
		int endy = (int) (size.height / 3);
		TouchAction action = new TouchAction(androidDriver);
		long startTime = System.currentTimeMillis();
		while (true) {
			action.press(new PointOption<>().point(startx, starty)).waitAction()
					.moveTo(new PointOption<>().point(startx, endy)).perform().release();
			try {
				Thread.sleep(1000);
				if (androidDriver.findElement(By.id("atfRedesign_priceblock_priceToPay"))
						.findElement(By.className("android.widget.EditText")).getText().length() > 2) {
					return androidDriver.findElement(By.id("atfRedesign_priceblock_priceToPay"))
							.findElement(By.className("android.widget.EditText")).getText();
				}
			} catch (Exception e) {
				if ((System.currentTimeMillis() - startTime) > 30000) {
					break;
				}
			}
		}
		return "null";
	}

	/**
	 * click On Add To Cart button
	 * 
	 * @return
	 */
	public boolean clickOnAddToCart() {

		int cartcount = Integer.valueOf(androidDriver.findElement(By.id("action_bar_cart_count")).getText().toString());

		org.openqa.selenium.Dimension size = androidDriver.manage().window().getSize();
		int startx = size.width / 2;
		System.out.println("device width " + startx);
		System.out.println("device height " + size.height);
		int starty = (int) (size.height * 0.7);
		int endy = (int) (size.height * 0.4);

		while (true) {
			TouchAction action = new TouchAction(androidDriver);
			long startTime = System.currentTimeMillis();
			action.press(new PointOption<>().point(startx, starty)).waitAction()
					.moveTo(new PointOption<>().point(startx, endy)).perform().release();

			try {
				Thread.sleep(2000);
				action.tap(PointOption.point(addTo_Cart_Button.getSize().getWidth(),
						addTo_Cart_Button.getSize().getWidth()));
				Thread.sleep(4000);
				if (cartcount < Integer
						.valueOf(androidDriver.findElement(By.id("action_bar_cart_count")).getText().toString())) {

					return true;
				}
			} catch (Exception e) {
				if ((System.currentTimeMillis() - startTime) > 60000) {
					return false;
				}
			}
		}
	}

	/**
	 * click on Cart Icon
	 * 
	 * @return
	 */
	public boolean clickOnCartIcon() {
		return clickOnElement(cart_Icon_button);
	}

	/**
	 * get Products price from cart
	 * 
	 * @return
	 */
	public String getCartProductInfo() {
		String productInfo = null;
		for (int i = 0; i <= cart_product_info.size() - 1; i++) {
			productInfo = productInfo + cart_product_info.get(i).getText().toString() + " ";
		}
		return productInfo;
	}

	/**
	 * get prodcut price
	 * 
	 * @return
	 */
	public String getCartProductPrice() {
		String productPrice = null;
		for (int i = 0; i <= cartProductPrice.size() - 1; i++) {
			productPrice = productPrice + cartProductPrice.get(i).getText().toString() + " ";
		}
		return productPrice;
	}

	/**
	 * click on buy button in cart
	 * 
	 * @return
	 */
	public boolean clickOnBuyButton() {
		return clickOnElement(buy_button);

	}
}
