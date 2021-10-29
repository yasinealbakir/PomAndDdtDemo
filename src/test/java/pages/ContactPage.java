package pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilties.Excel;
import utilties.Log;

public class ContactPage extends BasePage {

    //region Constructor
    public ContactPage(WebDriver driver) {
        super(driver);
    }
    //endregion

    //region Web Elements
    By txtFirstName = By.xpath("//input[@id='txtAd']");
    By txtLastName = By.xpath("//input[@id='txtSoyad']");
    By txtEmail = By.xpath("//input[@id='txtEposta']");
    By txtComment = By.xpath("//textarea[@id='txtYorum']");
    By btnSave = By.xpath("//button[@id='btnKaydet']");
    //endregion

    //region Methods
    public ContactPage completeContactForm(XSSFRow row) {
        Log.info("Filling out the contact form");
        sendText(txtFirstName, row.getCell(1).toString());
        sendText(txtLastName, row.getCell(2).toString());
        sendText(txtEmail, row.getCell(3).toString());
        sendText(txtComment, row.getCell(4).toString());
        click(btnSave);
        return this;
    }

    public ContactPage verifyTestResult(String result) {
        Log.info("Verifying test result");
        Assert.assertEquals(getAlertboxText(), result);
        return this;
    }

    public ContactPage saveTestResult(int row, int col) {
        Log.info("Writing to excel test result");
        Excel.rowNumber = row;
        Excel.columnNumber = col;
        return this;
    }
    //endregion

}
