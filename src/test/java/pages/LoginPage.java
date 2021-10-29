package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilties.Config;
import utilties.Log;

import java.io.IOException;

public class LoginPage extends BasePage {

    //region Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //endregion

    //region Web Elements
    By txtUserName = By.xpath("//input[@id='kullaniciAdi']");
    By txtPassword = By.xpath("//input[@id='parola']");
    By btnSubmit = By.xpath("//button[@id='btnGirisYap']");
    //endregion

    //region Methods
    public LoginPage navigateLoginPage() throws IOException {
        Log.info("Login Page is opening");
        driver.navigate().to(Config.BASE_URL);
        return this;
    }

    public LoginPage setUsernameAndPassword() {
        Log.info("Filling out the username and password fields");
        clearText(txtUserName);
        clearText(txtPassword);
        sendText(txtUserName, Config.USERNAME);
        sendText(txtPassword, Config.PASSWORD);
        return this;
    }

    public HomePage clickLoginButton() {
        Log.info("Going to Home Page");
        click(btnSubmit);
        return new HomePage(driver);
    }
    //endregion

}
