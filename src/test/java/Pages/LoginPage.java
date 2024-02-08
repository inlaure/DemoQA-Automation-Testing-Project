package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BaseTest {

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "userName")
    public WebElement usernameField;
    @FindBy(id = "password")
    public WebElement passwordField;
    @FindBy(id = "login")
    public WebElement loginButton;
    @FindBy(id = "newUser")
    public WebElement addNewUserButton;

    //Error message container
    @FindBy(id = "name")
    public WebElement error;

    //When fields are empty, their borders turn red
    @FindBy(css = ".mr-sm-2.is-invalid.form-control")
    public List<WebElement> invalidFields;


    //-------------------------

    public void inputUsername(String username){//This method accepts username which is inserted through Excel file
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password){//This method accepts password which is inserted through Excel file
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

    public void clickOnAddNewUserButton(){
        addNewUserButton.click();
    }
    public String errorMessage() {
        return error.getText();
    }
}
