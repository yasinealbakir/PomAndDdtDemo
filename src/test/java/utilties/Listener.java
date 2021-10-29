package utilties;

import com.aventstack.extentreports.Status;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.util.Objects;

import static utilties.ExtentTestManager.getTest;
import static utilties.ExtentTestManager.startTest;

public class Listener extends BaseTest implements ITestListener {

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info(getTestMethodName(result) + " Test is starting");
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(getTestMethodName(result) + " Test is succeed");
        getTest().log(Status.PASS, "Test passed");
        Excel.setCellData("PASSED", Excel.rowNumber, Excel.columnNumber);
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        Log.error(getTestMethodName(result) + " Test failed");
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "TEST FAILED",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        Excel.setCellData("FAILED", Excel.rowNumber, Excel.columnNumber);
    }

    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn(getTestMethodName(result) + " Test is skipped");
        getTest().log(Status.SKIP, "Test is skipped");
        Excel.setCellData("SKIPPED", Excel.rowNumber, Excel.columnNumber);
    }

    @Override
    public void onStart(ITestContext context) {
        context.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.extentReports.flush();
    }

}
