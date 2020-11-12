package actions;

import model.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.SeleniumUtil;

public class SelectFieldAction extends CommonFieldAction {

    public SelectFieldAction(Field field) {
        super(field);
    }

    @Override
    public void fill(WebDriver driver, String value) {
        By locator =  By.xpath("//select[@name='" + getCurrentField().getIdentifier() + "']");
        if(getCurrentField().getXpathIdentifier() != null) {
            locator = By.xpath(getCurrentField().getXpathIdentifier());
        }
        SeleniumUtil.waitForGivenElement(driver, locator, 20);
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(value);
    }
}
