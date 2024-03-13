package pageObjects.saurceLab;

import org.openqa.selenium.WebDriver;
import pageObjects.wordpress.*;

public class PageGeneratorManager {
    public static LoginPO getLoginPage(WebDriver driver) {
        return new LoginPO(driver);
    }

    public static ProductPO getProductPage(WebDriver driver) {
        return new ProductPO(driver);
    }

}
