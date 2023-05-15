package com.nopcommerce.user;

import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Feature_02_Login extends BaseTest {
  private WebDriver driver;
  private HomePageObject homePage ;
  private RegisterPageObject registerPage ;
  private LoginPageObject loginPage ;
  private String  invalidEmail, existingEmail, unregisteredEmail, invalidPassword, validPassword, firstName, lastName;
  
  @Parameters({"browser","url"})
  @BeforeClass
  public void beforeClass(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  homePage = PageGeneratorManager.getHomePage(driver);
	  
	  firstName ="automation";
	  lastName ="FC";
	  validPassword = "abc1234";
	  existingEmail = "abc"+ generateFakeNumber() + "@gmail.com";
	  invalidEmail = "abc@123";
	  unregisteredEmail = "abc123@gmail.com";
	  invalidPassword = "1234";
	  
	  log.info("Pre-condition - Step 01: Navigate to 'Register' Page");
	  registerPage = homePage.clickToRegisterLink();
	  
	  log.info("Pre-condition - Step 02: Enter to Firstname textbox with value is'" + firstName + "'");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Pre-condition - Step 03: Enter to Lastname textbox with value is'" + lastName + "'");
	  registerPage.inputToLastNameTextbox(lastName);
	  
	  log.info("Pre-condition - Step 04: Enter to Email textbox with value is'" + existingEmail + "'");
	  registerPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Pre-condition - Step 05: Enter to Password textbox with value is'" + validPassword + "'");
	  registerPage.inputToPasswordTextbox(validPassword);
	  
	  log.info("Pre-condition - Step 06: Enter to ConfirmPassword textbox with value is'" + validPassword + "'");
	  registerPage.inputToConfirmPasswordTextbox(validPassword);
	  
	  log.info("Pre-condition - Step 07: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Pre-condition - Step 08: Verify Register success message is displayed");
	  verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
//	  log.info("Pre-condition - Step 08: Click to Logout link");
//	  registerPage.clickToLogoutLink();
  }
  
  @Test
  public void Login_01_Empty_Data() {
	  log.info("Login_01 - Step 01: Click to 'Login' link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("Login_01 - Step 02: Click to 'Login' button");
	  loginPage.clickToLoginButton();
	  
	  log.info("Login_01 - Step 03: Verify error message at Email textbox is displayed");
	  verifyEquals(loginPage.getWarningNotInputDataMessage(), "Please enter your email");
	  
  }
  
  @Test
  public void Login_02_Invalid_Email() {
	  log.info("Login_02 - Step 01: Click to 'Login' link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("Login_02 - Step 02: Enter to Invalid Email textbox with value "+ invalidEmail + "");
	  loginPage.inputToEmailTextbox(invalidEmail);
	  
	  log.info("Login_02 - Step 03: Enter to Password textbox with value "+ validPassword + "");
	  loginPage.inputToPasswordTextbox(validPassword);
	  
	  log.info("Login_02 - Step 04: Click to 'Login' button");
	  loginPage.clickToLoginButton();
	  
	  log.info("Login_02 - Step 05: Verify Wrong Email message is displayed");
	  verifyEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	  
  }
  
  @Test
  public void Login_03_Login_With_Unregistered_Email() {
	  log.info("Login_03 - Step 01: Click to 'Login' link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("Login_03 - Step 02: Enter to Invalid Email textbox with value "+ unregisteredEmail + "");
	  loginPage.inputToEmailTextbox(unregisteredEmail);
	  
	  log.info("Login_03 - Step 03: Enter to Password textbox with value "+ validPassword + "");
	  loginPage.inputToPasswordTextbox(validPassword);
	  
	  log.info("Login_03 - Step 04: Click to 'Login' button");
	  loginPage.clickToLoginButton();
	  
	  log.info("Login_03 - Step 05: Verify error login message is displayed");
	  verifyEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	  
  }
  
  @Test
  public void Login_04_Login_With_Existing_Email_And_Not_Input_To_Password() {
	  log.info("Login_04 - Step 01: Click to 'Login' link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("Login_04 - Step 02: Not enter to Invalid Email textbox with value "+ existingEmail + "");
	  loginPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Login_04 - Step 03: Click to 'Login' button");
	  loginPage.clickToLoginButton();
	  
	  log.info("Login_04 - Step 04: Verify Wrong Email message is displayed");
	  verifyEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	  
  }

  @Test
  public void Login_05_Login_With_Existing_Email_And_Incorrect_Password() {
	  log.info("Login_05 - Step 01: Click to 'Login' link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("Login_05 - Step 02: Enter to Invalid Email textbox with value "+ existingEmail + "");
	  loginPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Login_05 - Step 03: Enter to Password textbox with value "+ invalidPassword + "");
	  loginPage.inputToPasswordTextbox(invalidPassword);
	  
	  log.info("Login_05 - Step 04: Click to 'Login' button");
	  loginPage.clickToLoginButton();
	  
	  log.info("Login_05 - Step 05: Verify Wrong Email message is displayed");
	  verifyEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	  
  }
  
  @Test
  public void Login_06_Login_Success() {
	  log.info("Login_05 - Step 01: Click to 'Login' link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("Login_05 - Step 02: Enter to Invalid Email textbox with value "+ existingEmail + "");
	  loginPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Login_05 - Step 03: Enter to Password textbox with value "+ validPassword + "");
	  loginPage.inputToPasswordTextbox(validPassword);
	  
	  log.info("Login_05 - Step 04: Click to 'Login' button");
	  loginPage.clickToLoginButton();
	  
	  log.info("Login_05 - Step 05: Verify My Account link is displayed");
	  verifyTrue(loginPage.isMyAccountLinkDisplayed());
  }
  
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  closeBrowserDriver();
  }
  
}
