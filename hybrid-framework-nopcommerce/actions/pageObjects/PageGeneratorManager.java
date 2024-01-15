package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPageObject(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static HomePageObject getHomePageObject(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static AddressPageObject getAddressPageObject(WebDriver driver) {
        return new AddressPageObject(driver);
    }

    public static CustomerInfoPageObject getCustomerInforPageObject(WebDriver driver) {
        return new CustomerInfoPageObject(driver);
    }

    public static RewardPointPageObject getRewardPointPageObject(WebDriver driver) {
        return new RewardPointPageObject(driver);
    }

    public static MyProductReviewPageObject getMyProductReviewPageObject(WebDriver driver) {
        return new MyProductReviewPageObject(driver);
    }


}
