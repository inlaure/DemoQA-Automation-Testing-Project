package Tests.WidgetsAndElementsTests;

import Base.BaseTest;
import Pages.AlertsPage;
import Pages.HomePage;
import Pages.SideBarPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        alertsPage = new AlertsPage();
        homePage.clickOnCard("Alerts, Frame & Windows");
        sideBarPage.clickOnItem("Alerts");
    }

    @Test(priority = 10)
    public void userCanOpenAlertWindow() {
        alertsPage.clickOnAlertButton();
        String alertText = alertText();
        Assert.assertEquals(alertText, "You clicked a button");
        driver.switchTo().alert().accept();
    }

    @Test(priority = 20)
    public void userCanOpenTimerAlertWindow() {
        alertsPage.clickOnTimerAlertButton();
        String alertText = alertText();
        Assert.assertEquals(alertText, "This alert appeared after 5 seconds");
        driver.switchTo().alert().accept();
    }

    @Test(priority = 30)
    public void userCanOpenConfirmWindow() {
        alertsPage.clickOnConfirmButton();
        String alertText = alertText();
        Assert.assertEquals(alertText, "Do you confirm action?");
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOf(alertsPage.successMessage));
        Assert.assertEquals(alertsPage.successMessage.getText(), "You selected Ok");
    }

    @Test(priority = 40)
    public void userCanOpenPromptWindow(){
        alertsPage.clickOnPromptButton();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String inputText = "itbootcamp";
        alert.sendKeys(inputText);
        alert.accept();
        wait.until(ExpectedConditions.visibilityOf(alertsPage.successMessage));
        Assert.assertEquals(alertsPage.successMessage.getText(), "You entered " + inputText);
        }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
    }

