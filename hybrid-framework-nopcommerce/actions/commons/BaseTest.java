package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class BaseTest {
    private final String projectPath = System.getProperty("user.dir");
    private WebDriver driver;

    protected WebDriver getBrowserName(String browserName) {
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\hybrid-framework-nopcommerce\\browserDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\hybrid-framework-nopcommerce\\browserDrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("edge")) {
            System.setProperty("webdriver.edge.driver", projectPath + "\\hybrid-framework-nopcommerce\\browserDrivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser Name Invalid");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");
        return driver;
    }
}
