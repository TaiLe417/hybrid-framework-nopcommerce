package com.wordpress.posts.admin;

import commons.BaseTest;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.*;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
    private WebDriver driver;
    AdminLoginPO adminLoginPO;
    AdminDashboardPO adminDashboardPO;
    AdminPostSearchPO adminPostSearchPO;
    AdminPostAddNewPO adminPostAddNewPO;
    UserHomePO userHomePO;
    UserPostDetailPO userPostDetailPO;
    String adminUsername = "automation";
    String adminPassword = "automation";
    String postTitle;
    String postBody;
    String authorName = "Automation";
    String searchPostUrl;
    String urlAdmin, urlUser;
    String currentDay = getToday();
    Faker faker = new Faker();

    @Parameters({"browser", "urlAdmin", "urlUser"})
    @BeforeClass
    public void beforeClass(String browserName, String urlAdmin, String urlUser) {
        postTitle = "Live Coding Title " + faker.number().randomNumber();
        postBody = "Live Coding Body " + faker.number().randomNumber();
        log.info("Pre-condition - Step 01: Open browser and admin Url");
        this.urlAdmin = urlAdmin;
        this.urlUser = urlUser;
        driver = getBrowserName(browserName, urlAdmin);
        adminLoginPO = PageGeneratorManager.getAdminLoginPage(driver);
        log.info("Pre-condition - Step 02: Enter to Username textbox with value " + adminUsername);
        adminLoginPO.enterToUsernameTextbox(adminUsername);
        log.info("Pre-condition - Step 03: Enter to Password textbox with value " + adminPassword);
        adminLoginPO.enterToPasswordTextbox(adminPassword);
        log.info("Pre-condition - Step 04: Click to Login button");
        adminDashboardPO = adminLoginPO.clickToLoginButton();
    }

    @Test
    public void Post_01_CreateNewPost() {
        log.info("Create Post - step 01: Click to 'Post' menu link");
        adminPostSearchPO = adminDashboardPO.clickToPostMenuLink();

        log.info("Create Post - step 03: Get 'Search Pots' page Url");
        searchPostUrl = adminPostSearchPO.getCurrentUrl(driver);

        log.info("Create Post - step 03: Click to 'Add' button");
        adminPostAddNewPO = adminPostSearchPO.clickToAddNewButton();

        log.info("Create Post - step 04: Enter to post title");
        adminPostAddNewPO.enterToAddNewPostTitle(postTitle);

        log.info("Create Post - step 05: Enter to body");
        adminPostAddNewPO.enterToAddNewPostBody(postBody);

        log.info("Create Post - step 06: Click to public button");
        adminPostAddNewPO.clickToPublishButton();

        log.info("Create Post - step 07: Click to pre-public button");
        adminPostAddNewPO.clickToPrePublishButton();

        log.info("Create Post - step 08: Post publish message is displayed");
        Assert.assertTrue(adminPostAddNewPO.isPostPublishMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_And_View_Post() {
        log.info("Search - Step 01: Open 'Search Post' page");
        adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);

        log.info("Search - Step 02: Enter to Search textbox");
        adminPostSearchPO.enterToSearchTextBox(postTitle);

        log.info("Search - Step 03: Click to 'Search Pots' button");
        adminPostSearchPO.clickToSearchPostTextBox();

        log.info("Search - Step 04: Verify Search table contains '" + postTitle + "'");
        verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed("title", postTitle));

        log.info("Search - Step 05: Verify Search table contains '" + authorName + "'");
        verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed("author", authorName));

        log.info("Search - Step 06: Open End user site ");
        userHomePO = adminPostSearchPO.openEndUserSite(driver, urlUser);

        log.info("Search - Step 07: Verify Post info displayed at Home Page");
        verifyTrue(userHomePO.isPostInfoDisplayedWithTitle(postTitle));
        verifyTrue(userHomePO.isPostInfoDisplayedWithBody(postTitle, postBody));
        verifyTrue(userHomePO.isPostInfoDisplayedWithAuthor(postTitle, authorName));
        verifyTrue(userHomePO.isPostInfoDisplayedWithDate(postTitle, currentDay));

        log.info("Search - Step 08: Click to Post title");
        userPostDetailPO = userHomePO.clickToPostTitle(postTitle);

        log.info("Search - Step 09: Verify Post info displayed at Post detail Page");
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithTitle(postTitle));
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithBody(postTitle, postBody));
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithAuthor(postTitle, authorName));
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithDate(postTitle, currentDay));
    }

    @Test
    public void Post_03_View_Post() {

    }

    @Test
    public void Post_04_Edit_Post() {

    }

    @Test
    public void Post_05_Delete_Post() {

    }

    //    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
