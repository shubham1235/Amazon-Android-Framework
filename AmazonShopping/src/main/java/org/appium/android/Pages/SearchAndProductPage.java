package org.appium.android.Pages;

import java.util.List;

import org.appium.android.Utils.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.TouchAction;
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

	@FindBy(xpath = "//*[@resource-id='title_feature_div']//android.view.View")
	public WebElement product_title_info;

	@FindBy(id = "//*[@resource-id='atfRedesign_priceblock_priceToPay']//android.widget.EditText")
	public WebElement product_price;

	@FindBy(xpath = "//*[@text='Add to Cart']")
	public WebElement addToCartButton;

	String add_to_cart_ID = "add-to-cart-button";

	@FindBy(xpath = "//*[@resource-id='sc-buy-box']/android.view.View[1]/android.view.View[4]")
	public WebElement cart_price_value;

	@FindBy(id = "Cart")
	public WebElement cart_Icon_button;

	@FindBys({
			@FindBy(id = "//*[@resource-id='activeCartViewForm']//android.view.View[contains(@resource-id,'sc-item-')]/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View") })
	public WebElement cart_product_info;

	@FindBy(id = "sc-mini-buy-box")
	public WebElement buy_button;
	
	

	public void clickOnSearchBar() {
		clickOnElement(search_box);
	}
	
	
	
	public void searchProduct(String text) {
		enterText(search_box, text);
	}

	public String getproductInfo() {
		return product_title_info.getText();
	//	return getElementText(product_title_info);
	}

	public String getproductPrice() {
		return getElementText(product_price);
	}

	public void clickOnAddToCart() {
		scrollToElementByPointer(addToCartButton);
	}

	public boolean clickOnCartIcon() {
		return clickOnElement(cart_Icon_button);
	}


	public String getProductInfoFromCart(String productSubName) {

		WebElement element = getelementByTextWithScroll(productSubName);
		return element.getText();
	}
	
	public boolean clickOnBuyButton() {
		return clickOnElement(buy_button);

	}

}
