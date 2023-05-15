package pageUIs.user;

public class BasePageUI {

	public static final String BUTTON_BY_TEXT = "//button[text()='%s']";
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA_BY_TEXT = "//div[contains(@class,\"account-navigation\")]//a[text()='%s']";
	public static final String DYNAMIC_PAGE_AT_CATOGORIES_AREA_BY_TEXT = "//div[@class=\"block block-category-navigation\"]//a[text()='%s']";
	public static final String DYNAMIC_PAGE_AT_HEADER_AREA_BY_TEXT = "//ul[@class=\"top-menu notmobile\"]//a[text()='%s']";
	public static final String DYNAMIC_PAGE_AT_FOOTER_AREA_BY_TEXT = "//div[@class=\"footer-upper\"]//a[text()='%s']";
	public static final String TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String TEXT_ATTRIBUTE_BY_CLASS = "//li[@class='%s']";
}
