package Tests.WebTablesTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.SideBarPage;
import Pages.WebTablesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EditRecordsTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        webTablesPage = new WebTablesPage();
        reader = new ExcelReader("AppData.xlsx");
    }

    @Test(priority = 10)
    public void userCanEditAnyRecord() {
        if (webTablesPage.deleteButtons.isEmpty()) {
            webTablesPage.addSomeRecordsToTable(reader);
        }
        int editedRow = webTablesPage.clickOnAnyActionButton(webTablesPage.editButtons);
        //Saving values of the edited record
        List<WebElement> dataBeforeEdit = webTablesPage.totalRowsInTable.get(editedRow).findElements(By.className("rt-td"));
        String prevFirstName = dataBeforeEdit.get(0).getText();
        String prevLastName = dataBeforeEdit.get(1).getText();
        String prevAge = dataBeforeEdit.get(2).getText();
        String prevEmail = dataBeforeEdit.get(3).getText();
        String prevSalary = dataBeforeEdit.get(4).getText();
        String prevDepartment = dataBeforeEdit.get(5).getText();
        //Editing the record
        String firstName = reader.getStringData("EditUser", 1, 0);
        String lastName = reader.getStringData("EditUser", 1, 1);
        String age = String.valueOf(reader.getIntegerData("EditUser", 1, 2));
        String email = reader.getStringData("EditUser", 1, 3);
        String salary = String.valueOf(reader.getStringData("EditUser", 1, 4));
        String department = reader.getStringData("EditUser", 1, 5);
        //Replacing previous data
        webTablesPage.inputFirstName(firstName);
        webTablesPage.inputLastName(lastName);
        webTablesPage.inputEmail(email);
        webTablesPage.inputAge(age);
        webTablesPage.inputSalary(salary);
        webTablesPage.inputDepartment(department);
        webTablesPage.clickOnSubmitFormButton();
        //Saving new values of the edited record
        List<WebElement> dataAfterEdit = webTablesPage.totalRowsInTable.get(editedRow).findElements(By.className("rt-td"));
        //Asserting that previous and current record values differ
        Assert.assertNotEquals(prevFirstName, dataAfterEdit.get(0).getText());
        Assert.assertNotEquals(prevLastName, dataAfterEdit.get(1).getText());
        Assert.assertNotEquals(prevAge, dataAfterEdit.get(2).getText());
        Assert.assertNotEquals(prevEmail, dataAfterEdit.get(3).getText());
        Assert.assertNotEquals(prevSalary, dataAfterEdit.get(4).getText());
        Assert.assertNotEquals(prevDepartment, dataAfterEdit.get(5).getText());
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
