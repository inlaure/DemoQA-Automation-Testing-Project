package Tests.BookStoreTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Pages.SideBarPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
       driver = new ChromeDriver();
       driver.get("https://demoqa.com/");
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       homePage = new HomePage();
       sideBarPage = new SideBarPage();
       loginPage = new LoginPage();
       profilePage = new ProfilePage();
       reader = new ExcelReader("AppData.xlsx");
       homePage.clickOnCard("Book Store Application");
       sideBarPage.clickOnItem("Login");
    }

    @Test(priority = 10)
    public void userCanLoginWithValidCredentials(){
        String username = reader.getStringData("Login", 1, 0);
        String password = reader.getStringData("Login", 1, 1);
        String profileUrl = "https://demoqa.com/profile";
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        wait.until(ExpectedConditions.urlToBe(profileUrl));
        Assert.assertEquals(driver.getCurrentUrl(), profileUrl);
        Assert.assertEquals(username, profilePage.userNameValue());
    }

    @Test(priority = 20)
    public void userCannotLogInWithInvalidUsername() {
        for (int i = 1; i <= reader.getLastRow("Login"); i++) {
            String invalidUsername = reader.getStringData("Login", i, 2);
            String validPassword = reader.getStringData("Login", 1, 1);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!");
        }
    }

    @Test(priority = 30)
    public void userCannotLoginWithInvalidPassword(){
        for (int i = 1; i <= reader.getLastRow("Login"); i++) {
            String validUsername = reader.getStringData("Login", i, 0);
            String invalidPassword = reader.getStringData("Login", i, 3);
            loginPage.inputUsername(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!");
        }
    }

    @Test(priority = 40)
    public void userCannotLogInWithInvalidCredentials() {
        for (int i = 1; i <= reader.getLastRow("Login"); i++) {
            String invalidUsername = reader.getStringData("Login", i, 2);
            String invalidPassword = reader.getStringData("Login", i, 3);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.error.isDisplayed());
            Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!");
        }
    }

    @Test(priority = 50)
    public void userCannotLoginWithEmptyUsernameField(){
        String password = reader.getStringData("Login", 1, 1);
        String loginUrl = "https://demoqa.com/login";
        loginPage.inputUsername("");
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
        Assert.assertTrue(loginPage.usernameField.getText().isEmpty());
    }


    @Test(priority = 60)
    public void userCannotLoginWithEmptyPasswordField(){
        String username = reader.getStringData("Login", 1, 0);
        String loginUrl = "https://demoqa.com/login";
        loginPage.inputUsername(username);
        loginPage.inputPassword("");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
        Assert.assertTrue(loginPage.passwordField.getText().isEmpty());
    }

    @Test(priority = 70)
    public void userCannotLoginWithEmptyFields(){
        String loginUrl = "https://demoqa.com/login";
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.usernameField.getText().isEmpty());
        Assert.assertTrue(loginPage.passwordField.getText().isEmpty());
        Assert.assertEquals(loginPage.invalidFields.size(), 2);
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
