package com.nopcommerce.user;

import commons.BasePage;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class User_TC01_Register extends BasePage {
    WebDriver driver;
    Faker faker;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;
    

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
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getTextElement(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getTextElement(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getTextElement(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getTextElement(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getTextElement(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC02_Register_Invalid() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement(driver, "//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement(driver, "//input[@id='Email']", "jghsg@ghr.lkknu11");
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getTextElement(driver, "//div[contains(@class,'message-error')]"), "Wrong email");
    }

    @Test
    public void TC03_Register_Valid() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement(driver, "//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getTextElement(driver, "//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void TC04_Register_Exist() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement(driver, "//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getTextElement(driver, "//div[contains(@class,'message-error')]"), "The specified email already exists");
    }

    @Test
    public void TC05_Register_PasswordLess6() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement(driver, "//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeyToElement(driver, "//input[@id='Password']", "123");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
        Assert.assertEquals(getTextElement(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC06_Register_ConfirmPasswordNotMatch() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", faker.name().firstName());
        sendKeyToElement(driver, "//input[@id='LastName']", faker.name().lastName());
        sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getTextElement(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
