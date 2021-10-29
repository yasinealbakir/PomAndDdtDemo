package pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilties.Excel;
import utilties.Log;

public class HomePage extends BasePage {

    //region Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //endregion

    //region Web Elements
    By txtFirstName = By.xpath("//input[@id='txtAd']");
    By txtLastName = By.xpath("//input[@id='txtSoyad']");
    By txtEmail = By.xpath("//input[@id='txtEposta']");
    By rdMale = By.xpath("//input[@id='radioErkek']");
    By rdFemale = By.xpath("//input[@id='radioKadin']");
    By cbxTitle = By.xpath("//select[@id='drpUnvan']");
    By chckbxAccept = By.xpath("//input[@id='chckbxKabul']");
    By btnSave = By.xpath("//button[@id='btnKaydet']");
    By btnNavigateContact = By.xpath("//a[contains(text(),'İletişim')]");
    //endregion

    //region Methods
    public HomePage completeUserDefineForm(XSSFRow row) {
        Log.info("Filling out the user define form");
        sendText(txtFirstName, row.getCell(1).toString());
        sendText(txtLastName, row.getCell(2).toString());
        sendText(txtEmail, row.getCell(3).toString());
        if (row.getCell(4).toString().equals("Erkek")) {
            click(rdMale);
        } else {
            click(rdFemale);
        }
        select(cbxTitle, row.getCell(5).toString());
        click(chckbxAccept);
        click(btnSave);
        return this;
    }

    public HomePage verifyTestResult(String expected) {
        Log.info("Verifying test result");
        Assert.assertEquals(getAlertboxText(), expected);
        return this;
    }

    public HomePage saveTestResult(int row, int col) {
        Log.info("Writing to excel test result");
        Excel.rowNumber = row;
        Excel.columnNumber = col;
        return this;
    }

    public ContactPage navigateContactPage() {
        Log.info("The contact page is opening");
        click(btnNavigateContact);
        return new ContactPage(driver);
    }

    //endregion

}
