package org.appium.android.Pages;

import java.util.List;

import org.appium.android.Utils.BaseUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchAndProductPage  extends BaseUtil {

	public SearchAndProductPage() throws Exception {
		super();
		PageFactory.initElements(androidDriver, this);
	}
	
	@FindBy(id = "rs_search_src_text")
	public WebElement search_box;
	
	@FindBy(xpath = "//android.widget.ViewSwitcher[contains(@resource-id,'layoutSwitcher')]//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
	public List<WebElement> searchElementList;
	
	
	
	@FindBy(id = "title_feature_div")
	public WebElement product_title_info;
	
	@FindBy(id = "atfRedesign_priceblock_priceToPay")
	public WebElement product_price;
	
	
	@FindBy(id = "add-to-cart-button")
	public WebElement addToCartButton;
	
	@FindBy(xpath = "//*[@resource-id='sc-buy-box']/android.view.View[1]/android.view.View[4]")
	public WebElement cart_price_value;
	
	@FindBy(id = "chrome_action_bar_cart_count")
	public WebElement cart_Icon_button;
	
	@FindBy(id = "//*[@resource-id='activeCartViewForm']//android.view.View[contains(@resource-id,'sc-item-')]/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View")
	public WebElement cart_product_info;
	
	@FindBy(id = "sc-mini-buy-box")
	public WebElement buy_button;
	
	
	
		
	
	
		
}
