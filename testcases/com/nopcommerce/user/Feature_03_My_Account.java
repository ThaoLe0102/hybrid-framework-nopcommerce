package com.nopcommerce.user;

import org.testng.annotations.Test;
import com.nopcommerce.commons.Common_1_Register_Cookie;
import com.nopcommerce.commons.Common_1_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.CustomerInforPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Feature_03_My_Account extends BaseTest {
  private WebDriver driver;
  private HomePageObject homePage ;
  private LoginPageObject loginPage ;
  private CustomerInforPageObject customerInfoPage ;
  private String  existingEmail, password, firstName, lastName, day, month, year, email, companyName;
  private String country, state, city, address1, address2, zip, phoneNumber, faxNumber;
  
  @Parameters({"browser","url"})
  @BeforeClass
  public void beforeClass(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  homePage = PageGeneratorManager.getHomePage(driver);
	  
	  existingEmail = Common_1_Register_End_User.existingEmail;
	  password = Common_1_Register_End_User.password;
	  
	  firstName = "Jerry"; 
	  lastName = "Tom";
	  day = "10";
	  month = "July";
	  year = "1997";
	  email = "automation123@gmail.com";
	  companyName = "American";
	  country = "Viet Nam"; 
	  state= "Other";
	  city= "Ha Noi";
	  address1= "15 Truong Dinh";
	  address2= "2 Pham Van Bach";
	  zip= "55000";
	  phoneNumber= "012345678";
	  faxNumber= "097412345";
	 
	  log.info("Pre-condition - Step 01: Click to 'Login' link");
      loginPage = homePage.clickToLoginLink();
	  
      log.info("Pre-condition - Step 02: Add cookie and reload");
      loginPage.getCookie(driver,Common_1_Register_Cookie.loggedCookie);
      loginPage.refreshCurrentPage(driver);
      homePage = PageGeneratorManager.getHomePage(driver);
      
      log.info("Pre-condition - Step 03: Verify MyAccount link is displayed");
	  verifyTrue(homePage.isMyAccountLinkDisplayed());
	  
	  log.info("Pre-condition - Step 04: Close success message");
	  homePage.closeSuccessMessage();
  }
  
  @Test
  public void My_Account_01_Customer_Info() {
	  log.info("My_Account_01 - Step 01: Click to My Account link");
	  customerInfoPage = homePage.clickToMyAccountLink();
	  
	  log.info("My_Account_01 - Step 02: Choose Female Radio button");
	  customerInfoPage.chooseCheckboxRadioButtonByName(driver, "Gender");
	  
	  log.info("My_Account_01 - Step 03: Input to FirstName Textbox with value = " + firstName + "");
	  customerInfoPage.inputToTextboxByName(driver, "FirstName", firstName);
	  
	  log.info("My_Account_01 - Step 04: Input to LastName Textbox with value = " + lastName + "");
	  customerInfoPage.inputToTextboxByName(driver, "LastName", lastName);
	  
	  log.info("My_Account_01 - Step 05: Choose Day dropdown with value = " + day + "");
	  customerInfoPage.chooseDopdownByName(driver, "DateOfBirthDay", day);
	  
	  log.info("My_Account_01 - Step 06: Choose Day dropdown with value = " + month + "");
	  customerInfoPage.chooseDopdownByName(driver, "DateOfBirthMonth", month);
	  
	  log.info("My_Account_01 - Step 07: Choose Day dropdown with value = " + year + "");
	  customerInfoPage.chooseDopdownByName(driver, "DateOfBirthYear", year);
	  
	  log.info("My_Account_01 - Step 08: Input to Email Textbox with value = " + email + "");
	  customerInfoPage.inputToTextboxByName(driver, "Email", email);
	  
	  log.info("My_Account_01 - Step 09: Input to Company Name Textbox with value = " + companyName + "");
	  customerInfoPage.inputToTextboxByName(driver, "Company", companyName);
	  
	  log.info("My_Account_01 - Step 10: Click to 'Save' button");
	  customerInfoPage.clickToButtonByTex(driver, "Save");
	  
	  log.info("My_Account_01 - Step 11: Verify status of Radio Button");
	  verifyTrue(customerInfoPage.checkStatusOfRadioButton(driver, "Gender"));
	  
	  log.info("My_Account_01 - Step 12: Verify value text of FirstName Textbox");
	  verifyEquals(customerInfoPage.getAttributeValueOfTextboxByName(driver, "value", "FirstName"), firstName);
	  
	  log.info("My_Account_01 - Step 13: Verify value text of LastName Textbox");
	  verifyEquals(customerInfoPage.getAttributeValueOfTextboxByName(driver, "value", "LastName"),lastName );
	  
	  log.info("My_Account_01 - Step 14: Verify value text of Day Dropdown");
	  verifyEquals(customerInfoPage.getAttributeTextOfDayDropdown(),day );
	  
	  log.info("My_Account_01 - Step 15: Verify value text of Month Dropdown");
	  verifyEquals(customerInfoPage.getAttributeTextOfMonthDropdown(),month );
	  
	  log.info("My_Account_01 - Step 16: Verify value text of Year Dropdown");
	  verifyEquals(customerInfoPage.getAttributeTextOfYearDropdown(), year);
	  
	  log.info("My_Account_01 - Step 17: Verify value text of Email Textbox");
	  verifyEquals(customerInfoPage.getAttributeValueOfTextboxByName(driver, "value", "Email"), email);
	  
	  log.info("My_Account_01 - Step 18: Verify value text of Company Name Textbox");
	  verifyEquals(customerInfoPage.getAttributeValueOfTextboxByName(driver, "value", "Company"),companyName );
  }
  
  @Test
  public void My_Account_02_Address() {
	  log.info("My_Account_02 - Step 01: Click to Address link");
	  homePage.clickToLinkInMyAccountAreaByText(driver, "Addresses");
	  customerInfoPage = PageGeneratorManager.getCustomerInforPage(driver);
	  
	  log.info("My_Account_02 - Step 02: Click to 'Add new' button");
	  customerInfoPage.clickToButtonByTex(driver, "Add new");
	  
	  log.info("My_Account_02 - Step 03: Input to FirstName Textbox with value = " + firstName + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.FirstName", firstName);
	  
	  log.info("My_Account_02 - Step 04: Input to LastName Textbox with value = " + lastName + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.LastName", lastName);
	  
	  log.info("My_Account_02 - Step 05: Input to Email Textbox with value = " + email + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.Email", email);
	 
	  log.info("My_Account_02 - Step 06: Input to Company Name Textbox with value = " + companyName + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.Company", companyName);
	  
	  log.info("My_Account_02 - Step 07: Choose Country dropdown with value = " + country + "");
	  customerInfoPage.chooseDopdownByName(driver, "Address.CountryId", country);
	  
	  log.info("My_Account_02 - Step 08: Choose State / province dropdown with value = " + state + "");
	  customerInfoPage.chooseDopdownByName(driver, "Address.StateProvinceId", state);
	  
	  log.info("My_Account_02 - Step 09: Input to City Textbox with value = " + city + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.City", city);
	  
	  log.info("My_Account_02 - Step 10: Input to Address1 Textbox with value = " + address1 + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.Address1", address1);
	  
	  log.info("My_Account_02 - Step 11: Input to Address2 Textbox with value = " + address2 + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.Address2", address2);
	  
	  log.info("My_Account_02 - Step 12: Input to Zip / postal code Textbox with value = " + zip + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.ZipPostalCode", zip);
	  
	  log.info("My_Account_02 - Step 13: Input to Phone number Textbox with value = " + phoneNumber + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.PhoneNumber", phoneNumber);
	  
	  log.info("My_Account_02 - Step 14: Input to Fax number Textbox with value = " + faxNumber + "");
	  customerInfoPage.inputToTextboxByName(driver, "Address.FaxNumber", faxNumber);
	  
	  log.info("My_Account_02 - Step 15: Click to 'Save' button");
	  customerInfoPage.clickToButtonByTex(driver, "Save");
	  
	  log.info("My_Account_02 - Step 16: Verify value text of FirstName and LastName");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "name"), firstName + " " + lastName);
	  
	  log.info("My_Account_02 - Step 18: Verify value text of Email ");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "email"), "Email: " + email);
	  
	  log.info("My_Account_02 - Step 19: Verify value text of Company Name");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "company"), companyName );
	  
	  log.info("My_Account_02 - Step 20: Verify value text of Country ");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "country"),country );
	  
	  log.info("My_Account_02 - Step 22: Verify value text of City and Zip");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "city-state-zip"), city + ", " + zip);
	  
	  log.info("My_Account_02 - Step 23: Verify value text of Address1");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "address1"), address1 );
	  
	  log.info("My_Account_02 - Step 24: Verify value text of Address2");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "address2"), address2 );
	  
	  log.info("My_Account_02 - Step 26: Verify value text of Phone number ");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "phone"), "Phone number: " + phoneNumber );
	  
	  log.info("My_Account_02 - Step 27: Verify value text of Fax number");
	  verifyEquals(customerInfoPage.getAttributeValueByName(driver, "fax"), "Fax number: " + faxNumber );
  }
  
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  closeBrowserDriver();
  }
  
}
