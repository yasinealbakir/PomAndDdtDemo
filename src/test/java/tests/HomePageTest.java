package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilties.Excel;
import utilties.Log;

import java.io.IOException;
import java.lang.reflect.Method;

import static utilties.ExtentTestManager.startTest;

public class HomePageTest extends BaseTest {
    @BeforeTest
    public void prepareTestData() throws IOException {
        Log.info("Test data is being prepared.");
        Excel.setExcelSheet("UserData");
    }

    @Test
    public void userDefineFeature(Method method) throws IOException {
        Log.info(method.getName() + " do test");
        startTest(method.getName(), "Tüm zorunlu alanların formata uygun ve tam doldurularak kullanıcı tanımlanması");
        loginPage
                .navigateLoginPage()
                .setUsernameAndPassword()
                .clickLoginButton()
                .completeUserDefineForm(Excel.getRowData(1))
                .verifyTestResult(Excel.getCellData(1, 6))
                .saveTestResult(1, 7);
    }
}
