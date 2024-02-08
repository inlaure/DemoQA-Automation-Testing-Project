package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProfilePage extends BaseTest {

    public ProfilePage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id ="userName-value")
    public WebElement loggedInUserValue;
    @FindBy(className = "mr-2")
    public List<WebElement> booksInProfile;
    @FindBy(id = "submit")
    public List<WebElement> profileButtons;
    @FindBy(linkText = "Log out")
    public WebElement logoutButton;
    @FindBy(id = "closeSmallModal-ok")
    public WebElement okButton;
    @FindBy(id = "delete-record-undefined")
    public List<WebElement> deleteButtons;
    @FindBy(id ="searchBox")
    public WebElement searchBoxField;

    //--------------------------------------------

    public String userNameValue(){
        return loggedInUserValue.getText();
    }

    public void inputSearch(String str){
        searchBoxField.clear();
        searchBoxField.sendKeys(str);
    }

    //All four buttons on profile page have the same id
    public void clickOnButton(String str) {
        for (int i = 0; i < profileButtons.size(); i++) {
            if (profileButtons.get(i).getText().equals(str)) {
                profileButtons.get(i).click();
                break;
            }
        }
    }

    //Method return selected button whose title is set as an argument
    public WebElement findButton(String str){
        WebElement selectedElement = profileButtons.getFirst();
        for(int i = 0;i<profileButtons.size(); i++){
            if(profileButtons.get(i).getText().equals(str)){
                selectedElement = profileButtons.get(i);
                break;
            }
        }
        return selectedElement;
    }

    public void clickOnAnySubmitButton(String str){
        clickOnElement(findButton(str));
    }

    public void clickOnDeleteAllBooks() {
        for (int i = 0; i < profileButtons.size(); i++) {
            if (profileButtons.get(i).getText().equals("Delete All Books")) {
                clickOnElement(profileButtons.get(i));
                break;
            }
        }
    }

    public void clickOnOk() {
        okButton.click();
    }

    public int deleteAnyBook(){
        int randomNum = generateRandomNumber(deleteButtons.size());
            for(int i = 0; i<deleteButtons.size(); i++){
                if(i == randomNum){
                    clickOnElement(deleteButtons.get(i));
                }
            }
            return randomNum;
    }
}
