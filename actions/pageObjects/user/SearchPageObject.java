package pageObjects.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.user.SearchPageUI;

public class SearchPageObject extends BasePage {
	private WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getErrorSearchMessage() {
		waitForElementVisble(driver, SearchPageUI.ERROR_SEARCH_MESSAGE);
		return getElementText(driver, SearchPageUI.ERROR_SEARCH_MESSAGE);
	}

	public void inputToSearchKeywordTextbox(WebDriver driver, String data) {
		waitForElementVisble(driver, SearchPageUI.SEARCH_KEYWORD_TEXTBOX);
		sendKeyToElement(driver, SearchPageUI.SEARCH_KEYWORD_TEXTBOX, data);
	}

	public int getNumberProductOfResult() {
		waitForElementVisble(driver, SearchPageUI.PRODUCT_OF_RESULT_SEARCH);
		return getElementSize(driver, SearchPageUI.PRODUCT_OF_RESULT_SEARCH);
	}
	
	public void chooseCheckboxButtonByName(WebDriver driver, String checkboxLabelText) {
		waitForElementClickable(driver, SearchPageUI.CHECKBOX_BY_TEXT, checkboxLabelText);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.CHECKBOX_BY_TEXT, checkboxLabelText);
	}

	public void clickToSearchButton() {
		waitForElementVisble(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

}
