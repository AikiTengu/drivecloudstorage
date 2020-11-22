package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

//Notes elements
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotes;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement titleText;

    @FindBy(id = "note-description")
    private WebElement descriptionText;

    @FindBy(id = "save-note")
    private WebElement saveNoteButton;

    @FindBy(id="delete_note")
    private WebElement deleteNoteButton;

    @FindBy(id="edit_note")
    private WebElement editNoteButton;

    @FindBy(name="ntlist")
    private WebElement noteTitle;

    @FindBy(name="ndlist")
    private WebElement noteDescription;

//Credentials elements
    @FindBy(id="credential-url")
    private WebElement credUrl;

    @FindBy(id="credential-username")
    private WebElement credUserName;

    @FindBy(id="credential-password")
    private WebElement credPassword;

    @FindBy(id="nav-credentials-tab")
    private WebElement navCreds;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredButton;

    @FindBy(id="save-cred")
    private WebElement saveCredButton;

    @FindBy(id="delete-cred")
    private WebElement deleteCredButton;

    @FindBy(id="edit-cred")
    private WebElement editCredButton;

    @FindBy(name="credurllist")
    private WebElement CredUrlList;

    @FindBy(name="creduserlist")
    private WebElement credUserNameList;

    @FindBy(name="credpwlist")
    private WebElement credPasswordList;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void addNewNote(String title, String description) {
        try {
            this.navNotes.click();
            Thread.sleep(1000);
            this.addNoteButton.click();
            Thread.sleep(1000);
            this.titleText.sendKeys(title);
            this.descriptionText.sendKeys(description);
            Thread.sleep(1000);
            this.saveNoteButton.click();
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        };
    }

    public void nav2Notes() {
        this.navNotes.click();
    }

    public String getNoteTitle() throws NoSuchElementException {
        return this.noteTitle.getText();
    }

    public String getNoteDescription() throws NoSuchElementException{
        return this.noteDescription.getText();
    }

    public void deleteNote() {
        try {
            this.navNotes.click();
            Thread.sleep(1000);
            this.deleteNoteButton.click();
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        };
    }
    public void editNote(String title, String description) {
        try {
            this.navNotes.click();
            Thread.sleep(1000);
            this.editNoteButton.click();
            Thread.sleep(1000);
            this.titleText.clear();
            this.titleText.sendKeys(title);
            Thread.sleep(1000);
            this.descriptionText.clear();
            this.descriptionText.sendKeys(description);
            Thread.sleep(1000);
            this.saveNoteButton.click();
        }
        catch (InterruptedException e) {
        };
    }

    public void addNewCred(String url, String userName, String password) {
        try {
            this.navCreds.click();
            Thread.sleep(1000);
            this.addCredButton.click();
            Thread.sleep(1000);
            this.credUrl.sendKeys(url);
            this.credUserName.sendKeys(userName);
            this.credPassword.sendKeys(password);
            Thread.sleep(1000);
            this.saveCredButton.click();
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        };
    }

    public void nav2Creds() {
        this.navCreds.click();
    }

    public String getCredUrl() throws NoSuchElementException {
        return this.CredUrlList.getText();
    }

    public String getCredUser() throws NoSuchElementException{
        return this.credUserNameList.getText();
    }

    public String getCredPassword() throws NoSuchElementException{
        return this.credPasswordList.getText();
    }

    public void deleteCred() {
        try {
            this.navCreds.click();
            Thread.sleep(1000);
            this.deleteCredButton.click();
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        };
    }
    public void editCred(String url, String userName, String password) {
        try {
            this.navCreds.click();
            Thread.sleep(1000);
            this.editCredButton.click();
            Thread.sleep(1000);
            this.credUrl.clear();
            this.credUrl.sendKeys(url);
            Thread.sleep(1000);
            this.credUserName.clear();
            this.credUserName.sendKeys(userName);
            Thread.sleep(1000);
            this.credPassword.clear();
            this.credPassword.sendKeys(password);
            Thread.sleep(1000);
            this.saveCredButton.click();
        }
        catch (InterruptedException e) {
        };
    }
}
