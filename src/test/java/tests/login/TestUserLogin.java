package tests.login;

import org.testng.Assert;
import tests.Fixture;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class TestUserLogin extends Fixture {

    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String TEST_USER_7 = PropertyLoader.loadProperty("testUser7.name");
    private static final String TEST_USER_8 = PropertyLoader.loadProperty("testUser8.name");
    private static final String TEST_USER_PASSWORD = PropertyLoader.loadProperty("testUsers.password");
    private static final int newImpWait = 300;

    public static void openWebSiteAndLoginTestsUsers() {
        apisSystem.loginPage.openLoginPage();
        apisSystem.loginPage.waitInvisibilityLoader();
        Assert.assertEquals(apisSystem.loginPage.getPageURL(), LOGIN_URL);
        impWait = String.valueOf(newImpWait);
        driverWrapper.manage().timeouts().
                implicitlyWait(Long.parseLong(impWait), TimeUnit.MILLISECONDS);
        testUsersLogin();
    }

    /**
     * Login as test user
     *
     * @param testUserName test user name
     */
    public static void testUserLogin(String testUserName) {
        apisSystem.loginPage.inputUserName(testUserName);
        apisSystem.loginPage.inputPassword(TEST_USER_PASSWORD);
        apisSystem.loginPage.clickLoginButton();
        apisSystem.loginPage.waitInvisibilityPanelBody();
        Assert.assertTrue(apisSystem.mainPage.isWelcomeToApisSystemPresent());
    }

    private static void testUsersLogin() {
        for (int i = 0; i <= 1; i++) {
            if (i == 0) {
                apisSystem.loginPage.inputUserName(TEST_USER_7);
            } else {
                apisSystem.loginPage.inputUserName(TEST_USER_8);
            }
            apisSystem.loginPage.inputPassword(TEST_USER_PASSWORD);
            apisSystem.loginPage.clickLoginButton();
            apisSystem.loginPage.waitInvisibilityPanelBody();
            Assert.assertTrue(apisSystem.mainPage.isWelcomeToApisSystemPresent());
            apisSystem.mainPage.clickLogoutButton();
        }
    }
}
