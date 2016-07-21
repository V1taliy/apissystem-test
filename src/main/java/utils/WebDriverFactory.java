package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public static final String browserName = PropertyLoader.loadProperty("browser.name");
    public static final String browserVersion = PropertyLoader.loadProperty("browser.version");
    public static final String platform = PropertyLoader.loadProperty("browser.platform");
//    public static final String grid = PropertyLoader.loadProperty("grid2.hub");

    /*
    * Browsers constants
    * if necessary, you can add new browsers
    * */
    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";
    private static final String MOBILE_EMULATOR = "mobileEmulator";
//    private static final String PHANTOMJS = "phantomjs";

    private static final String FIREFOX_PATH = "C:\\Users\\alexanderba\\AppData\\Local\\Mozilla Firefox\\firefox.exe";
    private static final String CHROME_PATH = "C:\\Users\\alexanderba\\AppData\\Local\\Google\\Chrome\\User Data\\chromedriver.exe";
    private static final String DEVICE_NAME = "Apple iPhone 5";
    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    public static WebDriverWrapper driverWrapper;

    public WebDriverFactory() {
    }

    /**
     * static method that returns the WebDriver
     *
     * @return new web driver
     */
    public static WebDriverWrapper initDriver() {

        if (FIREFOX.equals(browserName)) {

            System.setProperty("webdriver.firefox.bin", FIREFOX_PATH);
            driverWrapper = new WebDriverWrapper(new FirefoxDriver());

        } else if (CHROME.equals(browserName)) {

            System.setProperty("webdriver.chrome.driver", CHROME_PATH);
            ChromeOptions options = new ChromeOptions();
            driverWrapper = new WebDriverWrapper(new ChromeDriver(options));

        } else if (MOBILE_EMULATOR.equals(browserName)) {

            System.setProperty("webdriver.chrome.driver", CHROME_PATH);

            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", DEVICE_NAME);

            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);

            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

            driverWrapper = new WebDriverWrapper(new ChromeDriver(WebDriverFactory.capabilities));

        } else {
            Assert.fail("invalid driver name");
        }

        driverWrapper.manage().deleteAllCookies();
        driverWrapper.manage().window().maximize();
        return driverWrapper;

    }

}
