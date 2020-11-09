package pages.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import utils.SetUpUtil;

@Getter
public class ComponentBase {

    private WebDriver driver;

    public ComponentBase() {
        driver = SetUpUtil.getInstance().getDriver();
    }
}
