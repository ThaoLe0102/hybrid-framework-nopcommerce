package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.user.AddressPageObject;
import pageObjects.user.HomePageObject;
import pageUIs.user.BasePageUI;
import pageUIs.user.CustomerInforPageUI;

public class BasePage {
	WebDriver driver;
	Select select;
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl (WebDriver driver) {
		return driver.getPageSource();
	}
	
	public String getPageSourceCode (WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage (WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage (WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage (WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> setAllCookies (WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void getCookie(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence (WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait (driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert (WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert (WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void closeAllWindowWithoutParent (WebDriver driver, String parentID) {
		Set <String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs ) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	public void switchToWindowByID(WebDriver driver, String otherID) {
		Set <String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
			driver.switchTo().window(id);	
			break;
			}
		}
	}
	
	public void switchToWindowByPageTitle (WebDriver driver, String expectedPageTitle) {
		Set <String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}
	
	public String getDynamicXpath (String xpathLocator, String... dynamicValues) {
		xpathLocator = String.format(xpathLocator, (Object[]) dynamicValues);
		return xpathLocator;
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}
	
	public List<WebElement> getListWebElement (WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator, String ...dynamicValues) {
		getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).click();
	}
	
	public void sendKeyToElement (WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendKeyToElement (WebDriver driver, String xpathLocator, String textValue, String ...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText (WebDriver driver, String xpathLoacator) {
		return getWebElement(driver, xpathLoacator).getText();
	}
	
	public String getElementText (WebDriver driver, String xpathLocator, String ...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getText();
	}
	
	public void selectItemInDefaultDropdown (WebDriver driver, String xpathLocator, String textItem) {
	Select select = new Select (getWebElement(driver, xpathLocator));
	select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown (WebDriver driver, String xpathLocator, String textItem, String ...dynamicValues) {
		Select select = new Select (getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByVisibleText(textItem);
		}
	
	public String gettemInDefaultDropdown (WebDriver driver, String xpathLocator) {
		Select select = new Select (getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple (WebDriver driver, String xpathLocator ) {
		Select select = new Select (getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textExpectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
		List<WebElement> allItems = driver.findElements(By.xpath(childLocator));
		for (WebElement item : allItems) {
			String textActualItem = item.getText();
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
				break;
			}
		}
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getEmlementAttribute (WebDriver driver, String attributeName, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getEmlementAttribute (WebDriver driver, String attributeName, String xpathLocator, String ...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getTextIntoDroppdown (WebDriver driver, String xpathLocator) {
		select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator)));
	return select.getFirstSelectedOption().getText();
	}
	
	public String getEmlementCssValue (WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA (String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver,xpathLocator).size();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator, String ...dynamicValues) {
		return getListWebElement(driver,getDynamicXpath(xpathLocator, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxRadio (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement (driver, xpathLocator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public boolean checkToStatusDefaultCheckboxRadio (WebDriver driver, String xpathLocator, String ...dynamicValues) {
		WebElement element = getWebElement (driver, getDynamicXpath(xpathLocator, dynamicValues));
		if(!element.isSelected()) {
			return false;
		}
		return true;
	}
	
	public void checkToDefaultCheckboxRadio (WebDriver driver, String xpathLocator, String ...dynamicValues) {
		WebElement element = getWebElement (driver, getDynamicXpath(xpathLocator, dynamicValues));
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement (driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox (WebDriver driver, String xpathLocator, String ...dynamicValues) {
		WebElement element = getWebElement (driver, getDynamicXpath(xpathLocator, dynamicValues));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();	
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String ...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isDisplayed();
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		overrideGlobalTimeout(driver, 5);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		overrideGlobalTimeout(driver, 30);
		
		if (elements.size()==0) {
			return true;
		}else if (elements.size()>0 && !elements.get(0).isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();	
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();	
	}
	
	public void switchToFrameIframe (WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement (WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}
	 
	public void pressKeyToElement (WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}
	
	public void pressKeyToElement (WebDriver driver, String xpathLocator, Keys key, String ...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	
	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}


	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		 final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String xpathLocator, String ...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisble(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
	}
	
	public void waitForElementVisble(WebDriver driver, String xpathLocator, String ...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}
	
	public void waitForAllElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathLocator)));
	}
	
	public void waitForAllElementVisible (WebDriver driver, String xpathLocator, String ...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForElementInvisble(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLocator)));
	}
	
	public void waitForAllElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathLocator, String ...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}
	
	public void uploadMultipleFiles (WebDriver driver,String addressUploadFile, String ...fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		for (String file: fileNames) {
			fullFileName = fullFileName + filePath + file +"\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, addressUploadFile).sendKeys(fullFileName);
	}
	
	//Dynamic
	public BasePage openMyAccountPageAtName(WebDriver driver,String pageName ) {
		waitForAllElementVisible(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT, pageName);
		switch (pageName) {
		case "Addresses":
			return PageGeneratorManager.getAddressPage(driver);
		case "Customer info":
			return PageGeneratorManager.getCustomerInforPage(driver);
			

		default:
			throw new RuntimeException("Invalid Dyanmic Name");
		}
	}
	
	public void openMyAccountPageNameAtName(WebDriver driver,String pageName ) {
		waitForAllElementVisible(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT, pageName);
	}
	
	public void clickToLinkInMyAccountAreaByText(WebDriver driver, String textName ) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT, textName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT, textName);
	}
	
	public void clickToLinkInCatogoriesAreaByText(WebDriver driver, String textName ) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_CATOGORIES_AREA_BY_TEXT, textName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_CATOGORIES_AREA_BY_TEXT, textName);
	}
	
	public void clickToLinkInHeaderByText(WebDriver driver, String textName ) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_HEADER_AREA_BY_TEXT, textName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_HEADER_AREA_BY_TEXT, textName);
	}
	
	public void clickToLinkInFooterByText(WebDriver driver, String textName ) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_FOOTER_AREA_BY_TEXT, textName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_FOOTER_AREA_BY_TEXT, textName);
	}
	
	public void clickToButtonByTex(WebDriver driver, String textName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, textName);
		clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, textName);
		sleepInSecond(2);
	}
	
	public void inputToTextboxByName(WebDriver driver, String textboxLabelName, String nameTextbox) {
		waitForElementVisble(driver, CustomerInforPageUI.TEXTBOX_BY_NAME, textboxLabelName);
		sendKeyToElement(driver, CustomerInforPageUI.TEXTBOX_BY_NAME, nameTextbox, textboxLabelName);
	}
	
	public void chooseDopdownByName(WebDriver driver, String dropdownLabelName, String nameDropdown) {
		waitForElementVisble(driver, CustomerInforPageUI.DROPDOWN_BY_NAME, dropdownLabelName);
		selectItemInDefaultDropdown(driver, CustomerInforPageUI.DROPDOWN_BY_NAME, nameDropdown, dropdownLabelName);
	}
	
	public boolean checkStatusOfRadioButton(WebDriver driver, String dropdownLabelName) {
		waitForElementVisble(driver, CustomerInforPageUI.TEXTBOX_BY_NAME, dropdownLabelName);
		return checkToStatusDefaultCheckboxRadio(driver, CustomerInforPageUI.TEXTBOX_BY_NAME, dropdownLabelName);
	}
	
	public String getAttributeValueOfTextboxByName(WebDriver driver, String attributeName, String textboxLabelName) {
		waitForElementVisble(driver, CustomerInforPageUI.TEXTBOX_BY_NAME, textboxLabelName);
		return getEmlementAttribute(driver, attributeName, CustomerInforPageUI.TEXTBOX_BY_NAME, textboxLabelName);
	}
	
	public String getAttributeValueByName(WebDriver driver, String labelClass) {
		waitForElementVisble(driver, BasePageUI.TEXT_ATTRIBUTE_BY_CLASS, labelClass);
		return getElementText(driver, BasePageUI.TEXT_ATTRIBUTE_BY_CLASS, labelClass);
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
 
	
	
	
}