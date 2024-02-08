package Tests.BookStoreTests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteBookTest extends BaseTest {
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

        String validUsername = reader.getStringData("Login", 2, 0);
        String validPassword = reader.getStringData("Login", 2, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void userCanDeleteAnyBook() {
        if(profilePage.booksInProfile.isEmpty()){
            booksPage.addBooks();
        }
        Assert.assertFalse(profilePage.booksInProfile.isEmpty());
        int numOfBooksBeforeDelete = profilePage.booksInProfile.size();
        int deletedBookIndex = profilePage.deleteAnyBook();
        profilePage.clickOnOk();
        String deletedBookTitle = profilePage.booksInProfile.get(deletedBookIndex).getText();
        acceptAlert();
        int numOfBooksAfterDelete = profilePage.booksInProfile.size();
        for(int i = 0; i<profilePage.booksInProfile.size(); i++){
            Assert.assertNotEquals(profilePage.booksInProfile.get(i).getText(), deletedBookTitle);
        }
        Assert.assertEquals(numOfBooksBeforeDelete-1, numOfBooksAfterDelete);
    }

    @Test(priority = 20)
    public void userCanDeleteAllBooks() {
        if(profilePage.booksInProfile.isEmpty()){
            booksPage.addBooks();
        }
        Assert.assertFalse(profilePage.booksInProfile.isEmpty());
        profilePage.clickOnDeleteAllBooks();
        profilePage.clickOnOk();
        acceptAlert();
        int numOfBooksAfterDelete = profilePage.booksInProfile.size();
        Assert.assertEquals(numOfBooksAfterDelete, 0);
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}


