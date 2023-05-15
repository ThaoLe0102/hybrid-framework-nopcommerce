package com.nopcommerce.user;

import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Feature_01_Register extends BaseTest {
  private WebDriver driver;
  private HomePageObject homePage ;
  private RegisterPageObject registerPage ;
  private String  invalidEmail, existingEmail, invalidPassword, password, firstName, lastName;
  
  @Parameters({"browser","url"})
  @BeforeClass
  public void beforeClass(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  homePage = PageGeneratorManager.getHomePage(driver);
	  
	  firstName ="automation";
	  lastName ="FC";
	  password = "abc1234";
	  existingEmail = "abc"+ generateFakeNumber() + "@gmail.com";
	  invalidEmail = "abc@123";
	  invalidPassword = "1234";
	  
  }
  
  @Test
  public void Register_01_Empty_Data() {
	  log.info("Register_01 - Step 01: Click to 'Register' link");
	  registerPage = homePage.clickToRegisterLink();
	  
	  log.info("Register_01 - Step 02: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register - Step 03: Verify error message at FirstName textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
	  
	  log.info("Register - Step 04: Verify error message at LastName textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
	  
	  log.info("Register - Step 05: Verify error message at Email textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
	  
	  log.info("Register - Step 06: Verify error message at Password textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
	  
	  log.info("Register - Step 07: Verify error message at  Confirm Password textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	  
  }
  
  
  @Test
  public void Register_02_Invalid_Email() {
	  log.info("Register_02 - Step 01: Click to Register link");
	  registerPage = homePage.clickToRegisterLink();
	  
	  log.info("Register_02 - Step 02: Enter to FirstName textbox with value "+ firstName + "");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Register_02 - Step 03: Enter to LastName textbox with value "+ lastName + "");
	  registerPage.inputToLastNameTextbox(firstName);
	  
	  log.info("Register_02 - Step 04: Enter to Invalid Email textbox with value "+ invalidEmail + "");
	  registerPage.inputToEmailTextbox(invalidEmail);
	  
	  log.info("Register_02 - Step 05: Enter to Password textbox with value "+ password + "");
	  registerPage.inputToPasswordTextbox(password);
	  
	  log.info("Register_02 - Step 06: Enter to Confirm Password textbox with value "+ password + "");
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  log.info("Register_02 - Step 06: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register_02 - Step 05: Verify error message at Email textbox is displayed");
	  verifyEquals(registerPage.getWrongEmailMessage(), "Wrong email");
	  
  }
  
  @Test
  public void Register_03_Success() {
	  log.info("Register_03 - Step 01: Navigate to 'Register' Page");
	  registerPage = homePage.clickToRegisterLink();
	  
	  log.info("Register_03 - Step 02: Enter to Firstname textbox with value is'" + firstName + "'");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Register_03 - Step 03: Enter to Lastname textbox with value is'" + lastName + "'");
	  registerPage.inputToLastNameTextbox(lastName);
	  
	  log.info("Register_03 - Step 04: Enter to Email textbox with value is'" + existingEmail + "'");
	  registerPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Register_03 - Step 05: Enter to Password textbox with value is'" + password + "'");
	  registerPage.inputToPasswordTextbox(password);
	  
	  log.info("Register_03 - Step 06: Enter to ConfirmPassword textbox with value is'" + password + "'");
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  log.info("Register_03 - Step 07: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register_03 - Step 08: Verify Register success message is displayed");
	  verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
//	  log.info("Register_03 - Step 08: Click to Logout link");
//	  registerPage.clickToLogoutLink();
  }
  
  @Test
  public void Register_04_Existing_Email() {
	  log.info("Register_04 - Step 01: Navigate to 'Register' Page");
	  homePage.clickToRegisterLink();
	  
	  log.info("Register_04 - Step 02: Enter to FirstName textbox with value "+ firstName + "");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Register_04 - Step 03: Enter to LastName textbox with value "+ lastName + "");
	  registerPage.inputToLastNameTextbox(firstName);
	  
	  log.info("Register_04 - Step 04: Enter to Existing  Email textbox with value "+ existingEmail + "");
	  registerPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Register_04 - Step 05: Enter to Password textbox with value "+ password + "");
	  registerPage.inputToPasswordTextbox(password);
	  
	  log.info("Register_04 - Step 06: Enter to Confirm Password textbox with value "+ password + "");
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  log.info("Register_04 - Step 06: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register_04 - Step 05: Verify error existing Email message is displayed");
	  verifyEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	  
  }

  @Test
  public void Register_05_Password_Less_Than_6_Charts() {
	  log.info("Register_05 - Step 01: Navigate to 'Register' Page");
	  homePage.clickToRegisterLink();
	  
	  log.info("Register_05 - Step 02: Enter to FirstName textbox with value "+ firstName + "");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Register_05 - Step 03: Enter to LastName textbox with value "+ lastName + "");
	  registerPage.inputToLastNameTextbox(firstName);
	  
	  log.info("Register_05 - Step 04: Enter to Existing  Email textbox with value "+ existingEmail + "");
	  registerPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Register_05 - Step 05: Enter to Password textbox with value "+ invalidPassword + "");
	  registerPage.inputToPasswordTextbox(invalidPassword);
	  
	  log.info("Register_05 - Step 06: Enter to Confirm Password textbox with value "+ invalidPassword + "");
	  registerPage.inputToConfirmPasswordTextbox(invalidPassword);
	  
	  log.info("Register_05 - Step 06: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register_05 - Step 05: Verify error message at Password textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	  
  }
  
  @Test
  public void Register_06_Invalid_Confirm_Password() {
	  log.info("Register_06 - Step 01: Navigate to 'Register' Page");
	  homePage.clickToRegisterLink();
	  
	  log.info("Register_06 - Step 02: Enter to FirstName textbox with value "+ firstName + "");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Register_06 - Step 03: Enter to LastName textbox with value "+ lastName + "");
	  registerPage.inputToLastNameTextbox(firstName);
	  
	  log.info("Register_06 - Step 04: Enter to Existing  Email textbox with value "+ existingEmail + "");
	  registerPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Register_06 - Step 05: Enter to Password textbox with value "+ password + "");
	  registerPage.inputToPasswordTextbox(password);
	  
	  log.info("Register_06 - Step 06: Enter to Confirm Password textbox with value "+ invalidPassword + "");
	  registerPage.inputToConfirmPasswordTextbox(invalidPassword);
	  
	  log.info("Register_06 - Step 06: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register_06 - Step 05: Verify error message at Confirm Password textbox is displayed");
	  verifyEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	  
  }
  
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  closeBrowserDriver();
  }
  
}
