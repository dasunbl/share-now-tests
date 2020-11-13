package com.dasun.registration.pages.components;

import org.openqa.selenium.By;

public class Header extends ComponentBase {

    private By registerBtn =  By.id("button-register-now");


    public void clickRegisterButton() {
        getDriver().findElement(registerBtn).click();
    }

    public String getRegisterButtonText() {
       return getDriver().findElement(registerBtn).getText();
    }

}
