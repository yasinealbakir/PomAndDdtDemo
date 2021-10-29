package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilties.Excel;
import utilties.Log;

import java.io.IOException;
import java.lang.reflect.Method;

import static utilties.ExtentTestManager.startTest;

public class ContactPageTest extends BaseTest {

    @BeforeTest
    public void prepareTestData() throws IOException {
        Log.info("Test data is being prepared.");
        Excel.setExcelSheet("ContactData");
    }

    @Test
    public void sendToCommentFeature(Method method) throws IOException {
        Log.info(method.getName() + " do test");
        startTest(method.getName(), "Tüm zorunlu alanların formata uygun ve tam doldurularak yorum/öneri gönderme");
        loginPage
                .navigateLoginPage()
                .setUsernameAndPassword()
                .clickLoginButton()
                .navigateContactPage()
                .completeContactForm(Excel.getRowData(1))
                .verifyTestResult(Excel.getCellData(1, 5))
                .saveTestResult(1, 6);
    }
}
