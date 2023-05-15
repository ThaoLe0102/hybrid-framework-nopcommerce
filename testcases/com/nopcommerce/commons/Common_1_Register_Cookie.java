package com.nopcommerce.commons;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class Common_1_Register_Cookie extends BaseTest {
  private HomePageObject homePage ;
  private RegisterPageObject registerPage ;
  private LoginPageObject loginPage;

  private WebDriver driver;
  public static String  existingEmail, password;
  private String  firstName, lastName;
  public static Set<Cookie> loggedCookie;
  
 
  @Parameters({"browser","url"})
  @BeforeTest(description =" Create new User for all class")
  public void Register(String browserName, String appUrl) {
		  driver = getBrowserDriver(browserName, appUrl);
		  homePage = PageGeneratorManager.getHomePage(driver);
		  
		  firstName ="automation";
		  lastName ="FC";
		  password = "abc1234";
		  existingEmail = "abc"+ generateFakeNumber() + "@gmail.com";
		  
	  log.info("Register - Step 01: Navigate to 'Register' Page");
	  registerPage = homePage.clickToRegisterLink();
	  
	  log.info("Register - Step 02: Enter to Firstname textbox with value is'" + firstName + "'");
	  registerPage.inputToFirstNameTextbox(firstName);
	  
	  log.info("Register - Step 03: Enter to Lastname textbox with value is'" + lastName + "'");
	  registerPage.inputToLastNameTextbox(lastName);
	  
	  log.info("Register - Step 04: Enter to Email textbox with value is'" + existingEmail + "'");
	  registerPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Register - Step 05: Enter to Password textbox with value is'" + password + "'");
	  registerPage.inputToPasswordTextbox(password);
	  
	  log.info("Register - Step 06: Enter to ConfirmPassword textbox with value is'" + password + "'");
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  log.info("Register - Step 07: Click to 'Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register - Step 08: Verify Register success message is displayed");
	  verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
//	  log.info("Register - Step 09: Click to Logout link");
//	  registerPage.clickLogoutLink();
	  
	  log.info("Register - Step 09: Click to 'Login' link");
      loginPage = registerPage.clickToLoginLink();
	  
      log.info("Register - Step 10: Enter to Email textbox with value is'" + existingEmail + "'");
	  loginPage.inputToEmailTextbox(existingEmail);
	  
	  log.info("Register - Step 11: Enter to Password textbox with value is'" + password + "'");
	  loginPage.inputToPasswordTextbox(password);
	  
	  log.info("Register - Step 12: Click to 'Login' button");
	  homePage = loginPage.clickToLoginButton();
	  
	  loggedCookie = homePage.setAllCookies(driver);
	  
	  driver.quit();
  } 
}
