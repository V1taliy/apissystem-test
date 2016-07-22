package tests.navigation.users;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

public class UsersTest extends Fixture {

    private static final Logger log = Logger.getLogger(UsersTest.class);
    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String USERS_URL = PropertyLoader.loadProperty("users.url");


    @Test(priority = 1)
    public void openWebSiteAndLogin() {
        apisSystem.loginPage.openLoginPage();
        apisSystem.loginPage.waitInvisibilityLoader();
        Assert.assertEquals(apisSystem.loginPage.getPageURL(), LOGIN_URL);
        apisSystem.loginPage.inputUserName(ADMIN_NAME);
        apisSystem.loginPage.inputPassword(ADMIN_PASSWORD);
        apisSystem.loginPage.clickLoginButton();
        Assert.assertTrue(apisSystem.mainPage.isWelcomeToApisSystemPresent());
    }

    @Test(priority = 2, dependsOnMethods = {"openWebSiteAndLogin"})
    public void goToUsersTab() {
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    @Test(priority = 3, dependsOnMethods = {"goToUsersTab"})
    public void sortTabs() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 0; j <= 1; j++) {
                apisSystem.usersPage.selectTableSort(i);
                apisSystem.usersPage.waitInvisibilityProcessing();
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"sortTabs"})
    public void selectUserCheckboxAndClickToggleButtons() {
        boolean button = true;
        for (int i = 1; i <= 2; i++) {
            apisSystem.usersPage.clickUserCheckbox(i);
            apisSystem.usersPage.clickToggleButton(button);
            Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
            button = false;
        }
    }

    @Test(priority = 5, dependsOnMethods = {"selectUserCheckboxAndClickToggleButtons"})
    public void foo() {
        apisSystem.usersPage.clickActionButton(1);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
        // TODO
    }

}
