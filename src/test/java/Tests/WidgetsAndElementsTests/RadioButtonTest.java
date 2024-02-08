package Tests.WidgetsAndElementsTests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.RadioButtonPage;
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

public class RadioButtonTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        radioButtonPage = new RadioButtonPage();

        homePage.clickOnCard("Elements");
        sideBarPage.clickOnItem("Radio Button");
    }

    @Test(priority = 10)
    public void userCanSelectAnyRadioButton() {
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/radio-button"));
        String buttonMessage = radioButtonPage.clickOnAnyRadioButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("text-success")));
        Assert.assertEquals(radioButtonPage.successMessageText(), buttonMessage);
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
