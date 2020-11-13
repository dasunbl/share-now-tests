package com.dasun.registration.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {

    private SeleniumUtil() {}

    /**
     * Wait For Given Element
     * @param driver web driver
     * @param locator Locator for the element
     * @param waitingTime Time to wait
     */
    public static void waitForGivenElement(WebDriver driver, By locator, int waitingTime) {
        WebDriverWait wait = new WebDriverWait(driver,waitingTime);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
