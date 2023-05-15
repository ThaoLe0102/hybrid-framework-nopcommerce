package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerInforPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchPageObject;

public class PageGeneratorManager {
 
	public static HomePageObject getHomePage (WebDriver driver) {
	 return new HomePageObject(driver);
 }
	
	public static LoginPageObject getLoginPage (WebDriver driver) {
		 return new LoginPageObject(driver);
	 }
	
	public static RegisterPageObject getRegisterPage (WebDriver driver) {
		 return new RegisterPageObject(driver);
	 }
	
	public static CustomerInforPageObject getCustomerInforPage (WebDriver driver) {
		 return new CustomerInforPageObject(driver);
	 }
	
	public static AddressPageObject getAddressPage (WebDriver driver) {
		 return new AddressPageObject(driver);
	 }

	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}
	
}
