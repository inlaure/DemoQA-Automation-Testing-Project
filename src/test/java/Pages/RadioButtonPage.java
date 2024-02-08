package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class RadioButtonPage extends BaseTest {

    public RadioButtonPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "text-success")
    public WebElement successMessage;

    @FindBy(className = "custom-control-label")
    public List<WebElement> radioButtons;

    //----------------------------------------

    public String successMessageText(){
        return successMessage.getText();
    }

    //Random radio button is clicked each time and its text is returned
    public String clickOnAnyRadioButton(){
        String text = "";
        int randomNum = generateRandomNumber(radioButtons.size()-1);
        for(int i = 0; i<radioButtons.size()-1; i++){
            if(i == randomNum){
                radioButtons.get(i).click();
                text = radioButtons.get(i).getText();
                break;
            }
        }
        return text;
    }
}
