package Tests.BookStoreTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.LoginPage;
import Pages.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class RegisterAccountTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        reader = new ExcelReader("AppData.xlsx");
    }

    @Test(priority = 10)
    public void userCannotRegisterWhenRecaptchaIsNotVerified() {
        String firstName = reader.getStringData("NewUserRegistration", 1, 0);
        String lastName = reader.getStringData("NewUserRegistration", 1, 1);
        String userName = reader.getStringData("NewUserRegistration", 1, 2);
        String password = reader.getStringData("NewUserRegistration", 1, 3);

        loginPage.clickOnAddNewUserButton();
        registerPage.inputFirstName(firstName);
        registerPage.inputLastName(lastName);
        registerPage.inputUserName(userName);
        registerPage.inputPassword(password);
        registerPage.clickOnRegisterButton();
        wait.until(ExpectedConditions.visibilityOf(registerPage.recaptchaError));
        Assert.assertEquals(registerPage.recaptchaErrorMessage(), "Please verify reCaptcha to register!");
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
