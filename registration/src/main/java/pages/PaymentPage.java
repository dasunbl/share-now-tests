package pages;

import org.openqa.selenium.By;
import utils.SeleniumUtil;

public class PaymentPage extends PageBase {

    private By pageTitleText =  By.xpath("//div[@class='registration-payment__header']/h1");

    public String  getPPageTitleText() {
        SeleniumUtil.waitForGivenElement(getDriver(), pageTitleText, 20);
        return getDriver().findElement(pageTitleText).getText();
    }
}
