package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ScreenshotHelper;

public class TestListener implements ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(">>> LISTENER CALLED: " + result.getMethod().getMethodName());
        ExtentTest test = BaseTest.extent.createTest(result.getMethod().getMethodName());
        BaseTest.extentTest.set(test);    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest baseTest = (BaseTest) result.getInstance();
        String screenshotPath = ScreenshotHelper.capture(baseTest.driver,
                result.getMethod().getMethodName());
        try {
            BaseTest.extentTest.get()
                    .fail(result.getThrowable())
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest.extentTest.get().skip("Test Skipped");
    }

//    @Override
//    public void onFinish(ITestContext context) {
//        BaseTest.extent.flush();
//    }


}
