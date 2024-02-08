package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.Random;

public class BaseTest {
    /*For testing ToolsQA website, IntelliJ IDE and Maven repositories are used
    I used POM design pattern as it facilitates the maintenance of code, as well as enables definition of elements in one place
    I used TestNG framework to facilitate the testing process
    In order to minimize the use of hardcode, Data Driven Testing methodology is applied
    I used Chrome browser for testing purposes. According to statistics, Chrome browser is currently used by approximately 65% Internet users on Desktop
    Source: https://gs.statcounter.com/browser-market-share/desktop/worldwide
    If there's a need to test on other browsers, a driver needs to be changed and added to the directory. */

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

    //Scrolling to an element when the element is outside the viewport
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //Clicking on an element without using click() method
    public void clickOnElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    //Setting a JS timeout in order to test elements that are not yet displayed
    public void setAsyncTimeout(int seconds){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], " + (seconds * 1000)+ ");");
    }

    //Generating a random number to enable clicking on any element in a list
    public int generateRandomNumber(int num){
        Random r = new Random();
        return r.nextInt(num);
    }

    //Closing a pop-up alert/confirm window
    public void acceptAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //Extracting text from an alert/confirm window in order to test its value
    public String alertText(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    //Checking if an elements is still displayed after being removed
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

}
