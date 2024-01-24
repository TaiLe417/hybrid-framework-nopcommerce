package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.*;

public class PageGeneratorManager {

    public static UserLoginPageObject getLoginPageObject(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserHomePageObject getUserHomePageObject(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserAddressPageObject getUserAddressPageObject(WebDriver driver) {
        return new UserAddressPageObject(driver);
    }

    public static UserCustomerInfoPageObject getUserCustomerInfoPageObject(WebDriver driver) {
        return new UserCustomerInfoPageObject(driver);
    }

    public static UserRewardPointPageObject getUserRewardPointPageObject(WebDriver driver) {
        return new UserRewardPointPageObject(driver);
    }

    public static UserMyProductReviewPageObject getUserMyProductReviewPageObject(WebDriver driver) {
        return new UserMyProductReviewPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPageObject(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    public static AdminDashboardPageObject getAdminDashboardPageObject(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }


}
