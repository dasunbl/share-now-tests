package com.dasun.registration.pages;

import org.openqa.selenium.By;
import com.dasun.registration.utils.SeleniumUtil;

public class PaymentPage extends PageBase {

    private By pageTitleText =  By.xpath("//div[@class='registration-payment__header']/h1");

    public String  getPPageTitleText() {
        SeleniumUtil.waitForGivenElement(getDriver(), pageTitleText, 20);
        return getDriver().findElement(pageTitleText).getText();
    }
}
