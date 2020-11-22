package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement userNameField;

    @FindBy(id="inputPassword")
    private WebElement userPasswordField;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    @FindBy(id="alert")
    private WebElement alertLabel;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public void login(String userName, String userPassword) {
        this.userNameField.sendKeys(userName);
        this.userPasswordField.sendKeys(userPassword);
        this.submitButton.click();
    }

    public String loginResult () {
        return alertLabel.getText();
    }
}
