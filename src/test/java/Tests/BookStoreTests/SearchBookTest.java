package Tests.BookStoreTests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class SearchBookTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        sideBarPage = new SideBarPage();
        booksPage = new BooksPage();
        bookPage = new BookPage();
        reader = new ExcelReader("AppData.xlsx");

        String username = reader.getStringData("Login", 2, 0);
        String password = reader.getStringData("Login", 2, 1);
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        if(profilePage.booksInProfile.isEmpty()){
            booksPage.addBooks();
        }
    }

    @Test(priority = 10)
    public void userCanSearchBooks(){
        String searchInput = "Git Pocket Guide";
        profilePage.inputSearch(searchInput);

        for (int i = 0; i < profilePage.booksInProfile.size(); i++) {
            if(profilePage.booksInProfile.get(i).getText().equalsIgnoreCase(searchInput)){
                Assert.assertEquals(profilePage.booksInProfile.get(i).getText(), searchInput);
            }
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }

}
