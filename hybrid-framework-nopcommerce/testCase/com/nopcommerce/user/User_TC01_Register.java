package com.nopcommerce.user;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class User_TC01_Register {
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

    }

    @Test
    public void TC01_Register_EmptyData() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.id("FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.id("LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "Password is required.");
    }

    @Test
    public void TC02_Register_Invalid() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("LastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("Email")).sendKeys("jghsg@ghr.lkknu11");
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'message-error')]")).getText(), "Wrong email");
    }

    @Test
    public void TC03_Register_Valid() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("LastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
    }

    @Test
    public void TC04_Register_Exist() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("LastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'message-error')]")).getText(), "The specified email already exists");
    }

    @Test
    public void TC05_Register_PasswordLess6() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("LastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys("123");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123");
        Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC06_Register_ConfirmPasswordNotMatch() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("LastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("321");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
