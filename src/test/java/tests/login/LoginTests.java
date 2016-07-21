package tests.login;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

public class LoginTests extends Fixture {

    private static final Logger log = Logger.getLogger(LoginTests.class);
    private static final String FAKE_NAME = PropertyLoader.loadProperty("fake.name");
    private static final String FAKE_PASSWORD = PropertyLoader.loadProperty("fake.password");
    private static final String USER_NAME = PropertyLoader.loadProperty("user.name");
    private static final String USER_PASSWORD = PropertyLoader.loadProperty("user.password");
    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");

    @Test(priority = 1)
    public void openWebSite() {
        apisSystem.loginPage.openLoginPage();
        apisSystem.loginPage.waitInvisibilityLoader();
        Assert.assertEquals(apisSystem.loginPage.getPageURL(),
                "http://development.apis.office.dev/manage/login");
    }

    @Test(priority = 2, dependsOnMethods = {"openWebSite"})
    public void negativeLogin() {
        apisSystem.loginPage.inputUserName(FAKE_NAME);
        apisSystem.loginPage.inputPassword(FAKE_PASSWORD);
        apisSystem.loginPage.clickLoginButton();
        apisSystem.loginPage.waitAlertInvalidUserOrPass();
        Assert.assertTrue(apisSystem.loginPage.isAlertInvalidUserOrPassPresent());
    }

    @Test(priority = 3, dependsOnMethods = {"openWebSite"})
    public void negativeLogin2() {
        apisSystem.loginPage.inputUserName(FAKE_NAME + 1);
        apisSystem.loginPage.inputPassword(FAKE_PASSWORD + 1);
        apisSystem.loginPage.clickLoginButton();
        apisSystem.loginPage.waitAlertInvalidUserOrPass();
        Assert.assertTrue(apisSystem.loginPage.isAlertInvalidUserOrPassPresent());
    }

    @Test(priority = 4, dependsOnMethods = {"openWebSite"})
    public void negativeLoginFailedRequired() {
        apisSystem.loginPage.inputUserNameAndPasswordAndClearFields(FAKE_NAME, FAKE_PASSWORD);
        Assert.assertEquals(apisSystem.loginPage.isFieldRequiredUserNameTextPresent(), true);
    }

    @Test(priority = 5, dependsOnMethods = {"openWebSite"})
    public void userLogin() {
        apisSystem.loginPage.inputUserName(USER_NAME);
        apisSystem.loginPage.inputPassword(USER_PASSWORD);
        apisSystem.loginPage.clickLoginButton();
        Assert.assertTrue(apisSystem.mainPage.isWelcomeToApisSystemPresent());
        apisSystem.mainPage.clickLogoutButton();
    }

    @Test(priority = 6, dependsOnMethods = {"openWebSite"})
    public void adminLogin() {
        apisSystem.loginPage.inputUserName(ADMIN_NAME);
        apisSystem.loginPage.inputPassword(ADMIN_PASSWORD);
        apisSystem.loginPage.clickLoginButton();
        Assert.assertTrue(apisSystem.mainPage.isWelcomeToApisSystemPresent());
        apisSystem.mainPage.clickLogoutButton();
    }

}
