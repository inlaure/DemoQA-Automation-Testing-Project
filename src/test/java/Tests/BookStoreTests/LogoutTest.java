package Tests.BookStoreTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.LoginPage;
import Pages.ProfilePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        reader = new ExcelReader("AppData.xlsx");

        String username = reader.getStringData("Login", 1, 0);
        String password = reader.getStringData("Login", 1, 1);
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void userCanLogOut(){
        WebElement logoutButton= profilePage.findButton("Log out");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        boolean logoutButtonIsDisplayed = logoutButton.isDisplayed();
        profilePage.clickOnButton("Log out");

        try{
            logoutButtonIsDisplayed = profilePage.logoutButton.isDisplayed();
        }catch(NoSuchElementException e){
            logoutButtonIsDisplayed = false;
        }
        Assert.assertFalse(logoutButtonIsDisplayed);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
