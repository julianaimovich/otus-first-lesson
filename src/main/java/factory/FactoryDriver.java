package factory;

import exceptions.BrowserNotSupportedException;
import factory.impl.ChromeWebDriver;
import org.openqa.selenium.WebDriver;

public class FactoryDriver {

    private final static String BROWSER_NAME = System.getProperty("browser", "chrome");

    public WebDriver getDriver() throws BrowserNotSupportedException {

        switch(BROWSER_NAME) {
            case "chrome" -> {
                ChromeWebDriver chromeWebDriver = new ChromeWebDriver();
                chromeWebDriver.downloadLocalWebDriver(BROWSER_NAME);
                return new ChromeWebDriver().newDriver();
            }
            default ->
                throw new BrowserNotSupportedException(BROWSER_NAME);
        }
    }
}