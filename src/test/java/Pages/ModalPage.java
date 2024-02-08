package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModalPage extends BaseTest {

    public ModalPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "showSmallModal")
    public WebElement smallModalButton;
    @FindBy(id = "showLargeModal")
    public WebElement largeModalButton;

    //Observe that both small and large modal containers have the same css selector
    @FindBy(css = "div[role='dialog']")
    public WebElement modal;

    //Observe that both small and large modal titles have the same css selector
    @FindBy(css = ".modal-title.h4")
    public WebElement modalTitle;
    @FindBy(id = "closeSmallModal")
    public WebElement closeSmallModalButton;
    @FindBy(id = "closeLargeModal")
    public WebElement closeLargeModalButton;

    //-----------------------------------

    public void clickOnSmallModal(){
        smallModalButton.click();
    }
    public void clickOnLargeModal(){
        largeModalButton.click();
    }
    public void closeSmallModal(){
        closeSmallModalButton.click();
    }
    public void closeLargeModal(){
        closeLargeModalButton.click();
    }

}