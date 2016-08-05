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
    private static final String[] BUTTONS_NAME_ARRAY = {"first", "previous", "next", "last"};

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

    @Test(priority = 3, dependsOnMethods = {"goToDeskExpirationTime"})
    public void sortTabs() {
        for (int i = 2; i <= 5; i++) {
            for (int j = 0; j <= 1; j++) {
                if (apisSystem.deskExpirationTime.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.deskExpirationTime.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"sortTabs"})
    public void clickNavigationButtonsOnList() {
        for (int i = 0; i <= BUTTONS_NAME_ARRAY.length - 1; i++) {
            if (apisSystem.deskExpirationTime.isLoadedClassHaveAttributeInClass()) {
                apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.deskExpirationTime.scrollAndClickNavigationButtons(BUTTONS_NAME_ARRAY[i]);
        }
    }

    @Test(priority = 5, dependsOnMethods = {"clickNavigationButtonsOnList"})
    public void clickNavigationIndexButtonsOnList() {
        for (int i = 0; i <= 3; i++) {
            if (apisSystem.deskExpirationTime.isLoadedClassHaveAttributeInClass()) {
                apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.deskExpirationTime.scrollAndClickNavigationIndexButton(i);
        }
        apisSystem.deskExpirationTime.scrollPageToNavigationWrapper();
    }

    @Test(priority = 6, dependsOnMethods = {"clickNavigationIndexButtonsOnList"})
    public void clickCheckbox() {
        // select checkbox in first position and click
        apisSystem.deskExpirationTime.selectCheckboxInPosition(1);
        apisSystem.deskExpirationTime.clickActionButton(1);
        apisSystem.deskExpirationTime.clickItemFromDropDownMenu(1);
        apisSystem.edit.waitPopupLoaded();
        Assert.assertTrue(apisSystem.edit.isPopupPresent());
    }

    @Test(priority = 7, dependsOnMethods = {"clickCheckbox"})
    public void popupEditInputValueClickCancelButton() {
        apisSystem.edit.inputExpirationTime(12);
        apisSystem.edit.clickButtonSaveOrCancel(false);
        apisSystem.edit.waitInvisibilityPopup();
        Assert.assertFalse(apisSystem.edit.isPopupNotPresent());
    }

}
