package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgressBarPage extends BaseTest {
    public ProgressBarPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "startStopButton")
    public WebElement startStopButton;
    @FindBy(id = "resetButton")
    public WebElement resetButton;
    @FindBy(css = ".progress-bar.bg-success")
    public WebElement successMessage;
    @FindBy(className= "progress-bar")
    public WebElement progressBar;

    //----------------------------

    public void clickOnResetButton(){
        resetButton.click();
    }
    public void clickOnStartStopButton(){
        startStopButton.click();
    }
}
