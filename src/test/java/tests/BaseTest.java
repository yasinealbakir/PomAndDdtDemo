package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import utilties.Log;


public class BaseTest {

    //region Variables and Enum
    protected WebDriver driver;
    protected ChromeOptions chromeOptions;
    protected FirefoxOptions firefoxOptions;
    protected EdgeOptions edgeOptions;
    protected LoginPage loginPage;

    protected enum browsers {
        chrome,
        firefox,
        edge
    }
    //endregion

    //region Methods
    public WebDriver getDriver() {
        return driver;
    }

    public void selectBrowser(browsers browser) {
        if (browser.equals(browsers.chrome)) {
            WebDriverManager.chromedriver().setup();
            chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(false);
            chromeOptions.addArguments("start-maximized");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equals(browsers.firefox)) {
            WebDriverManager.firefoxdriver().setup();
            firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(false);
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browser.equals(browsers.edge)) {
            WebDriverManager.edgedriver().setup();
            edgeOptions = new EdgeOptions();
            driver = new EdgeDriver(edgeOptions);
        }
    }
    //endregion

    //region Before Test Method
    @BeforeMethod
    @Parameters("browser")
    public void setUp(browsers browser) {
        Log.info("Tests is starting with " + browser + " browser");
        selectBrowser(browser);
        loginPage = new LoginPage(driver);
    }
    //endregion

    //region After Test Method
    @AfterMethod
    public void tearDown() {
        Log.info("Tests has been finished");
        driver.quit();
    }
    //endregion

}
