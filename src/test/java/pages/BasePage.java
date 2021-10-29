package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasePage {

    //region Variables
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Select select;
    protected Properties properties;
    protected InputStream inputStream;
    //endregion

    //region Constructor Method
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
    //endregion

    //region Methods
    // Wait
    protected WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Click
    public void click(By by) {
        waitVisibility(by).click();
    }

    // Send Text
    public void sendText(By by, String text) {
        waitVisibility(by).sendKeys(text);
    }

    // Get Text
    public String getText(By by) {
        return waitVisibility(by).getText();
    }

    // Clear Text
    public void clearText(By by) {
        waitVisibility(by).clear();
    }

    // Find Element
    public WebElement find(By by) {
        return waitVisibility(by).findElement(by);
    }

    // Select Element
    public void select(By locator, String value) {
        new Select(find(locator)).selectByVisibleText(value);
    }

    // Get Alertbox Text
    public String getAlertboxText() {
        return this.driver.switchTo().alert().getText();
    }

    //endregion

}
