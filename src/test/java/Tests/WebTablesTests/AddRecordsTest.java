package Tests.WebTablesTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.SideBarPage;
import Pages.WebTablesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;

public class AddRecordsTest extends BaseTest {

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
    public void userCanAddNewRecords() {
        int j = 0;
        webTablesPage.deleteAllRecords();
        for (int i = 1; i <= reader.getLastRow("AddRecords"); i++) {
            webTablesPage.clickOnAddRecordButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userForm")));
            String firstName = reader.getStringData("AddRecords", i, 0);
            String lastName = reader.getStringData("AddRecords", i, 1);
            String age = reader.getStringData("AddRecords", i, 2);
            String email = reader.getStringData("AddRecords", i, 3);
            String salary = reader.getStringData("AddRecords", i, 4);
            String department = reader.getStringData("AddRecords", i, 5);
            webTablesPage.inputFirstName(firstName);
            webTablesPage.inputLastName(lastName);
            webTablesPage.inputEmail(email);
            webTablesPage.inputAge(age);
            webTablesPage.inputSalary(salary);
            webTablesPage.inputDepartment(department);
            webTablesPage.clickOnSubmitFormButton();
            Assert.assertEquals(firstName, webTablesPage.recordCells.get(j).getText());
            Assert.assertEquals(lastName, webTablesPage.recordCells.get(j + 1).getText());
            Assert.assertEquals(age, webTablesPage.recordCells.get(j + 2).getText());
            Assert.assertEquals(email, webTablesPage.recordCells.get(j + 3).getText());
            Assert.assertEquals(salary, webTablesPage.recordCells.get(j + 4).getText());
            Assert.assertEquals(department, webTablesPage.recordCells.get(j + 5).getText());
            j += 7;
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}




