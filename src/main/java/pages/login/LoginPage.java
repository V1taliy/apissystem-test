package pages.login;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class LoginPage extends Page {

    private static final Logger log = Logger.getLogger(LoginPage.class);


    public LoginPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Input user name in user name field on a page
     *
     * @param userName user name data
     */
    public void inputUserName(String userName) {
        web.clickLink("userNameField");
        web.clearAndInput("userNameField", userName);
    }

    /**
     * Input password in password field on a page
     *
     * @param password password data
     */
    public void inputPassword(String password) {
        web.clickLink("passwordField");
        web.clearAndInput("passwordField", password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        web.clickButton("loginButton");
    }

    /**
     * Is alert preset on a page
     *
     * @return true if alert 'Invalid username or password' present on a page, otherwise false
     */
    public boolean isAlertInvalidUserOrPassPresent() {
        return web.isElementPresent("alertInvalidUserOrPass",
                Integer.parseInt(PropertyLoader.loadProperty("wait.timeout5sec")));
    }

    /**
     * Check is text 'Field required' present on a page
     *
     * @return true if text 'Field required' for userName field and
     * password field present, otherwise false
     */
    public boolean isFieldRequiredUserNameTextPresent() {
        return web.isElementPresent("fieldRequiredUserNameText") &&
                web.isElementPresent("fieldRequiredPasswordText");
    }

}
