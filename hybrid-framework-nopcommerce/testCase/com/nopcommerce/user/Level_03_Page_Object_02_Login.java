package com.nopcommerce.user;

import commons.BasePage;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;

public class Level_03_Page_Object_02_Login {
    private WebDriver driver;
    private final String projectPath = System.getProperty("user.dir");
    private String emailAddress, firstName, lastName, password, emailNotFound, unsuccessfulMessage;
    private HomePageObject homePageObject;
    private RegisterPageObject registerPageObject;
    private LoginPageObject loginPageObject;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\hybrid-framework-nopcommerce\\browserDrivers\\chromedriver.exe");
        System.out.println(projectPath);
        Faker faker = new Faker();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");

        emailAddress = faker.internet().emailAddress();
        emailNotFound = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        password = "123456";
        unsuccessfulMessage = "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect";
        homePageObject = new HomePageObject(driver);
        registerPageObject = new RegisterPageObject(driver);

        System.out.println("Pre-condition Step 1: Click Register link");
        homePageObject.clickRegisterLink();

        System.out.println("Pre-condition Step 2: Input data into textbox");
        registerPageObject.inputToFirstNameTextbox(firstName);
        registerPageObject.inputToLastNameTextbox(lastName);
        registerPageObject.inputToEmailTextbox(emailAddress);
        registerPageObject.inputToPasswordTextbox(password);
        registerPageObject.inputToConfirmPasswordTextbox(password);

        System.out.println("Pre-condition Step 3: Click register button");
        registerPageObject.clickRegisterButton();

        System.out.println("Pre-condition Step 4: Verify register success message displayed");
        Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");

//        System.out.println("Pre-condition Step 5: Log out");
//        Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");

        loginPageObject = new LoginPageObject(driver);
    }

    @Test
    public void Login_01_EmptyData() {
        System.out.println("Login_01 Step 1: Click log in link");
        homePageObject.clickLogInLink();

        System.out.println("Login_01 Step 2: Click log in button");
        loginPageObject.clickLogInButton();

        System.out.println("Login_01 Step 3: Verify email error message displayed");
        Assert.assertEquals(loginPageObject.getEmailErrorMessage(), "Please enter your email");
    }

    @Test
    public void Login_02_InvalidData() {
        System.out.println("Login_02 Step 1: Click log in link");
        homePageObject.clickLogInLink();

        System.out.println("Login_02 Step 2: Input invalid mail");
        loginPageObject.inputToEmailTextBox("lksjnkn@.mnjs.inc");

        System.out.println("Login_02 Step 3: Click log in button");
        loginPageObject.clickLogInButton();

        System.out.println("Login_02 Step 4: Verify wrong email error message displayed");
        Assert.assertEquals(loginPageObject.getEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void Login_03_EmailNotFound() {
        System.out.println("Login_03 Step 1: Click log in link");
        homePageObject.clickLogInLink();

        System.out.println("Login_03 Step 2: Input mail unregister");
        loginPageObject.inputToEmailTextBox(emailNotFound);

        System.out.println("Login_03 Step 3: Click log in button");
        loginPageObject.clickLogInButton();

        System.out.println("Login_03 Step 4: Verify unsuccessful error message displayed");
        Assert.assertEquals(loginPageObject.getUnsuccessfulErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void Login_04_ExistEmailEmptyPassword() {
        System.out.println("Login_04 Step 1: Click log in link");
        homePageObject.clickLogInLink();

        System.out.println("Login_04 Step 2: Input mail");
        loginPageObject.inputToEmailTextBox(emailAddress);

        System.out.println("Login_04 Step 3: Click log in button");
        loginPageObject.clickLogInButton();

        System.out.println("Login_04 Step 4: Verify unsuccessful error message displayed");
        Assert.assertEquals(loginPageObject.getUnsuccessfulErrorMessage(), unsuccessfulMessage);
    }

    @Test
    public void Login_05_ExistEmailWrongPassword() {
        System.out.println("Login_05 Step 1: Click log in link");
        homePageObject.clickLogInLink();

        System.out.println("Login_05 Step 2: Input mail");
        loginPageObject.inputToEmailTextBox(emailAddress);

        System.out.println("Login_05 Step 3: Input password");
        loginPageObject.inputToPasswordTextBox("123321");

        System.out.println("Login_05 Step 2: Click log in button");
        loginPageObject.clickLogInButton();

        System.out.println("Login_05 Step 3: Verify unsuccessful error message displayed");
        Assert.assertEquals(loginPageObject.getUnsuccessfulErrorMessage(), unsuccessfulMessage);
    }

    @Test
    public void Login_06_ValidEmailPassword() {
        System.out.println("Login_06 Step 1: Click log in link");
        homePageObject.clickLogInLink();

        System.out.println("Login_06 Step 2: Input mail");
        loginPageObject.inputToEmailTextBox(emailAddress);

        System.out.println("Login_06 Step 3: Input password");
        loginPageObject.inputToPasswordTextBox(password);

        System.out.println("Login_06 Step 4: Click log in button");
        loginPageObject.clickLogInButton();

        System.out.println("Login_06 Step 5: Verify login successful");
        Assert.assertTrue(homePageObject.isMyAccountDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
