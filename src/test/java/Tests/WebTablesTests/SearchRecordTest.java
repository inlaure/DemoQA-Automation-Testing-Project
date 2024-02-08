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

public class SearchRecordTest extends BaseTest {

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
    public void userCanSearchRecords(){
        webTablesPage.deleteAllRecords();
        webTablesPage.addSomeRecordsToTable(reader);
        String searchInput = "Smith".toLowerCase();
        webTablesPage.inputSearch(searchInput);

        int cellCounter = webTablesPage.numOfCellsContainingSearchInput(reader, searchInput);
        int recordCounter = webTablesPage.numOfRecordsContainingSearchInput(searchInput);

        Assert.assertEquals(cellCounter, recordCounter);
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
