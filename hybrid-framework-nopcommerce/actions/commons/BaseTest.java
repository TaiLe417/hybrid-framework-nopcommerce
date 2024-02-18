package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    protected final Log log;

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
//        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(GlobalConstants.USER_URL);
        return driver;
    }

    protected WebDriver getBrowserName(String browserName, String url) {
        if (browserName.equals("chrome")) {
            driver = WebDriverManager.chromedriver().create();
        } else if (browserName.equals("firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
        } else if (browserName.equals("edge")) {
            driver = WebDriverManager.edgedriver().create();
        } else {
            throw new RuntimeException("Browser Name Invalid");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(url);
        return driver;
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("--------------------PASSED--------------------");
        } catch (Throwable e) {
            log.info("--------------------FAILED--------------------");
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("--------------------PASSED--------------------");
        } catch (Throwable e) {
            log.info("--------------------FAILED--------------------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("--------------------PASSED--------------------");
        } catch (Throwable e) {
            log.info("--------------------FAILED--------------------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + "hybrid-framework-nopcommerce" + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
