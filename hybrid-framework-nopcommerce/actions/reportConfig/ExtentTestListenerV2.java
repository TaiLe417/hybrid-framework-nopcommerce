package reportConfig;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import com.relevantcodes.extentreports.LogStatus;


public class ExtentTestListenerV2 implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
//        ExtentManagerV5.endTest();
//        ExtentManagerV5.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ExtentManagerV5.getTest().log(LogStatus.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        Object testClass = result.getInstance();
//        WebDriver webDriver = ((BaseTest) testClass).getDriverInstance();
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
//        ExtentManagerV5.getTest().log(LogStatus.FAIL, "Test Failed", ExtentManagerV5.getTest().addBase64ScreenShot(base64Screenshot));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        ExtentManagerV5.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }


}