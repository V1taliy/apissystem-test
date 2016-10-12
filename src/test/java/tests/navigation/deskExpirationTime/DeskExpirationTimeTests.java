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
    private static final String[] BUTTONS_NAME_ARRAY = {"First", "Previous", "Next", "Last"};

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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= 1; j++) {
                if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.deskExpirationTime.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickNavigationButtonsOnList() {
        for (int i = 0; i <= BUTTONS_NAME_ARRAY.length - 1; i++) {
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.listNavigationButtons.scrollAndClickNavigationButtons(BUTTONS_NAME_ARRAY[i]);
        }
    }

    @Test(priority = 5, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickNavigationIndexButtonsOnList() {
        for (int i = 0; i <= 3; i++) {
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.listNavigationButtons.scrollAndClickNavigationIndexButton(i);
        }
        apisSystem.deskExpirationTime.scrollPageToNavigationWrapper();
    }

    @Test(priority = 6, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickCheckbox1() {
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
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
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 10, dependsOnMethods = {"goToDeskExpirationTime"})
    public void switchToBrands() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.deskExpirationTime.getCurrentPageURL(), BRANDS_URL);
    }

    @Test(priority = 11, dependsOnMethods = {"goToDeskExpirationTime"})
    public void changeSomeBrandsEnabled() {
        try {
            Thread.sleep(1000);
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.listEntity.clickCheckboxSelectAll();
        apisSystem.brandsPage.clickBrandCheckbox(0);
        apisSystem.brandsPage.clickToggleButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 12, dependsOnMethods = {"goToDeskExpirationTime"})
    public void switchToDeskExpTime() {
        apisSystem.mainPage.clickOnNavigationItem(5);
        Assert.assertEquals(apisSystem.deskExpirationTime.getCurrentPageURL(), DESK_EXPIRATION_TIME_URL);
    }

    @Test(priority = 13, dependsOnMethods = {"goToDeskExpirationTime"})
    public void selectBrand() {
        apisSystem.deskExpirationTime.filterClickedOnSelectBrand();
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.deskExpirationTime.getBrandNameFromList(),
                apisSystem.deskExpirationTime.getFilterBrandFiledBrandName(1));
    }

    @Test(priority = 14, dependsOnMethods = {"goToDeskExpirationTime"})
    public void selectDesk() {
        apisSystem.deskExpirationTime.filterClickOnDesk();
        apisSystem.deskExpirationTime.filterClikedOnDesk();
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
    }

    @Test(priority = 15, dependsOnMethods = {"goToDeskExpirationTime"})
    public void clickResetButton() {
        String expectedResultForBrand = "All Brands";
        String expectedResultForDesk = "All Desk";
        apisSystem.filterEntity.clickSearchOrResetButton(false);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.deskExpirationTime.filterGetDeskValue(), expectedResultForDesk);
        Assert.assertEquals(apisSystem.deskExpirationTime.filterGetBrandValue(), expectedResultForBrand);
    }

    @Test(priority = 16, dependsOnMethods = {"goToDeskExpirationTime"})
    public void selectBrandAndDesk() {
        apisSystem.deskExpirationTime.filterClickedOnSelectBrand();
        apisSystem.deskExpirationTime.waitDeskToBeActive();
        int expectedResult = apisSystem.deskExpirationTime.filterClikedOnDesk();
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.deskExpirationTime.listGetDeskName(),
                apisSystem.deskExpirationTime.filterGetDeskValue(expectedResult));
    }

    private void clickCheckbox(int checkboxPosition) {
        // select checkbox in some position and click
        apisSystem.deskExpirationTime.selectCheckboxInPosition(checkboxPosition);
        apisSystem.deskExpirationTime.clickActionButton(checkboxPosition);
        apisSystem.edit.waitPopupLoaded();
        Assert.assertTrue(apisSystem.edit.isPopupPresent());
    }

}
