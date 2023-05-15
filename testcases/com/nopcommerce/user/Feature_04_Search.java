package com.nopcommerce.user;

import org.testng.annotations.Test;
import com.nopcommerce.commons.Common_1_Register_Cookie;
import com.nopcommerce.commons.Common_1_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.SearchPageObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Feature_04_Search extends BaseTest {
  private WebDriver driver;
  private HomePageObject homePage ;
  private LoginPageObject loginPage ;
  private SearchPageObject searchPage;
  private String  existingEmail, password;
  private String invalidData, validData, relativeName, absoluteName, valueCatogory;
  
  @Parameters({"browser","url"})
  @BeforeClass
  public void beforeClass(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  homePage = PageGeneratorManager.getHomePage(driver);
	  
	  invalidData = "Macbook pro 2050";
	  relativeName = "Lenovo";
	  absoluteName = "ThinkPad X1 Carbon";
	  valueCatogory = "Computers";
	  validData = "Apple Macbook Pro";
	  
	  existingEmail = Common_1_Register_End_User.existingEmail;
	  password = Common_1_Register_End_User.password;
	  
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
	  
	  log.info("Pre-condition - Step 05: Click to 'Search' link");
      homePage.clickToLinkInFooterByText(driver, "Search");
      searchPage = PageGeneratorManager.getSearchPage(driver);
  }
  
  @Test
  public void Search_01_Search_With_Empty_Data() {
	  log.info("Search_01 - Step 01: Click to 'Search' button");
	  searchPage.clickToSearchButton();
	  
	  log.info("Search_01 - Step 02: Verify Error Message");
	  verifyEquals(searchPage.getErrorSearchMessage(), "Search term minimum length is 3 characters");
  }
  
  @Test
  public void Search_02_Search_With_Data_That_Does_Not_Exist() {
	  log.info("Search_02 - Step 01: Input data that doesn't exist to Search textbox");
	  searchPage.inputToSearchKeywordTextbox(driver, invalidData);
	  
	  log.info("Search_02 - Step 02: Click to 'Search' button");
	  searchPage.clickToSearchButton();
	  
	  log.info("Search_02 - Step 03: Verify Error Message");
	  verifyEquals(searchPage.getErrorSearchMessage(), "No products were found that matched your criteria.");
  }
  
  @Test
  public void Search_03_Search_With_Relative_Name() {
	  log.info("Search_03 - Step 01: Input relative data to Search textbox");
	  searchPage.inputToSearchKeywordTextbox(driver, relativeName);
	  
	  log.info("Search_03 - Step 02: Click to 'Search' button");
	  searchPage.clickToSearchButton();
	  
	  log.info("Search_03 - Step 03: Verify Number of dispayed product is 2");
	  verifyEquals(searchPage.getNumberProductOfResult(), 2);
  }

  @Test
  public void Search_04_Search_With_Absolute_Name() {
	  log.info("Search_04 - Step 01: Input absolute data to Search textbox");
	  searchPage.inputToSearchKeywordTextbox(driver, absoluteName);
	  
	  log.info("Search_04 - Step 02: Click to 'Search' button");
	  searchPage.clickToSearchButton();
	  
	  log.info("Search_04 - Step 03: Verify Number of dispayed product is 1");
	  verifyEquals(searchPage.getNumberProductOfResult(), 1);
  }
  
  @Test
  public void Search_05_Search_Advance_With_Parent_Categories() {
	  log.info("Search_05 - Step 01: Input valid data to Search textbox");
	  searchPage.inputToSearchKeywordTextbox(driver, validData);
	  
	  log.info("Search_05 - Step 02: Check to Advanced search checkbox");
	  searchPage.chooseCheckboxButtonByName(driver, "Advanced search");
	  
	  log.info("Search_05 - Step 03: Choose Catogory dropdown");
	  searchPage.chooseDopdownByName(driver, "cid", valueCatogory);
	  
	  log.info("Search_05 - Step 04: Click to 'Search' button");
	  searchPage.clickToSearchButton();
	 
	  log.info("Search_05 - Step 05: Verify Error Message");
	  verifyEquals(searchPage.getErrorSearchMessage(), "No products were found that matched your criteria.");
  }
  
  @Test
  public void Search_06_Search_Advance_With_Sub_Categories() {
	  log.info("Search_06 - Step 01: Input valid data to Search textbox");
	  searchPage.inputToSearchKeywordTextbox(driver, validData);
	  
	  log.info("Search_06 - Step 02: Check to Advanced search checkbox");
	  searchPage.chooseCheckboxButtonByName(driver, "Advanced search");
	  
	  log.info("Search_05 - Step 03: Choose Catogory dropdown");
	  searchPage.chooseDopdownByName(driver, "cid", valueCatogory);
	  
	  log.info("Search_06 - Step 02: Check to Automatically search sub categories checkbox");
	  searchPage.chooseCheckboxButtonByName(driver, "Automatically search sub categories");
	  
	  log.info("Search_05 - Step 04: Click to 'Search' button");
	  searchPage.clickToSearchButton();

	  log.info("Search_06 - Step 05: Verify Number of dispayed product is 1");
	  verifyEquals(searchPage.getNumberProductOfResult(), 1);
  }
  
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  closeBrowserDriver();
  }
  
}
