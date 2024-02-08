package Tests.WebTablesTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.SideBarPage;
import Pages.WebTablesPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class PageNavigationTest extends BaseTest {
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

    //Asserting that user can go to next page when there are more than 1 pages
    @Test(priority = 10)
    public void userCanGoToNextPage() {
        webTablesPage.addRecordsToTable(reader);
        for(int i = 1; i<webTablesPage.totalPagesNum(); i++) {
            Assert.assertTrue(webTablesPage.controlButtons.get(1).isEnabled());
            webTablesPage.clickOnNextButton();
            Assert.assertEquals(webTablesPage.pageInputValue(), i+1);
        }
    }

    //Asserting that user can go to previous page when user is not on page 1.
    @Test(priority = 20)
    public void userCanGoToPreviousPage() {
        webTablesPage.addRecordsToTable(reader);
        for(int i = 1; i<webTablesPage.totalPagesNum(); i++) {
            webTablesPage.clickOnNextButton();
        }
        for(int i = webTablesPage.totalPagesNum(); i>1;i--){
            Assert.assertEquals(webTablesPage.pageInputValue(), i);
            Assert.assertTrue(webTablesPage.controlButtons.getFirst().isEnabled());
            webTablesPage.clickOnPreviousButton();
        }
    }

    @Test(priority = 30)
    public void userCanSelectNumberOfRowsDisplayed(){
        Select select = new Select(webTablesPage.selectRowsPerPage);
        List<WebElement> optionList = select.getOptions();

        for(int i = 0; i<optionList.size(); i++){
            select.selectByIndex(i);
            int optionValue = Integer.parseInt(optionList.get(i).getAttribute("value"));
            int displayedRows = webTablesPage.recordCells.size()/7;
            Assert.assertEquals(optionValue, displayedRows);
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
