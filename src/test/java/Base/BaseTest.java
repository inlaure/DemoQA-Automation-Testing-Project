package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.Random;

public class BaseTest {

    public static WebDriver driver;

    public WebDriverWait wait;

    public ExcelReader reader;

    public HomePage homePage;

    public SideBarPage sideBarPage;
    public AlertsPage alertsPage;
    public ModalPage modalPage;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public RegisterPage registerPage;
    public ProgressBarPage progressBarPage;
    public RadioButtonPage radioButtonPage;
    public WebTablesPage webTablesPage;
    public BooksPage booksPage;
    public BookPage bookPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        reader = new ExcelReader("AppData.xlsx");
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickOnElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void setAsyncTimeout(int seconds){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], " + (seconds * 1000)+ ");");
    }

    public int generateRandomNumber(int num){
        Random r = new Random();
        return r.nextInt(num);
    }

    public void acceptAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String alertText(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public boolean elementIsDisplayed(WebElement element){
        boolean elementIsDisplayed = false;
        try{
            wait.until(ExpectedConditions.invisibilityOf(element));
            elementIsDisplayed= element.isDisplayed();
        }catch(NoSuchElementException e) {
            elementIsDisplayed = false;
        }
        return elementIsDisplayed;
    }

    @AfterClass
    public void quitClass(){
//        driver.manage().deleteAllCookies();
//        driver.navigate().refresh();
//        driver.quit();
    }
}
