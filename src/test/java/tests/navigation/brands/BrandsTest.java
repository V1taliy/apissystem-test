package tests.navigation.brands;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class BrandsTest extends Fixture {

    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String BRANDS_URL = PropertyLoader.loadProperty("brands.url");
    private static final String[] TEST_DATA = {"testAppKey", "testApiUser",
            "testApiPassword", "testBrandName", "testDomain"};

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
    public void goToBrandsTab() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        Assert.assertEquals(apisSystem.brandsPage.getCurrentPageURL(), BRANDS_URL);
    }

    @Test(priority = 3, dependsOnMethods = {"goToBrandsTab"})
    public void sortTabs() {
        for (int i = 2; i <= 4; i++) {
            for (int j = 0; j <= 1; j++) {
                if (apisSystem.brandsPage.isProcessingDisplayed()) {
                    apisSystem.brandsPage.waitInvisibilityProcessing();
                }
                apisSystem.brandsPage.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"sortTabs"})
    public void selectBrandAndClickToggleButtons() {
        boolean button = false;
        int brandPosition = 1;
        for (int i = 1; i <= 2; i++) {
            if (apisSystem.brandsPage.isProcessingDisplayed()) {
                apisSystem.brandsPage.waitInvisibilityProcessing();
            }
            apisSystem.brandsPage.clickBrandCheckbox(brandPosition);
            apisSystem.brandsPage.clickToggleButton(button);
            apisSystem.brandsPage.waitMessageSuccessPresent();
            Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
        }
    }

    @Test(priority = 6, dependsOnMethods = {"selectBrandAndClickToggleButtons"})
    public void popupCreateBrandInputFieldsAndClickCancel() {
        clickCreateBrand();
        apisSystem.createBrand.inputAppKey(TEST_DATA[0]);
        apisSystem.createBrand.inputApiUser(TEST_DATA[1]);
        apisSystem.createBrand.inputApiPassword(TEST_DATA[2]);
        apisSystem.createBrand.inputBrandName(TEST_DATA[3]);
        apisSystem.createBrand.inputDomain(TEST_DATA[4]);
        apisSystem.createBrand.selectCheckboxEnabled();
        Assert.assertEquals(apisSystem.createBrand.clickButtonSaveOrCancel(false), true);
    }

    @Test(priority = 7, dependsOnMethods = {"popupCreateBrandInputFieldsAndClickCancel"})
    public void popupCreateBrandFailedDomain() {
        apisSystem.createBrand.waitInvisibilityPopup();
        clickCreateBrand();
        apisSystem.createBrand.inputAppKey(TEST_DATA[0]);
        apisSystem.createBrand.inputApiUser(TEST_DATA[1]);
        apisSystem.createBrand.inputApiPassword(TEST_DATA[2]);
        apisSystem.createBrand.inputBrandName(TEST_DATA[3]);
        apisSystem.createBrand.inputDomain(TEST_DATA[4]);
        apisSystem.createBrand.selectCheckboxEnabled();
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.createBrand.isErrorMessagePresent());
    }

    @Test(priority = 8, dependsOnMethods = {"popupCreateBrandFailedDomain"})
    public void popupCreateBrandCorrectDomain() {
        apisSystem.createBrand.inputDomain(TEST_DATA[4] + ".com");
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
    }

    @Test(priority = 9, dependsOnMethods = {"popupCreateBrandCorrectDomain"})
    public void createSecondBrand() {
        apisSystem.createBrand.waitInvisibilityPopup();
        clickCreateBrand();
        apisSystem.createBrand.inputAppKey(TEST_DATA[0] + 2);
        apisSystem.createBrand.inputApiUser(TEST_DATA[1] + 2);
        apisSystem.createBrand.inputApiPassword(TEST_DATA[2] + 2);
        apisSystem.createBrand.inputBrandName(TEST_DATA[3] + 2);
        apisSystem.createBrand.inputDomain(TEST_DATA[4] + "2.com");
        apisSystem.createBrand.selectCheckboxEnabled();
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
    }

    @Test(priority = 10, dependsOnMethods = {"createSecondBrand"})
    public void deleteFirstTestBrandClickCancel() {
        apisSystem.brandsPage.filterInputBrandName(TEST_DATA[3]);
        apisSystem.brandsPage.waitInvisibilityOverlay();
        apisSystem.brandsPage.filterClickSearchOrReset(true);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(6);
        apisSystem.deleteBrand.waitPopupLoaded();
        Assert.assertTrue(apisSystem.deleteBrand.isPopupDeleteBrandPresent());
        apisSystem.deleteBrand.clickButtonCancelOrYes(false);
    }

    @Test(priority = 11, dependsOnMethods = {"deleteFirstTestBrandClickCancel"})
    public void deleteFirstTestBrandClickYes() {
        apisSystem.deleteBrand.waitInvisibilityPopup();
        deleteBrand();
    }

    @Test(priority = 12, dependsOnMethods = {"deleteFirstTestBrandClickYes"})
    public void editSecondTestBrand() {
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(1);
        apisSystem.editBrand.inputApiUser(TEST_DATA[1] + "edit");
        apisSystem.editBrand.inputBrandName(TEST_DATA[3] + "edit");
        apisSystem.editBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
        apisSystem.editBrand.waitInvisibilityPopup();
    }

    @Test(priority = 13, dependsOnMethods = {"editSecondTestBrand"})
    public void deleteSecondTestBrandClickYes() {
        deleteBrand();
    }

    @Test(priority = 14, dependsOnMethods = {"deleteSecondTestBrandClickYes"})
    public void clickDisableAndEnableFromCogwheel() {
        apisSystem.brandsPage.filterClickSearchOrReset(false);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(3);
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(4);
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
    }

    @Test(priority = 15, dependsOnMethods = {"clickDisableAndEnableFromCogwheel"})
    public void filterBrandNameCorrect() {
        String brandName = apisSystem.brandsPage.getValueFromFirstBrandName("filterFirstBrandName");
        apisSystem.brandsPage.filterInputBrandName(brandName);
        apisSystem.brandsPage.filterClickAndSelectEnabled(true);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        Assert.assertEquals(brandName, apisSystem.brandsPage.getInputValue("filterBrandNameInput"));
    }

    @Test(priority = 16, dependsOnMethods = {"filterBrandNameCorrect"})
    public void filterBrandNameFail() {
        apisSystem.brandsPage.filterInputBrandName("failBrandName");
        apisSystem.brandsPage.filterClickSearchOrReset(true);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        Assert.assertEquals(apisSystem.brandsPage.getValueFromFirstBrandName("noDataAvailable"),
                "No data available in table");
        apisSystem.brandsPage.filterClickSearchOrReset(false);
        apisSystem.brandsPage.waitInvisibilityProcessing();
    }

    @Test(priority = 17, dependsOnMethods = {"filterBrandNameFail"})
    public void filterEnabledModeEnable() {
        apisSystem.brandsPage.filterClickAndSelectEnabled(true);
        apisSystem.brandsPage.filterClickSearchOrReset(true);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        if (apisSystem.brandsPage.getFirstPosition() == 1) {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("noDataAvailable"),
                    "No data available in table");
        } else {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("firstPositionBrandEnabled"),
                    "true");
        }
    }

    @Test(priority = 18, dependsOnMethods = {"filterEnabledModeEnable"})
    public void filterEnabledModeDisable() {
        apisSystem.brandsPage.filterClickSearchOrReset(false);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        apisSystem.brandsPage.filterClickAndSelectEnabled(false);
        apisSystem.brandsPage.filterClickAndSelectEnabled(false);
        apisSystem.brandsPage.filterClickSearchOrReset(true);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        if (apisSystem.brandsPage.getFirstPosition() == 1) {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("noDataAvailable"),
                    "No data available in table");
        } else {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("firstPositionBrandEnabled"),
                    "false");
        }
    }

    @Test(priority = 19, dependsOnMethods = {"filterEnabledModeDisable"})
    public void filterAllFilters() {
        boolean enabledStatus;
        apisSystem.brandsPage.filterClickSearchOrReset(false);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        String brandName = apisSystem.brandsPage.getValueFromFirstBrandName("firstPositionBrandName");
        apisSystem.brandsPage.filterInputBrandName(brandName);
        String enableStatusStr = apisSystem.brandsPage.getValueFromFirstBrandName("firstPositionBrandEnabled");
        if (enableStatusStr.equals("true")) {
            apisSystem.brandsPage.filterClickAndSelectEnabled(true);
            enabledStatus = true;
        } else {
            apisSystem.brandsPage.filterClickAndSelectEnabled(false);
            enabledStatus = false;
        }
        apisSystem.brandsPage.filterClickSearchOrReset(true);
        apisSystem.brandsPage.waitInvisibilityProcessing();
        Assert.assertEquals(apisSystem.brandsPage.getInputValue("filterBrandNameInput"), brandName);
        Assert.assertEquals(apisSystem.brandsPage.getValueFromFirstBrandName("firstPositionBrandEnabled"),
                String.valueOf(enabledStatus));
    }

    private void clickCreateBrand() {
        apisSystem.brandsPage.createOrDeleteBrand(true);
        apisSystem.createBrand.waitPopupLoaded();
        Assert.assertTrue(apisSystem.createBrand.isPopupPresent());
    }

    private void deleteBrand() {
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(6);
        apisSystem.deleteBrand.waitPopupLoaded();
        apisSystem.deleteBrand.clickButtonCancelOrYes(true);
        Assert.assertTrue(apisSystem.brandsPage.isMessageSuccessPresent());
        apisSystem.deleteBrand.waitInvisibilityPopup();
    }

}
