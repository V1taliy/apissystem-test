package tests.navigation.deskExpirationTime;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class DeskExpirationTimeTests extends Fixture {

    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String DESK_EXPIRATION_TIME_URL = PropertyLoader.loadProperty("deskTime.url");
    private static final String BRANDS_URL = PropertyLoader.loadProperty("brands.url");
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

    @Test(priority = 4, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickNavigationButtonsOnList() {
        for (int i = 0; i <= BUTTONS_NAME_ARRAY.length - 1; i++) {
            if (apisSystem.deskExpirationTime.isLoadedClassHaveAttributeInClass()) {
                apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.deskExpirationTime.scrollAndClickNavigationButtons(BUTTONS_NAME_ARRAY[i]);
        }
    }

    @Test(priority = 5, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickNavigationIndexButtonsOnList() {
        for (int i = 0; i <= 3; i++) {
            if (apisSystem.deskExpirationTime.isLoadedClassHaveAttributeInClass()) {
                apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.deskExpirationTime.scrollAndClickNavigationIndexButton(i);
        }
        apisSystem.deskExpirationTime.scrollPageToNavigationWrapper();
    }

    @Test(priority = 6, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickCheckbox1() {
        apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
        clickCheckbox(1);
    }

    @Test(priority = 7, dependsOnMethods = {"goToDeskExpirationTime"})
    public void popupEditInputValueClickCancelButton() {
        int expectedResult = apisSystem.deskExpirationTime.getExpirationTimeValue(1);
        apisSystem.edit.inputExpirationTime(11);
        apisSystem.edit.clickButtonSaveOrCancel(false);
        apisSystem.edit.waitInvisibilityPopup();
        Assert.assertEquals(apisSystem.deskExpirationTime.getExpirationTimeValue(1), expectedResult);
    }

    @Test(priority = 8, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickCheckbox2() {
        clickCheckbox(2);
    }

    @Test(priority = 9, dependsOnMethods = {"goToDeskExpirationTime"})
    public void popupEditInputValueClickSaveButton() {
        apisSystem.edit.inputExpirationTime(100);
        apisSystem.edit.clickButtonSaveOrCancel(true);
        apisSystem.edit.waitInvisibilityPopup();
        apisSystem.deskExpirationTime.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.deskExpirationTime.isMessageSuccessPresent());
    }

    @Test(priority = 10, dependsOnMethods = {"goToDeskExpirationTime"})
    public void switchToBrands() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.deskExpirationTime.getCurrentPageURL(), BRANDS_URL);
    }

    @Test(priority = 11, dependsOnMethods = {"goToDeskExpirationTime"})
    public void changeSomeBrandsEnabled() {
        apisSystem.brandsPage.waitLoadedAttributeToBeEmptyClass();
//        apisSystem.brandsPage.selectTableSort(2);
        apisSystem.brandsPage.waitLoadedAttributeToBeEmptyClass();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        apisSystem.brandsPage.selectTableSort(1);
        apisSystem.brandsPage.clickBrandCheckbox(1);
        apisSystem.brandsPage.clickToggleButton(true);
        apisSystem.brandsPage.waitLoadedAttributeToBeEmptyClass();
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
    }

    @Test(priority = 12, dependsOnMethods = {"goToDeskExpirationTime"})
    public void switchToDeskExpTime() {
        apisSystem.mainPage.clickOnNavigationItem(5);
        Assert.assertEquals(apisSystem.deskExpirationTime.getCurrentPageURL(), DESK_EXPIRATION_TIME_URL);
    }

    @Test(priority = 13, dependsOnMethods = {"goToDeskExpirationTime"})
    public void selectBrand() {
        apisSystem.deskExpirationTime.filterClickedOnSelectBrand();
        apisSystem.deskExpirationTime.filterClickOnSearchOrReset(true);
        apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.deskExpirationTime.getBrandNameFromList(),
                apisSystem.deskExpirationTime.getFilterBrandFiledBrandName(1));
    }

    @Test(priority = 14, dependsOnMethods = {"goToDeskExpirationTime"})
    public void selectDesk() {
        apisSystem.deskExpirationTime.filterClickOnDesk();
        apisSystem.deskExpirationTime.filterClikedOnDesk();
        apisSystem.deskExpirationTime.filterClickOnSearchOrReset(true);
        apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
    }

    @Test(priority = 15, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickResetButton() {
        String expectedResult = "Select";
        apisSystem.deskExpirationTime.filterClickOnSearchOrReset(false);
        apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.deskExpirationTime.filterGetDeskValue(), expectedResult);
        Assert.assertEquals(apisSystem.deskExpirationTime.filterGetBrandValue(), expectedResult);
    }

    @Test(priority = 16, dependsOnMethods = {"goToDeskExpirationTime"})
    public void selectBrandAndDesk() {
        apisSystem.deskExpirationTime.filterClickedOnSelectBrand();
        apisSystem.deskExpirationTime.waitDeskToBeActive();
        int expectedResult = apisSystem.deskExpirationTime.filterClikedOnDesk();
        apisSystem.deskExpirationTime.filterClickOnSearchOrReset(true);
        apisSystem.deskExpirationTime.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.deskExpirationTime.listGetDeskName(),
                apisSystem.deskExpirationTime.filterGetDeskValue(expectedResult));
    }

    private void clickCheckbox(int checkboxPosition) {
        // select checkbox in some position and click
        apisSystem.deskExpirationTime.selectCheckboxInPosition(checkboxPosition);
        apisSystem.deskExpirationTime.clickActionButton(checkboxPosition);
        apisSystem.deskExpirationTime.clickItemFromDropDownMenu(1);
        apisSystem.edit.waitPopupLoaded();
        Assert.assertTrue(apisSystem.edit.isPopupPresent());
    }

}
