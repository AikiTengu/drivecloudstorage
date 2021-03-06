package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id="inputFirstName")
    private WebElement firstNameField;

    @FindBy(id="inputLastName")
    private WebElement lastNameField;

    @FindBy(id="inputUsername")
    private WebElement userNameField;

    @FindBy(id="inputPassword")
    private WebElement passwordField;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String userName, String userPassword) {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.userNameField.sendKeys(userName);
        this.passwordField.sendKeys(userPassword);
        this.submitButton.click();
    }
}
