package Tests.WidgetsAndElementsTests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ModalPage;
import Pages.SideBarPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class ModalTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        modalPage = new ModalPage();
        homePage.clickOnCard("Alerts, Frame & Windows");
        sideBarPage.clickOnItem("Modal Dialogs");
    }

    @Test(priority = 10)
    public void userCanOpenSmallModal(){
        modalPage.clickOnSmallModal();
        Assert.assertTrue(modalPage.modal.isDisplayed());
        Assert.assertEquals(modalPage.modalTitle.getText(), "Small Modal");
    }

    @Test(priority = 20)
    public void userCanOpenLargeModal(){
        modalPage.clickOnLargeModal();
        Assert.assertTrue(modalPage.modal.isDisplayed());
        Assert.assertEquals(modalPage.modalTitle.getText(), "Large Modal");
    }

    @Test(priority = 30)
    public void userCanCloseSmallModal() {
        modalPage.clickOnSmallModal();
        wait.until(ExpectedConditions.visibilityOf(modalPage.modal));
        modalPage.closeSmallModal();
        boolean modalIsDisplayed = elementIsDisplayed(modalPage.modal);
        Assert.assertFalse(modalIsDisplayed);
    }

    @Test(priority = 40)
    public void userCanCloseLargeModal(){
        modalPage.clickOnLargeModal();
        wait.until(ExpectedConditions.visibilityOf(modalPage.modal));
        modalPage.closeLargeModal();
        boolean modalIsDisplayed = elementIsDisplayed(modalPage.modal);
        Assert.assertFalse(modalIsDisplayed);
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
