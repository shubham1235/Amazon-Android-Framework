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
	
	@FindBy(id="")
	public WebElement welcomeLable;
	
	
	@FindBy(id="")
	public WebElement LoginRadioButton;
	
	
	@FindBy(id="")
	public WebElement userNameTextBox;
	
	
	@FindBy(id="")
	public WebElement continueButton;
	
	

	@FindBy(id="")
	public WebElement wrongUserNameErrorMsg;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
