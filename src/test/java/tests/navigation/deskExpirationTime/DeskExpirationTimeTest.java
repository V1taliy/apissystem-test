package tests.navigation.deskExpirationTime;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class DeskExpirationTimeTest extends Fixture {

    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String DESK_EXPIRATION_TIME_URL = PropertyLoader.loadProperty("deskTime.url");

    @Test(priority = 1)
    public void openWebSiteAndLogin() {
        apisSystem.loginPage.openLoginPage();
        apisSystem.loginPage.waitInvisibilityLoader();
        Assert.assertEquals(apisSystem.loginPage.getPageURL(), LOGIN_URL);
        apisSystem.loginPage.inputUserName(ADMIN_NAME);
        apisSystem.loginPage.inputPassword(ADMIN_PASSWORD);
        apisSystem.loginPage.clickLoginButton();
        Assert.assertTrue(apisSystem.mainPage.isWelcomeToApisSystemPresent());
        impWait = PropertyLoader.loadProperty("wait.timeout1sec");
        driverWrapper.manage().timeouts().implicitlyWait(Long.parseLong(impWait), TimeUnit.MILLISECONDS);
    }

    @Test(priority = 2, dependsOnMethods = {"openWebSiteAndLogin"})
    public void goToDeskExpirationTime() {
        apisSystem.mainPage.clickOnNavigationItem(5);
        Assert.assertEquals(apisSystem.deskExpirationTime.getCurrentPageURL(), DESK_EXPIRATION_TIME_URL);
    }

}
