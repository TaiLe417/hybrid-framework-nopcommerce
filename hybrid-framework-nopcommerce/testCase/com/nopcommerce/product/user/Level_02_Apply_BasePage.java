package com.nopcommerce.product.user;

import commons.BasePage;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Level_02_Apply_BasePage extends BasePage {
    private WebDriver driver;
    Faker faker;
    private String projectPath = System.getProperty("user.dir");
    private String emailAddress;

    public Level_02_Apply_BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\hybrid-framework-nopcommerce\\browserDrivers\\chromedriver.exe");
        faker = new Faker();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");
        emailAddress = faker.internet().emailAddress();

        //Che dấu đi việc khởi tạo của 1 dối tượng
//        basePage = getBasePageObject();

    }

    @Test
    public void TC01_Register_EmptyData() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement("//a[@class='ico-register']");
        clickToElement("//button[@id='register-button']");

        Assert.assertEquals(getTextElement("//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getTextElement("//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getTextElement("//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getTextElement("//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getTextElement("//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC02_Register_Invalid() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement("//a[@class='ico-register']");
        sendKeyToElement("//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement("//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement("//input[@id='Email']", "jghsg@ghr.lkknu11");
        sendKeyToElement("//input[@id='Password']", "123456");
        sendKeyToElement("//input[@id='ConfirmPassword']", "123456");
        clickToElement("//button[@id='register-button']");
        Assert.assertEquals(getTextElement("//div[contains(@class,'message-error')]"), "Wrong email");
    }

    @Test
    public void TC03_Register_Valid() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement("//a[@class='ico-register']");
        sendKeyToElement("//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement("//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement("//input[@id='Email']", emailAddress);
        sendKeyToElement("//input[@id='Password']", "123456");
        sendKeyToElement("//input[@id='ConfirmPassword']", "123456");
        clickToElement("//button[@id='register-button']");
        Assert.assertEquals(getTextElement("//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void TC04_Register_Exist() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement("//a[@class='ico-register']");
        sendKeyToElement("//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement("//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement("//input[@id='Email']", emailAddress);
        sendKeyToElement("//input[@id='Password']", "123456");
        sendKeyToElement("//input[@id='ConfirmPassword']", "123456");
        clickToElement("//button[@id='register-button']");
        Assert.assertEquals(getTextElement("//div[contains(@class,'message-error')]"), "The specified email already exists");
    }

    @Test
    public void TC05_Register_PasswordLess6() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement("//a[@class='ico-register']");
        sendKeyToElement("//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement("//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement("//input[@id='Email']", emailAddress);
        sendKeyToElement("//input[@id='Password']", "123");
        sendKeyToElement("//input[@id='ConfirmPassword']", "123");
        Assert.assertEquals(getTextElement("//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC06_Register_ConfirmPasswordNotMatch() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement("//a[@class='ico-register']");
        sendKeyToElement("//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement("//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement("//input[@id='Email']", emailAddress);
        sendKeyToElement("//input[@id='Password']", "123456");
        sendKeyToElement("//input[@id='ConfirmPassword']", "123");
        clickToElement("//button[@id='register-button']");
        Assert.assertEquals(getTextElement("//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
