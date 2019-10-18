package drivermanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;
import java.util.Set;

import static drivermanager.Driver.Browser.currentDriver;

public class Driver implements WebDriver {

    private WebDriver driver;

    public enum Browser { // values to use in Maven CLI, e.g. -Dbrowser=safari to run tests with Safari
        CHROME("chrome"),
        FIREFOX("firefox"),
        SAFARI("safari"),
        HTMLUNIT("htmlunit");

        public static Browser currentDriver;
        private String browser;

        Browser(String browser){
            this.browser = browser;
        }

        public String getBrowser(){
            return browser;
        }
    }

    private static Browser defaultBrowser = Browser.CHROME;

    public Driver(String browserName){

        if(browserName.equalsIgnoreCase(String.valueOf(Browser.CHROME))) {
            this.driver = new ChromeDriver();
            currentDriver = Browser.CHROME;
        }

        if(browserName.equalsIgnoreCase(String.valueOf(Browser.FIREFOX))) {
            this.driver = new FirefoxDriver();
            currentDriver = Browser.FIREFOX;
        }

        if(browserName.equalsIgnoreCase(String.valueOf(Browser.SAFARI))) {
            this.driver = new SafariDriver();
            currentDriver = Browser.SAFARI;
        }

        if(browserName.equalsIgnoreCase(String.valueOf(Browser.HTMLUNIT))) {
            this.driver = new HtmlUnitDriver();
            currentDriver = Browser.HTMLUNIT;
        }
    }

    public static String selectBrowser(Browser browser){
        return browser.getBrowser();
        // Usage: driver = new Driver(selectBrowser(FIREFOX));
        // where 'FIREFOX' is an option from Browser enum
    }

    public static String getParameter(String name){
        String value = System.getProperty(name);
        if (value == null || value.isEmpty()) {
            value = String.valueOf(defaultBrowser); // default browser if -Dbrowser is not defined, or the value does not exist
            currentDriver = defaultBrowser;
        }
        boolean browserFound = false;
        for(Browser b : Browser.values()){
            if (value.equals(b.getBrowser())) {
                browserFound = true;
                break;
            }
        }
        if(!browserFound){
            value = String.valueOf(defaultBrowser);
            currentDriver = defaultBrowser;
        }
        return value;
    }


    @Override
    public void get(String url) {
        this.driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return this.driver.getPageSource();
    }

    @Override
    public void close() {
        this.driver.close();
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.driver.navigate();
    }

    @Override
    public Options manage() {
        return this.driver.manage();
    }
}