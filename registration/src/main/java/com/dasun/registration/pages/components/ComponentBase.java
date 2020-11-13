package com.dasun.registration.pages.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import com.dasun.registration.utils.SetUpUtil;

@Getter
public class ComponentBase {

    private WebDriver driver;

    public ComponentBase() {
        driver = SetUpUtil.getInstance().getDriver();
    }
}
