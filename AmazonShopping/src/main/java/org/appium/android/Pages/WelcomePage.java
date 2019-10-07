package org.appium.android.Pages;

import org.appium.android.Utils.BaseUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BaseUtil {
	
	public WelcomePage() throws Exception {
		super();
		PageFactory.initElements(androidDriver, this);
	}
	
	@FindBy(id="login_accordion_header")
	public WebElement loginRadioButton;
		
	@FindBy(id="ap_email_login")
	public WebElement userNameTextBox;
		
	@FindBy(xpath="//android.view.View[contains(@resource-id,'outer-accordion-signin-signup-page')]//*[@text='Continue']")
	public WebElement continueButton;
	
	@FindBy(xpath="//android.view.View[contains(@resource-id,'auth-error-message-box')]/android.view.View/android.view.View[1]")
	public WebElement wrongUserFisrtErrorMsg;
	
	@FindBy(xpath="//android.view.View[contains(@resource-id,'auth-error-message-box')]/android.view.View/android.view.View[2]")
	public WebElement wrongUserSecondErrorMsg;
	
	@FindBy(id="auth-password-container")
	public WebElement loginPasswordTextBox;
	
	@FindBy(id="signInSubmit")
	public WebElement loginButton;
	
	
	
	
}
