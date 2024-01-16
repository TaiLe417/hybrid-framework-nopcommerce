package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;

    protected WebDriver getBrowserName(String browserName) {
        if (browserName.equals("chrome")) {
            driver = WebDriverManager.chromedriver().create();
        } else if (browserName.equals("firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
        } else if (browserName.equals("edge")) {
            driver = WebDriverManager.edgedriver().create();
        } else {
            throw new RuntimeException("Browser Name Invalid");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(GlobalConstants.USER_URL);
        return driver;
    }
}
