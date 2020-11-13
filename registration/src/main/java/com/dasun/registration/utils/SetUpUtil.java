package com.dasun.registration.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.dasun.registration.types.BrowserType;
import com.dasun.registration.types.Environment;

public class SetUpUtil {

    private static SetUpUtil setUpUtil;
    private WebDriver driver;
    private String url;


    private SetUpUtil (){}

    public static SetUpUtil getInstance() {
        if (setUpUtil == null) {
            setUpUtil =  new SetUpUtil();
        }

        return setUpUtil;
    }


    /**
     * Get web driver instance based on configurations
     * @return WebDriver instance
     * @throws UnsupportedOperationException
     */
    public  WebDriver getDriver() throws UnsupportedOperationException{
        BrowserType browser;
        if (driver != null) {
            return  driver;
        }

        try {
            browser = BrowserType.valueOf(System.getProperty("browser"));
        } catch (IllegalArgumentException | NullPointerException e) {
            browser = BrowserType.CHROME;
        }

        switch (browser){
            case FIREFOX:
                driver = getFireFoxDriver();
                break;
            default:
                driver = getChromeDriver();
        }

        return driver;
    }

    /**
     * Get app url for given environment
     * @return App Url
     */
    public String getAppUrl()
    {
        Environment env ;
        if (url != null) {
            return  url;
        }

        try {
            env = Environment.valueOf(System.getProperty("env"));
        } catch (IllegalArgumentException | NullPointerException e) {
            env = Environment.QA;
        }

        switch (env){
            case PROD:
              url =  ConfigurationProvider.getConfigurationByKey("url.prod");
                break;
            case STAGING:
              url =   ConfigurationProvider.getConfigurationByKey("url.staging");
                break;
            default:
               url =  ConfigurationProvider.getConfigurationByKey("url.qa");
        }

     return  url;

    }

    /**
     * Close and Rest Driver
     */
    public void closeAndRestDriver() {
         if (driver != null ){
             driver.quit();
             driver = null;
         }
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver();
    }

    private  WebDriver getFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}