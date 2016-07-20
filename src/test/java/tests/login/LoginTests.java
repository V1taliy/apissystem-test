package tests.login;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

public class LoginTests extends Fixture {

    private static final Logger log = Logger.getLogger(LoginTests.class);
    private static final String FAKE_NAME = PropertyLoader.loadProperty("fake.NAME");
    private static final String FAKE_PASSWORD = PropertyLoader.loadProperty("fake.PASSWORD");
    private static final String USER_NAME = PropertyLoader.loadProperty("user.NAME");
    private static final String USER_PASSWORD = PropertyLoader.loadProperty("user.PASSWORD");
    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.NAME");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.PASSWORD");

    @Test(priority = 1)
    public void openWebSite() {
        apisSystem.loginPage.openLoginPage();
        apisSystem.loginPage.waitInvisibilityLoader();
        Assert.assertEquals(apisSystem.loginPage.getPageURL(),
                "http://development.apis.office.dev/manage/login");
    }



}
