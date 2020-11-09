package actions;

import model.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectFieldAction extends CommonFieldAction {

    public SelectFieldAction(Field field) {
        super(field);
    }

    @Override
    public void fill(WebDriver driver, String value) {
        Select select = new Select(driver.findElement(By.name(getCurrentField().getIdentifier())));
        select.selectByVisibleText(value);
    }
}
