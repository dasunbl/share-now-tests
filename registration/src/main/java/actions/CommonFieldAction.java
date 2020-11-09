package actions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter (AccessLevel.PROTECTED)
@Setter (AccessLevel.PROTECTED)
public class CommonFieldAction  {

    private Field currentField;

    public CommonFieldAction (Field field) {
        this.currentField = field;
    }

    public void fill(WebDriver driver, String value) {
        driver.findElement(By.name(currentField.getIdentifier())).sendKeys(value);
    }
}
