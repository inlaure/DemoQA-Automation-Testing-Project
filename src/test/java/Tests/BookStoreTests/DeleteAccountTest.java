package Tests.BookStoreTests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.ProfilePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class DeleteAccountTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String username = reader.getStringData("Login", 3, 0);
        String password = reader.getStringData("Login", 3, 1);

        loginPage = new LoginPage();
        profilePage = new ProfilePage();

        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void userCanDeleteAccount(){
        String username = reader.getStringData("Login", 3, 0);
        String password = reader.getStringData("Login", 3, 1);
        Assert.assertEquals(profilePage.userNameValue(), username);
        profilePage.clickOnAnySubmitButton("Delete Account");
        profilePage.clickOnOk();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/login"));
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
