package org.appium.android.Pages;

import org.appium.android.Utils.BaseUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BaseUtil {
	
	public WelcomePage() throws Exception {
		PageFactory.initElements(androidDriver, this);
	}
	
	@FindBy(id="login_accordion_header")
	public WebElement loginRadioButton;
		
	@FindBy(id="ap_email_login")
	public WebElement userNameTextBox;
		
	@FindBy(xpath="//android.view.View[contains(@resource-id,'outer-accordion-signin-signup-page')]//*[@text='Continue']")
	public WebElement continueButton;
	
	@FindBy(xpath="//android.view.View[contains(@resource-id,'auth-error-message-box')]/android.view.View/android.view.View[1]")
	public WebElement invalidUserFisrtErrorMsg;
	
	@FindBy(xpath="//android.view.View[contains(@resource-id,'auth-error-message-box')]/android.view.View/android.view.View[2]")
	public WebElement invalidUserSecondErrorMsg;
	
	@FindBy(id="auth-password-container")
	public WebElement loginPasswordTextBox;
	
	@FindBy(id="signInSubmit")
	public WebElement loginButton;
	
	// when login first time this app language pop up on Screen 
	@FindBy(xpath="//android.view.View[contains(@resource-id,'customer-preferences')]//*[@text='English - EN']/..")
	public WebElement englishRadioButton;
	
	@FindBy(id="icp-btn-save")
	public WebElement save_Change_Button;
	
	
	/**
	 * click on login radio button
	 * @return
	 */
	public boolean clickOnRadioLoginButton(){
		return clickOnElement(loginRadioButton);
	}
	
	/**
	 * Enter User name For Login
	 * @param text
	 */
	public boolean enterUserName(String text)
	{
		return enterText(userNameTextBox, text);
	}
	
	/**
	 * click On Continue button
	 */
	public boolean clickOnContinueButton(){
		return clickOnElement(continueButton);		
	}	
	/**
	 * get invalid User Name Error Message 
	 */
	public String  getWrongUsrNameErrorMgs(){
		return getElementText(invalidUserFisrtErrorMsg)+" "+getElementText(invalidUserSecondErrorMsg);
		
	}
	/**
	 * Enter password
	 * @param password
	 */
	public boolean enterPassword(String password){
		return enterText(loginPasswordTextBox, password);
	}
	
	/**
	 * click on Continue button
	 */
	public boolean clickOnLoginButton(){
		return clickOnElement(loginButton);
	}
	/**
	 * get to know password text box is displayed 
	 * @return
	 */
	public boolean isPasswordTextfieldDisplayed(){
		return isElementDisplayed(loginPasswordTextBox);
	}
	
	/**
	 * select english Language for app 
	 * @return
	 */
	public boolean selectEnglishforApp()
	{
		return clickOnElement(englishRadioButton);
		
	}
	
	
	/**
	 * click on save change button on Pop up
	 * @return
	 */
	public boolean clickOnSaveChangeButton()
	{
		return clickOnElement(save_Change_Button);
		
	}
	
}
