package Pages;

import Base.BaseTest;
import Base.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebTablesPage extends BaseTest {

    public WebTablesPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id ="addNewRecordButton")
    public WebElement addRecordButton;
    @FindBy(css ="button[type='submit']")
    public WebElement submitFormButton;
    @FindBy(id = "firstName")
    public WebElement firstnameField;
    @FindBy(id = "lastName")
    public WebElement lastnameField;
    @FindBy(id = "userEmail")
    public WebElement emailField;
    @FindBy(id ="age")
    public WebElement ageField;
    @FindBy(id ="salary")
    public WebElement salaryField;
    @FindBy(id = "department")
    public WebElement departmentField;
    @FindBy(id ="searchBox")
    public WebElement searchBoxField;
    @FindBy(className = "rt-td")
    public List<WebElement> recordCells;
    @FindBy(css = "span[title='Delete']")
    public List<WebElement> deleteButtons;
    @FindBy(className = "mr-2")
    public List<WebElement> editButtons;
    @FindBy(css = "div[role='rowgroup']")
    public List<WebElement> totalRowsInTable;
    public WebElement totalPageNumber(){
        return driver.findElement(By.className("-totalPages"));
    }
    @FindBy(className = "-btn")
    public List<WebElement> controlButtons;
    @FindBy(css = "input[value]")
    public WebElement currentPage;
    @FindBy(css = "select[aria-label='rows per page']")
    public WebElement selectRowsPerPage;
    @FindBy(className = "rt-noData")
    public WebElement noDataCell;

    //--------------------------------


    public void clickOnAddRecordButton(){
        addRecordButton.click();
    }

    public void clickOnSubmitFormButton(){
        submitFormButton.click();
    }

    public void inputFirstName(String firstname){
        firstnameField.clear();
        firstnameField.sendKeys(firstname);
    }

    public void inputLastName(String lastname){
        lastnameField.clear();
        lastnameField.sendKeys(lastname);
    }

    public void inputEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void inputAge(String age){
        ageField.clear();
        ageField.sendKeys(age);
    }

    public void inputSalary(String salary){
        salaryField.clear();
        salaryField.sendKeys(salary);
    }

    public void inputDepartment(String department){
        departmentField.clear();
        departmentField.sendKeys(department);
    }

    public void inputSearch(String str){
        searchBoxField.clear();
        searchBoxField.sendKeys(str);
    }

    public WebElement getTotalPageNumber(){
        return this.totalPageNumber();
    }

    public int totalPagesNum() {
        setAsyncTimeout(1);
        return Integer.parseInt(getTotalPageNumber().getText().trim());
    }

    public int pageInputValue(){
       return Integer.parseInt(currentPage.getAttribute("value"));
    }

    public void clickOnNextButton(){
        clickOnElement(controlButtons.get(1));
    }

    public void clickOnPreviousButton(){
        clickOnElement(controlButtons.getFirst());
    }

    public void deleteAllRecords(){
        if(!deleteButtons.isEmpty()){
            for(int i = deleteButtons.size() - 1; i>=0; i--){
                deleteButtons.get(i).click();
            }
        }
    }

    public int clickOnAnyActionButton(List<WebElement> buttons){
        int randomNum = generateRandomNumber(buttons.size());
        if(!buttons.isEmpty()){
            for(int i = 0; i<buttons.size(); i++){
                if(i == randomNum){
                    clickOnElement(buttons.get(i));
                    break;
                }
            }
        }
        return randomNum;
    }

    //Method counts how many cells in Excel file contain string entered in search box
    public int numOfCellsContainingSearchInput(ExcelReader reader, String str){
        int cellCounter = 0;
        for (int i = 1; i <= reader.getLastRow("AddRecords"); i++) {
            String cellDataString;
            for (int j = 0; j < 6; j++) {
                cellDataString = reader.getStringData("AddRecords", i, j);
                if(cellDataString.toLowerCase().contains(str)){
                    cellCounter++;
                }
            }
        }

        System.out.println(cellCounter);
        return cellCounter;
    }

    //Method counts how many cells in table contain string entered in search box
    public int numOfRecordsContainingSearchInput(String str){
        int recordCounter = 0;
        for (int i = 0; i < recordCells.size(); i++) {
            String cellDataString = recordCells.get(i).getText();
            if(cellDataString.toLowerCase().contains(str))
                recordCounter++;
        }
        System.out.println(recordCounter);

        return recordCounter;
    }

    //New records are added to table until 3 pages of records are displayed
    public void addSomeRecordsToTable(ExcelReader reader){
        for(int i = 1; i<=reader.getLastRow("AddRecords"); i++) {
            clickOnAddRecordButton();
            String firstName = reader.getStringData("AddRecords", i, 0);
            String lastName = reader.getStringData("AddRecords", i, 1);
            String age = reader.getStringData("AddRecords", i, 2);
            String email = reader.getStringData("AddRecords", i, 3);
            String salary = reader.getStringData("AddRecords", i, 4);
            String department = reader.getStringData("AddRecords", i, 5);
            inputFirstName(firstName);
            inputLastName(lastName);
            inputEmail(email);
            inputAge(age);
            inputSalary(salary);
            inputDepartment(department);
            clickOnSubmitFormButton();
        }
    }

    //Records are added to table until 3 pages of records are displayed
    //It is done to enable previous and next buttons
    public void addRecordsToTable(ExcelReader reader) {
        int i = 1;
        while(totalPagesNum() < 3){
            clickOnAddRecordButton();
            String firstName = reader.getStringData("AddMultipleRecords", i, 0);
            String lastName = reader.getStringData("AddMultipleRecords", i, 1);
            String age = reader.getStringData("AddMultipleRecords", i, 2);
            String email = reader.getStringData("AddMultipleRecords", i, 3);
            String salary = reader.getStringData("AddMultipleRecords", i, 4);
            String department = reader.getStringData("AddMultipleRecords", i, 5);
            inputFirstName(firstName);
            inputLastName(lastName);
            inputEmail(email);
            inputAge(age);
            inputSalary(salary);
            inputDepartment(department);
            clickOnSubmitFormButton();
            i++;
        }
    }
}
