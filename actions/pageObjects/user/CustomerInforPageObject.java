package pageObjects.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.user.CustomerInforPageUI;

public class CustomerInforPageObject extends BasePage {
    private WebDriver driver;
	public CustomerInforPageObject(WebDriver driver) {
			this.driver = driver;
		}
	
	public void chooseCheckboxRadioButtonByName(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, CustomerInforPageUI.RADIO_BUTTON_BY_NAME, checkboxLabelName);
		checkToDefaultCheckboxRadio(driver, CustomerInforPageUI.RADIO_BUTTON_BY_NAME, checkboxLabelName);
	}

	public String getAttributeTextOfDayDropdown() {
		waitForElementVisble(driver, CustomerInforPageUI.DAY_DROPDOWN);
		return getTextIntoDroppdown(driver, CustomerInforPageUI.DAY_DROPDOWN);
	}
	
	public String getAttributeTextOfMonthDropdown() {
		waitForElementVisble(driver, CustomerInforPageUI.MONTH_DROPDOWN);
		return getTextIntoDroppdown(driver, CustomerInforPageUI.MONTH_DROPDOWN);
	}
	
	public String getAttributeTextOfYearDropdown() {
		waitForElementVisble(driver, CustomerInforPageUI.YEAR_DROPDOWN);
		return getTextIntoDroppdown(driver, CustomerInforPageUI.YEAR_DROPDOWN);
	}
}
