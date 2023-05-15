package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.HomePageUI;
import pageUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage {
private WebDriver driver;

public LoginPageObject(WebDriver driver) {
	this.driver = driver;
}

public HomePageObject clickToLoginButton() {
	waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
	clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	new  PageGeneratorManager();
	return PageGeneratorManager.getHomePage(driver);
}

public void inputToEmailTextbox(String existingEmail) {
	waitForElementVisble(driver, LoginPageUI.EMAIL_TEXTBOX);
	sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, existingEmail);
}
public void inputToPasswordTextbox(String password) {
	waitForElementVisble(driver, LoginPageUI.PASSWORD_TEXTBOX);
	sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
}

public String getWarningNotInputDataMessage() {
	waitForAllElementVisible(driver, LoginPageUI.WARNING_NOT_INPUT__DATA_MESSAGE);
	return getElementText(driver, LoginPageUI.WARNING_NOT_INPUT__DATA_MESSAGE);
}

public String getErrorLoginMessage() {
	waitForAllElementVisible(driver, LoginPageUI.ERROR_EMAIL_MESSAGE);
	return getElementText(driver, LoginPageUI.ERROR_EMAIL_MESSAGE);
}

public boolean isMyAccountLinkDisplayed() {
	waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
	return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
}

}
