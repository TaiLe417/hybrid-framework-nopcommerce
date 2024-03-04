package pageUIs.wordpress;

public class AdminPostAddNewPageUI {
    public static final String TITLE_TEXTBOX = "xpath=//h1[contains(@class,'wp-block-post-title')]";
    public static final String BODY_BUTTON = "xpath=//p[contains(@class,'block-editor-default')]";
    public static final String BODY_TEXTBOX = "xpath=//p[contains(@class,'block-editor-rich-text')]";
    public static final String PUBLISH_BUTTON = "xpath=//button[text()='Publish']";
    public static final String PRE_PUBLISH_BUTTON = "xpath=//div[@aria-label='Editor publish']//button[text()='Publish']";
    public static final String PUBLISHED_MESSAGE = "xpath=//div[@class='components-snackbar__content' and text()='%s']";


}
