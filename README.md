# ToolsQA
ToolsQA is a website for testing purposes where the main goal is to test as many various functionalities as possible.
## Dependencies
* Run on Windows 10 OS
* IDE for this project is IntelliJ IDEA 2023.3.2 (Community Edition)
* Browsers needed is Chrome as mandatory but also preferred are Firefox, Edge and Safari

## Installation
* Open terminal in IDE and git clone the repository

```git clone https://github.com/inlaure/DemoQA-Automation-Testing-Project.git```
* java version "21" 2023-09-19 LTS
* Apache Maven 3.9.5

## Executing program
Run all tests from terminal with:

```mvn test```

Run specific class from terminal with:
  
```mvn test -Dtest="LoginTest"```

Run specific xml file from terminal with:
  
```mvn clean test -DsuiteXmlFile="testng.xml"```

## Framework Walkthorugh
Packages:

* Base - Contains classes used throughout the app
* Pages - Contains classes for each page
* Tests - Contains test classes

Files:

* end-processes - Used in AfterMethod to end all processes in the background
* pom.xml - Contains all dependencies used in the project (last updated: 05/02/2024)
* AppData.xlsx - Excel file used to read some data for DDT as an alternative
* .gitignore - File used to store all items that are not pushed to github

## Naming Conventions
* Classes are written in camel case with first character in upper case
* Methods are written in camel case with first character in lower case
* Class objects are named the same as a class name with lower case for first character
* Test methods are named as test case names

## Other
* Test methods are kept clean
* Each test should have at least 2 assertions with few test exceptions
* Priorities are set with an increment of 10. If higher priority occurs later in testing, the priority of such a test is placed between two priorities
