package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BooksPage extends BaseTest {

    public BooksPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "mr-2")
    public List<WebElement> books;

    //-------------------------

    //Adding a random book to profile
    public void clickOnAnyBook(){
        int randomNumber = generateRandomNumber(books.size());
        scrollToElement(books.get(randomNumber));
        books.get(randomNumber).click();
    }

    //Adding multiple books to profile
    public void addBooks(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        sideBarPage = new SideBarPage();
        bookPage = new BookPage();
        sideBarPage.clickOnItem("Book Store");
        for(int i = 0; i<3; i++) {
            scrollToElement(books.get(i));
            books.get(i).click();
            wait.until(ExpectedConditions.visibilityOf(bookPage.title.findElement(By.id("userName-value"))));
            bookPage.clickOnAddToCollection();
            acceptAlert();
            driver.navigate().refresh();
            bookPage.clickOnBackToBookStore();
        }
        sideBarPage.clickOnItem("Profile");
    }
}