package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BaseTest {

    public RegisterPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    public WebElement firstnameField;

    @FindBy(id = "lastname")
    public WebElement lastnameField;

    @FindBy(id = "userName")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "register")
    public WebElement registerButton;

    @FindBy(id = "name")
    public WebElement recaptchaError;

    //----------------------------------

    public void inputFirstName(String firstName){
        firstnameField.clear();
        firstnameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        lastnameField.clear();
        lastnameField.sendKeys(lastName);
    }

    public void inputUserName(String userName){
        usernameField.clear();
        usernameField.sendKeys(userName);
    }

    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnRegisterButton(){
        clickOnElement(registerButton);
    }

    public String recaptchaErrorMessage(){
        clickOnElement(recaptchaError);
        return recaptchaError.getText();
    }
}
