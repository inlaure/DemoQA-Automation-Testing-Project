package Tests.BookStoreTests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class AddBookTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        sideBarPage = new SideBarPage();
        booksPage = new BooksPage();
        bookPage = new BookPage();

        String validUsername = reader.getStringData("Login", 1, 0);
        String validPassword = reader.getStringData("Login", 1, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void userCanAddAnyBook() {
        Assert.assertTrue(profilePage.booksInProfile.isEmpty());
        sideBarPage.clickOnItem("Book Store");
        wait.until(ExpectedConditions.visibilityOf(profilePage.loggedInUserValue));
        booksPage.clickOnAnyBook();
        String bookToBeAdded = bookPage.titleName();
        bookPage.clickOnAddToCollection();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        alert.accept();
        driver.navigate().refresh();
        sideBarPage.clickOnItem("Profile");
        Assert.assertEquals(profilePage.booksInProfile.getFirst().getText(), bookToBeAdded);
    }

    @Test(priority = 20)
    public void userCanAddMultipleBooks() {
        Assert.assertTrue(profilePage.booksInProfile.isEmpty());
        sideBarPage.clickOnItem("Book Store");
        wait.until(ExpectedConditions.visibilityOf(profilePage.loggedInUserValue));

        for(int i = 0; i<3; i++) {
            scrollToElement(booksPage.books.get(i));
            booksPage.books.get(i).click();
            wait.until(ExpectedConditions.visibilityOf(bookPage.title.findElement(By.id("userName-value"))));
            String bookToBeAdded = bookPage.titleName();
            bookPage.clickOnAddToCollection();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            alert.accept();
            driver.navigate().refresh();
            sideBarPage.clickOnItem("Profile");
            Assert.assertEquals(profilePage.booksInProfile.get(i).getText(), bookToBeAdded);
            sideBarPage.clickOnItem("Book Store");
        }
    }

    @AfterMethod
    public void bookCleanUp() {
        sideBarPage.clickOnItem("Profile");
        profilePage.clickOnDeleteAllBooks();
        profilePage.clickOnOk();
        driver.navigate().refresh();
    }
}