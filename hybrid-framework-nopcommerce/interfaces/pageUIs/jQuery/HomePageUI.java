package pageUIs.jQuery;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li/a[text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER = "xpath=//li/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String TOTAL_PAGINATION = "xpath=//ul[@class='qgrd-pagination-ul']/li";
    public static final String PAGINATION_PAGE_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";

}
