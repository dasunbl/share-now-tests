package com.dasun.registration.actions;

import com.dasun.registration.model.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.dasun.registration.utils.SeleniumUtil;

public class CheckBoxFieldAction extends CommonFieldAction {
    public CheckBoxFieldAction(Field field) {
        super(field);
    }

    @Override
    public void fill(WebDriver driver, String value) {
        By locator =  By.xpath("//input[@name='" + getCurrentField().getIdentifier() + "']");
        if(getCurrentField().getXpathIdentifier() != null) {
            locator = By.xpath(getCurrentField().getXpathIdentifier());
        }
        SeleniumUtil.waitForGivenElement(driver, locator, 20);
        WebElement element =  driver.findElement(locator);

        int width = element.getSize().getWidth();

        Actions act = new Actions(driver);
        act.moveToElement(element).moveByOffset((width/4)-4, 0).click().perform();


    }
}
