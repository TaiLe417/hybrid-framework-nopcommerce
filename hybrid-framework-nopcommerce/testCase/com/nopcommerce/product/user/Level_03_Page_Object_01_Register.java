package com.nopcommerce.product.user;

import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.time.Duration;

public class Level_03_Page_Object_01_Register {
    private WebDriver driver;
    private final String projectPath = System.getProperty("user.dir");
    private String emailAddress, firstName, lastName, password;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\hybrid-framework-nopcommerce\\browserDrivers\\chromedriver.exe");
        System.out.println(projectPath);
        Faker faker = new Faker();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");

        emailAddress = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        password = "123456";
        userHomePageObject = new UserHomePageObject(driver);
        userRegisterPageObject = new UserRegisterPageObject(driver);
    }

    @Test
    public void Register_01_EmptyData() {
        System.out.println("Register_01 Step 1: Click Register");
        userHomePageObject.clickRegisterLink();

        System.out.println("Register_01 Step 2: Click Register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Register_01 Step 3: Verify error message displayed");
        Assert.assertEquals(userRegisterPageObject.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(userRegisterPageObject.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(userRegisterPageObject.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(userRegisterPageObject.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(userRegisterPageObject.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void Register_02_Invalid() {
        System.out.println("Register_02 Step 1: Click Register");
        userHomePageObject.clickRegisterLink();

        System.out.println("Register_02 Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox("jghsg@ghr.lkknu11");
        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_02 Step 3: Click register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Register_02 Step 4: Verify email error message displayed");
        Assert.assertEquals(userRegisterPageObject.getWrongEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void Register_03_Valid() {
        System.out.println("Register_03 Step 1: Click Register");
        userHomePageObject.clickRegisterLink();

        System.out.println("Register_03 Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_03 Step 3: Click register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Register_03 Step 4: Verify register success message displayed");
        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void Register_04_Exist() {
        System.out.println("Register_04 Step 1: Click Register");
        userHomePageObject.clickRegisterLink();

        System.out.println("Register_04 Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_04 Step 3: Click register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Register_04 Step 4: Verify mail already exists message displayed");
        Assert.assertEquals(userRegisterPageObject.getEmailExistMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_PasswordLess6() {
        System.out.println("Register_05 Step 1: Click Register");
        userHomePageObject.clickRegisterLink();

        System.out.println("Register_05 Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToPasswordTextbox("123");
        userRegisterPageObject.inputToConfirmPasswordTextbox("123");


        System.out.println("Register_05 Step 3: Verify password error message displayed");
        Assert.assertEquals(userRegisterPageObject.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void Register_06_ConfirmPasswordNotMatch() {
        System.out.println("Register_06 Step 1: Click Register");
        userHomePageObject.clickRegisterLink();

        System.out.println("Register_06 Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToConfirmPasswordTextbox("111");

        System.out.println("Register_06 Step 3: Click register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Register_06 Step 4: Verify confirm password error message displayed");
        Assert.assertEquals(userRegisterPageObject.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
