package utilties;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.BaseTest;

import static utilties.ExtentTestManager.getTest;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < maxTry) {
                count++;
                result.setStatus(ITestResult.FAILURE);
                extendReportsFailOperations(result);
                return true;
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    private void extendReportsFailOperations(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "TEST FAILED",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
}
