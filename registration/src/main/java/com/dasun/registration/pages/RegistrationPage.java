package com.dasun.registration.pages;

import com.dasun.registration.actions.FieldActionProvider;
import com.dasun.registration.model.Field;
import org.openqa.selenium.By;


public class RegistrationPage extends PageBase {

    private By registrationButton = By.id("registration-save-button");

    public void fillGivenField(Field field, String value){
        FieldActionProvider actionProvider = new FieldActionProvider();
        actionProvider.getFieldAction(field).fill(getDriver(), value);
    }

    public String getValidationMessage(Field field) {
        FieldActionProvider actionProvider = new FieldActionProvider();
        return actionProvider.getFieldAction(field).getValidationMessage(getDriver(),field);
    }

    public void clickRegistrationButton() {
        getDriver().findElement(registrationButton).click();
    }


}
