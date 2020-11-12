package actions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtil;

import java.util.List;

@Getter (AccessLevel.PROTECTED)
@Setter (AccessLevel.PROTECTED)
public class CommonFieldAction  {

    private Field currentField;

    public CommonFieldAction (Field field) {
        this.currentField = field;
    }

    public void fill(WebDriver driver, String value) {
        By locator =  By.xpath("//input[@name='"+currentField.getIdentifier()+"']");
        if(currentField.getXpathIdentifier() != null) {
          locator = By.xpath(currentField.getXpathIdentifier());
        }
        SeleniumUtil.waitForGivenElement(driver, locator, 20);
        driver.findElement(locator).sendKeys(value);
    }

    public String getValidationMessage(WebDriver driver, Field field) {
        By locator =  By.xpath("//input[@name='"+ currentField.getIdentifier()+"']/following-sibling::p");
        if(currentField.getXpathIdentifier() != null) {
            locator = By.xpath(currentField.getXpathIdentifier()+"/following-sibling::p");
        }

        SeleniumUtil.waitForGivenElement(driver, locator, 20);
        return driver.findElement(locator).getText();
    }
}
