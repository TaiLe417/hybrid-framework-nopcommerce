package com.wordpress.posts.admin;

import commons.BaseTest;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
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
    UserSearchPostPO userSearchPostPO;
    String adminUsername = "automation";
    String adminPassword = "automation";
    String postTitle;
    String editPostTitle;
    String editPostBody;
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
        editPostTitle = "Edit Title " + faker.number().randomNumber();
        editPostBody = "Edit Body " + faker.number().randomNumber();
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
        searchPostUrl = adminPostSearchPO.getCurrentUrl();

        log.info("Create Post - step 03: Click to 'Add' button");
        adminPostAddNewPO = adminPostSearchPO.clickToAddNewButton();

        log.info("Create Post - step 04: Enter to post title");
        adminPostAddNewPO.enterToAddNewPostTitle(postTitle);

        log.info("Create Post - step 05: Enter to body");
        adminPostAddNewPO.enterToAddNewPostBody(postBody);

        log.info("Create Post - step 06: Click to public button");
        adminPostAddNewPO.clickToPublishOrUpdateButton();

        log.info("Create Post - step 07: Click to pre-public button");
        adminPostAddNewPO.clickToPrePublishButton();

        log.info("Create Post - step 08: Post publish message is displayed");
        verifyTrue(adminPostAddNewPO.isPostPublishMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_And_View_Post() {
        log.info("Search - Step 01: Open 'Search Post' page");
        adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);

        log.info("Search - Step 02: Enter to Search textbox");
        adminPostSearchPO.enterToSearchTextBox(postTitle);

        log.info("Search - Step 03: Click to 'Search Pots' button");
        adminPostSearchPO.clickToSearchPostButton();

        log.info("Search - Step 04: Verify Search table contains '" + postTitle + "'");
        verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed("title", postTitle));

        log.info("Search - Step 05: Verify Search table contains '" + authorName + "'");
        verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed("author", authorName));

        log.info("Search - Step 06: Open End user site ");
        userHomePO = adminPostSearchPO.openEndUserSite(urlUser);

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
    public void Post_03_Edit_Post() {
        log.info("Edit_Post - Step 01: Open Admin site");
        adminDashboardPO = userPostDetailPO.openAdminSite(this.urlAdmin);

        log.info("Edit_Post - Step 02: Click to 'Posts' menu link");
        adminPostSearchPO = adminDashboardPO.clickToPostMenuLink();

        log.info("Edit_Post - Step 03: Enter to Search textbox");
        adminPostSearchPO.enterToSearchTextBox(postTitle);

        log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
        adminPostSearchPO.clickToSearchPostButton();

        log.info("Edit_Post - Step 05: Click to Post title link and navigate to Edit Post page");
        adminPostAddNewPO = adminPostSearchPO.clickToPostTitleLink(postTitle);

        log.info("Edit_Post - Step 06: Enter to post title");
        adminPostAddNewPO.enterToAddNewPostTitle(editPostTitle);

        log.info("Edit_Post - Step 07: Enter to post body");
        adminPostAddNewPO.enterToEditPostBody(editPostBody);

        log.info("Edit_Post - Step 08: Click Update button");
        adminPostAddNewPO.clickToPublishOrUpdateButton();

        log.info("Edit_Post - Step 09: Verify 'Post updated.' message is displayed");
        verifyTrue(adminPostAddNewPO.isPostPublishMessageDisplayed("Post updated."));

        log.info("Edit_Post - Step 10: Open 'Search Post' page");
        adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);

        log.info("Edit_Post - Step 11: Enter to Search textbox");
        adminPostSearchPO.enterToSearchTextBox(editPostTitle);

        log.info("Edit_Post - Step 12: Click to 'Search Pots' button");
        adminPostSearchPO.clickToSearchPostButton();

        log.info("Edit_Post - Step 13: Verify Search table contains '" + editPostTitle + "'");
        verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed("title", editPostTitle));

        log.info("Edit_Post - Step 14: Verify Search table contains '" + authorName + "'");
        verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed("author", authorName));

        log.info("Edit_Post - Step 15: Open End user site ");
        userHomePO = adminPostSearchPO.openEndUserSite(urlUser);

        log.info("Edit_Post - Step 16: Verify Post info displayed at Home Page");
        verifyTrue(userHomePO.isPostInfoDisplayedWithTitle(editPostTitle));
        verifyTrue(userHomePO.isPostInfoDisplayedWithBody(editPostTitle, editPostBody));
        verifyTrue(userHomePO.isPostInfoDisplayedWithAuthor(editPostTitle, authorName));
        verifyTrue(userHomePO.isPostInfoDisplayedWithDate(editPostTitle, currentDay));

        log.info("Edit_Post - Step 17: Click to Post title");
        userPostDetailPO = userHomePO.clickToPostTitle(editPostTitle);

        log.info("Edit_Post - Step 18: Verify Post info displayed at Post detail Page");
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithTitle(editPostTitle));
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithBody(editPostTitle, editPostBody));
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithAuthor(editPostTitle, authorName));
        verifyTrue(userPostDetailPO.isPostInfoDisplayedWithDate(editPostTitle, currentDay));
    }

    @Test
    public void Post_04_Delete_Post() {
        log.info("Delete_Post - Step 01: Open Admin site");
        adminDashboardPO = userPostDetailPO.openAdminSite(this.urlAdmin);

        log.info("Delete_Post - Step 02: Click to 'Posts' menu link");
        adminPostSearchPO = adminDashboardPO.clickToPostMenuLink();

        log.info("Delete_Post - Step 03: Enter to Search textbox");
        adminPostSearchPO.enterToSearchTextBox(editPostTitle);

        log.info("Delete_Post - Step 04: Click to 'Search Posts' button");
        adminPostSearchPO.clickToSearchPostButton();

        log.info("Delete_Post - Step 05: Select Post detail checkbox");
        adminPostSearchPO.selectPostCheckBoxByTitle(editPostTitle);

        log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
        adminPostSearchPO.selectTextItemInActionDropDown("Move to Trash");

        log.info("Delete_Post - Step 07: Click to 'Apply' button");
        adminPostSearchPO.clickApplyButton();

        log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
        verifyTrue(adminPostSearchPO.isMovedToTrashMessageDisplayed("1 post moved to the Trash."));

        log.info("Delete_Post - Step 09: Enter to Search textbox");
        adminPostSearchPO.enterToSearchTextBox(editPostTitle);

        log.info("Delete_Post - Step 10: Click to 'Search Posts' button");
        adminPostSearchPO.clickToSearchPostButton();

        log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
        verifyTrue(adminPostSearchPO.isNoPostsFoundMessageDisplayed("No posts found."));

        log.info("Delete_Post - Step 12 : Open End user site");
        userHomePO = adminPostSearchPO.openEndUserSite(urlUser);

        log.info("Delete_Post - Step 13: Verify Post title undisplayed at Home Page");
        verifyTrue(userHomePO.isPostInfoUndisplayedWithTitle(editPostTitle));

        log.info("Delete_Post - Step 14: Enter to Search textbox");
        userHomePO.enterToSearchTextBox(editPostTitle);

        log.info("Delete_Post - Step 15: Click to 'Search' button");
        userSearchPostPO = userHomePO.clickToSearchButton();

        log.info("Delete_Post - Step 16: Verify 'Nothing Found' message is displayed");
        verifyTrue(userSearchPostPO.isNothingFoundMessageDisplayed("Nothing Found"));
    }
    //    @AfterClass(alwaysRun = true)
//    public void afterClass() {
//        closeBrowserDriver();
//    }
}
