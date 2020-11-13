package com.dasun.registration.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import com.dasun.registration.utils.SetUpUtil;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class PageBase {

    private WebDriver driver;


    public PageBase() {
        driver = SetUpUtil.getInstance().getDriver();
    }

    /**
     * Directly navigate to the page
     * @param path path of the page )
     */
    public void openPage(String path) {
        String url = SetUpUtil.getInstance().getAppUrl();
        driver.manage().window().maximize();
        driver.navigate().to(url+ "/" +path);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void closeBrowser() {
        SetUpUtil.getInstance().closeAndRestDriver();
    }
}
