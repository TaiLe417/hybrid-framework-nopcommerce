package com.wordpress.posts.admin;

import commons.BaseTest;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.admin.*;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
    private WebDriver driver;
    AdminLoginPO adminLoginPO;
    AdminDashboardPO adminDashboardPO;
    AdminPostSearchPO adminPostSearchPO;
    AdminPostAddNewPO adminPostAddNewPO;
    String adminUsername = "automation";
    String adminPassword = "automation";
    String postTitle;
    String postBody;
    String searchPostUrl;
    Faker faker = new Faker();

    @Parameters({"browser", "urlAdmin"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        postTitle = "Live Coding Title " + faker.number().randomDigit();
        postBody = "Live Coding Body " + faker.number().randomDigit();

        log.info("Pre-condition - Step 01: Open browser and admin Url");
        driver = getBrowserName(browserName, url);
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
        verifyTrue(adminPostAddNewPO.isPostPublishMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_Post() {
        log.info("Search - Step 01: Open 'Search Post' page");
        adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);

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
