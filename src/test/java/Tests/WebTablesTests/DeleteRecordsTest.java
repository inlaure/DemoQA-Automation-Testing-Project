package Tests.WebTablesTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.SideBarPage;
import Pages.WebTablesPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class DeleteRecordsTest extends BaseTest {
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

        if(elementIsDisplayed(webTablesPage.noDataCell)){
            webTablesPage.addSomeRecordsToTable(reader);
        }
    }

    @Test(priority = 10)
    public void userCanDeleteAnyRecord() {
        int initialNumberOfRecords = webTablesPage.deleteButtons.size();
        webTablesPage.clickOnAnyActionButton(webTablesPage.deleteButtons);
        int currentNumberOfRecords = webTablesPage.deleteButtons.size();
        Assert.assertEquals(initialNumberOfRecords - 1, currentNumberOfRecords);
    }


    @Test(priority = 20)
    public void userCanDeleteAllRecords() {
        webTablesPage.deleteAllRecords();
        Assert.assertEquals(webTablesPage.deleteButtons.size(), 0);
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
