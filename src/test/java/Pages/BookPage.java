package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookPage extends BaseTest {

    public BookPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "title-wrapper")
    public WebElement title;

    @FindBy(id = "addNewRecordButton")
    public List<WebElement> buttons;

    //---------------------

    public String titleName() {
        return title.findElement(By.id("userName-value")).getText();
    }

    public void clickOnAddToCollection() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().equals("Add To Your Collection")) {
                scrollToElement(buttons.get(i));
                buttons.get(i).click();
                break;
            }
        }
    }

    public void clickOnBackToBookStore() {
        for(int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().equals("Back To Book Store")) {
                scrollToElement(buttons.get(i));
                buttons.get(i).click();
                break;
            }
        }
    }


}