package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {
    @FindBy(id = "clickbait-savenotesuccess")
    private WebElement successSaveNoteReturn;

    @FindBy(id = "clickbait-delnotesuccess")
    private WebElement successDelNoteReturn;

    @FindBy(id = "clickbait-editnotesuccess")
    private WebElement successEditNoteReturn;

    @FindBy(id = "clickbait-savecredsuccess")
    private WebElement successSaveCredReturn;

    @FindBy(id = "clickbait-delcredsuccess")
    private WebElement successDelCredReturn;

    @FindBy(id = "clickbait-editcredsuccess")
    private WebElement successEditCredReturn;

    @FindBy(id = "clickbait-error")
    private WebElement errorReturn;




    public ResultsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void getBackFromSaveNote() {
        successSaveNoteReturn.click();
    }

    public void getBackFromDelNote() {
        successDelNoteReturn.click();
    }

    public void getBackFromEditNote() {
        successEditNoteReturn.click();
    }

    public void getBackFromSaveCred() {
        successSaveCredReturn.click();
    }

    public void getBackFromDelCred() {
        successDelCredReturn.click();
    }

    public void getBackFromEditCred() {
        successEditCredReturn.click();
    }
}
