package pageUIs.nopCommerce.user;

public class UserBasePageUI {
    public static final String CUSTOMER_INFO_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Customer info')]";
    public static final String ADDRESS_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Addresses')]";
    public static final String ORDER_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Orders')]";
    public static final String DOWNLOADABLE_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Downloadable products')]";
    public static final String BACK_IN_STORE_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Back in stock subscriptions')]";
    public static final String REWARD_POINTS_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Reward points')]";
    public static final String CHANGE_PASSWORD_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'Change password')]";
    public static final String MY_PRODUCT_REVIEWS_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'My product reviews')]";

    public static final String DYNAMIC_MY_ACCOUNT_LINK = "xpath=//div[@class='listbox']//a[contains(text(),'%s')]";
    public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
    public static final String DYNAMIC_RADIOBUTTON_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(),'%s')]/following-sibling::input";
}
