package org.appium.android.Pages;

import org.appium.android.Utils.BaseUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class SignInPage extends BaseUtil {

	public SignInPage() throws Exception {
		super();
		PageFactory.initElements(androidDriver, this);
	}

	@FindBy(id = "signin_to_yourAccount")
	public WebElement signin_To_YourAccount_Label;

	@FindBy(id = "view_your_wish_list")
	public WebElement view_Your_Wish_List_Label;

	@FindBy(id = "Find_purchase")
	public WebElement find_Purchase_Label;

	@FindBy(id = "track_your_packages")
	public WebElement track_Your_Packages_Label;

	@FindBy(id = "sign_in_button")
	public WebElement sign_in_Button;

	@FindBy(id = "new_user")
	public WebElement new_User_Account_Button;

	@FindBy(id = "skip_sign_in_buttonn")
	public WebElement skip_Sign_In_Button;

	
	@FindBy(id = "register_accordion_header")
	public WebElement yoyo;
	
	
	public void clickonyoyo()
	{
		clickOnElement(yoyo);
		
		System.out.println(yoyo.getText());
		System.out.println(yoyo.isSelected());
		System.out.println(yoyo.isDisplayed());
	}
	
	public String getSignInPageText() {
		return getElementText(signin_To_YourAccount_Label);

	}
	
	public boolean isSignInElementIsEnable()
	{
		return isElementEnabled(signin_To_YourAccount_Label);
		
	}
	
	public String getViewYourWishListText()
	{
		return getElementText(view_Your_Wish_List_Label);
	}
	
	public String getFindPurchaseText()
	{
		return getElementText(find_Purchase_Label);
	}
	
	
	public String getTrackYourPackagesText()
	{
		return getElementText(track_Your_Packages_Label);
	}
		
	public boolean clickOnSignInButton() {
		return clickOnElement(sign_in_Button);
	}
	
	public boolean isSignInButtonIsEnable() {
		return isElementEnabled(sign_in_Button);
	}

	public String getSingInButtonText() {
		return getElementText(sign_in_Button);

	}
}
