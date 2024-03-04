package pageUIs.wordpress;

public class AdminPostSearchPageUI {
    public static final String ADD_NEW_BUTTON = "xpath=//a[@class='page-title-action']";
    public static final String SEARCH_TEXTBOX = "id=post-search-input";
    public static final String SEARCH_BUTTON = "id=search-submit";
    public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME = "xpath=//table[contains(@class,'table-view-list posts')]/thead//th[@id='%s']/preceding-sibling::*";
    public static final String TABLE_ROW_INDEX_BY_HEADER_INDEX = "xpath=//table[contains(@class,'table-view-list posts')]/tbody/tr/*[%s]//a[text()='%s']";
}
