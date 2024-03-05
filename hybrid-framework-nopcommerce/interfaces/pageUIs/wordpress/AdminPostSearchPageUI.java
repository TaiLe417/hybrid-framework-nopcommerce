package pageUIs.wordpress;

public class AdminPostSearchPageUI {
    public static final String ADD_NEW_BUTTON = "xpath=//a[@class='page-title-action']";
    public static final String SEARCH_TEXTBOX = "id=post-search-input";
    public static final String SEARCH_BUTTON = "id=search-submit";
    public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME = "xpath=//table[contains(@class,'table-view-list posts')]/thead//th[@id='%s']/preceding-sibling::*";
    public static final String TABLE_ROW_INDEX_BY_HEADER_INDEX = "xpath=//table[contains(@class,'table-view-list posts')]/tbody/tr/*[%s]//a[text()='%s']";
    public static final String TABLE_ROW_TITLE_DETAIL_BY_TITLE_NAME = "xpath=//table[contains(@class,'table-view-list posts')]/tbody/tr//a[@class='row-title' and text()='%s']";
    public static final String ROW_CHECK_BOX_BY_TITLE_NAME = "xpath=//table[contains(@class,'table-view-list posts')]/tbody/tr/th/label[contains(string(),'%s')]/preceding-sibling::input";
    public static final String ACTION_DROPDOWN = "xpath=//select[@id='bulk-action-selector-top']";
    public static final String APPLY_BUTTON = "xpath=//input[@id='doaction']";
    public static final String MOVE_TO_TRASH_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'%s')]";
    public static final String NO_POSTS_FOUND_MESSAGE = "xpath=//table[contains(@class,'table-view-list posts')]//tr/td[text()='%s']";
}
