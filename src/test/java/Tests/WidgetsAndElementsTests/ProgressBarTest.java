package Tests.WidgetsAndElementsTests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProgressBarPage;
import Pages.SideBarPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProgressBarTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        progressBarPage = new ProgressBarPage();

        homePage.clickOnCard("Widgets");
        sideBarPage.clickOnItem("Progress Bar");
        progressBarPage.clickOnStartStopButton();
    }

    @Test(priority = 10)
    public void userCanRunProgressBar() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".progress-bar.bg-success")));
        Assert.assertEquals(progressBarPage.successMessage.getText(), "100%");
        Assert.assertTrue(progressBarPage.progressBar.getAttribute("style").contains(progressBarPage.successMessage.getText()));
    }

    @Test(priority = 20)
    public void userCanResetProgressBar() {
        wait.until(ExpectedConditions.visibilityOf(progressBarPage.resetButton));
        progressBarPage.clickOnResetButton();
        Assert.assertTrue(progressBarPage.progressBar.getAttribute("style").contains(progressBarPage.progressBar.getText()));
    }

    @Test(priority = 30)
    public void userCanStopProgressBar() {
        setAsyncTimeout(3);
        progressBarPage.clickOnStartStopButton();
        Assert.assertEquals(progressBarPage.startStopButton.getText(), "Start");
        Assert.assertTrue(progressBarPage.progressBar.getAttribute("style").contains(progressBarPage.progressBar.getText()));
    }

    @Test(priority = 40)
    public void userCanContinueRunningProgressBar() {
        setAsyncTimeout(3);
        progressBarPage.clickOnStartStopButton();
        String curProgress = progressBarPage.progressBar.getText();
        setAsyncTimeout(2);
        progressBarPage.clickOnStartStopButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".progress-bar.bg-success")));
        String finalProgress = progressBarPage.progressBar.getText();
        Assert.assertFalse(curProgress.equalsIgnoreCase(finalProgress));
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
