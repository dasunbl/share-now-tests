package pages.components;

import org.openqa.selenium.By;
import utils.SeleniumUtil;

public class CookiePopUp extends ComponentBase {

    private By cookieAccept = By.id("uc-btn-accept-banner");


    public void acceptCookies() {
        SeleniumUtil.waitForGivenElement(getDriver(), cookieAccept, 40);
        getDriver().findElement(cookieAccept).click();
    }
}
