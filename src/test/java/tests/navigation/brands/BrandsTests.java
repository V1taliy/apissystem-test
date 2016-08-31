package tests.navigation.brands;

import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class BrandsTests extends Fixture {

    private static final Logger log = Logger.getLogger(BrandsTests.class);

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
                if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.brandsPage.selectTableSort(i);
            }
        }
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.brandsPage.selectTableSort(2);
    }

    @Test(priority = 4, dependsOnMethods = {"goToBrandsTab"})
    public void selectBrandAndClickToggleButtons() {
        boolean button = false;
        int brandPosition = 2;
        for (int i = 1; i <= 2; i++) {
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.brandsPage.clickBrandCheckbox(brandPosition);
            apisSystem.brandsPage.clickToggleButton(button);
            apisSystem.greenMessage.waitMessageSuccessPresent();
            Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
            button = true;
            brandPosition++;
        }
    }

    @Test(priority = 6, dependsOnMethods = {"goToBrandsTab"})
    public void popupCreateBrandInputFieldsAndClickCancel() {
        clickCreateBrand();
        apisSystem.createBrand.inputAppKey(TEST_DATA[0]);
        apisSystem.createBrand.inputApiUser(TEST_DATA[1]);
        apisSystem.createBrand.inputApiPassword(TEST_DATA[2]);
        apisSystem.createBrand.inputBrandName(TEST_DATA[3]);
        apisSystem.createBrand.inputDomain(TEST_DATA[4]);
        apisSystem.createBrand.selectCheckboxEnable();
        Assert.assertEquals(apisSystem.createBrand.clickButtonSaveOrCancel(false), true);
    }

    @Test(priority = 7, dependsOnMethods = {"goToBrandsTab"})
    public void popupCreateBrandFailedDomain() {
        apisSystem.createBrand.waitInvisibilityPopup();
        clickCreateBrand();
        apisSystem.createBrand.inputAppKey(TEST_DATA[0]);
        apisSystem.createBrand.inputApiUser(TEST_DATA[1]);
        apisSystem.createBrand.inputApiPassword(TEST_DATA[2]);
        apisSystem.createBrand.inputBrandName(TEST_DATA[3]);
        apisSystem.createBrand.inputDomain(TEST_DATA[4]);
        apisSystem.createBrand.selectCheckboxEnable();
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.createBrand.isErrorMessagePresent());
    }

    @Test(priority = 8, dependsOnMethods = {"goToBrandsTab"})
    public void popupCreateBrandCorrectDomain() {
        apisSystem.createBrand.inputDomain(TEST_DATA[4] + ".com");
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        apisSystem.createBrand.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 9, dependsOnMethods = {"goToBrandsTab"})
    public void createSecondBrand() {
        apisSystem.createBrand.waitInvisibilityPopup();
        clickCreateBrand();
        apisSystem.createBrand.inputAppKey(TEST_DATA[0] + 2);
        apisSystem.createBrand.inputApiUser(TEST_DATA[1] + 2);
        apisSystem.createBrand.inputApiPassword(TEST_DATA[2] + 2);
        apisSystem.createBrand.inputBrandName(TEST_DATA[3] + 2);
        apisSystem.createBrand.inputDomain(TEST_DATA[4] + "2.com");
        apisSystem.createBrand.selectCheckboxEnable();
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 10, dependsOnMethods = {"goToBrandsTab"})
    public void deleteFirstTestBrandClickCancel() {
        apisSystem.brandsPage.filterInputBrandName(TEST_DATA[3]);
        apisSystem.brandsPage.waitInvisibilityOverlay();
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(6);
        apisSystem.deleteBrand.waitPopupLoaded();
        Assert.assertTrue(apisSystem.deleteBrand.isPopupDeleteBrandPresent());
        apisSystem.deleteBrand.clickButtonCancelOrYes(false);
    }

    @Test(priority = 11, dependsOnMethods = {"goToBrandsTab"})
    public void deleteFirstTestBrandClickYes() {
        apisSystem.deleteBrand.waitInvisibilityPopup();
        deleteBrand();
    }

    @Test(priority = 12, dependsOnMethods = {"goToBrandsTab"})
    public void editSecondTestBrand() {
        apisSystem.brandsPage.clickActionButton(1);
        apisSystem.brandsPage.clickItemFromDropDownMenu(1);
        apisSystem.editBrand.inputApiUser(TEST_DATA[1] + "edit");
        apisSystem.editBrand.inputBrandName(TEST_DATA[3] + "edit");
        apisSystem.editBrand.clickButtonSaveOrCancel(true);
        apisSystem.editBrand.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
        apisSystem.editBrand.waitInvisibilityPopup();
    }

    @Test(priority = 13, dependsOnMethods = {"goToBrandsTab"})
    public void deleteSecondTestBrandClickYes() {
        deleteBrand();
    }

    @Test(priority = 14, dependsOnMethods = {"goToBrandsTab"})
    public void clickDisableFromCogwheel() {
        apisSystem.filterEntity.clickSearchOrResetButton(false);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.brandsPage.clickActionButton(2);
        apisSystem.brandsPage.clickItemFromDropDownMenu(3);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 15, dependsOnMethods = {"goToBrandsTab"})
    public void clickEnableFromCogwheel() {
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.brandsPage.clickActionButton(2);
        apisSystem.brandsPage.clickItemFromDropDownMenu(4);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 16, dependsOnMethods = {"goToBrandsTab"})
    public void filterBrandNameCorrect() {
        String brandName = null;
        try {
            brandName = apisSystem.brandsPage.getValueFromFirstBrandName("filterFirstBrandName");
        } catch (StaleElementReferenceException e) {
            log.error("stale element reference: element is not attached to the page document");
        }
        apisSystem.brandsPage.filterInputBrandName(brandName);
        apisSystem.brandsPage.filterClickAndSelectEnabled(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(brandName, apisSystem.brandsPage.getInputValue("filterBrandNameInput"));
    }

    @Test(priority = 17, dependsOnMethods = {"goToBrandsTab"})
    public void filterBrandNameFail() {
        apisSystem.brandsPage.filterInputBrandName("failBrandName");
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.brandsPage.getValueFromFirstBrandName("noMatchingRecords"),
                "No matching records found.");
        apisSystem.filterEntity.clickSearchOrResetButton(false);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
    }

    @Test(priority = 18, dependsOnMethods = {"goToBrandsTab"})
    public void filterEnabledModeEnable() {
        apisSystem.brandsPage.filterClickAndSelectEnabled(true);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        if (apisSystem.brandsPage.getFirstPosition() == 1) {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("noMatchingRecords"),
                    "No matching records found.");
        } else {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("firstPositionBrandEnabled"),
                    "Enabled");
        }
    }

    @Test(priority = 19, dependsOnMethods = {"goToBrandsTab"})
    public void filterEnabledModeDisable() {
        apisSystem.filterEntity.clickSearchOrResetButton(false);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.brandsPage.filterClickAndSelectEnabled(false);
        apisSystem.brandsPage.filterClickAndSelectEnabled(false);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        if (apisSystem.brandsPage.getFirstPosition() == 1) {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("noMatchingRecords"),
                    "No matching records found.");
        } else {
            Assert.assertEquals(apisSystem.brandsPage.
                            getValueFromFirstBrandName("firstPositionBrandEnabled"),
                    "Disabled");
        }
    }

    @Test(priority = 20, dependsOnMethods = {"goToBrandsTab"})
    public void filterAllFilters() {
        String enabledStatus;
        apisSystem.filterEntity.clickSearchOrResetButton(false);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        String brandName = apisSystem.brandsPage.getValueFromFirstBrandName("firstPositionBrandName");
        apisSystem.brandsPage.filterInputBrandName(brandName);
        String enableStatusStr = apisSystem.brandsPage.getValueFromFirstBrandName("firstPositionBrandEnabled");
        if (enableStatusStr.equals("Enabled")) {
            apisSystem.brandsPage.filterClickAndSelectEnabled(true);
            enabledStatus = "Enabled";
        } else {
            apisSystem.brandsPage.filterClickAndSelectEnabled(false);
            enabledStatus = "Disabled";
        }
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(apisSystem.brandsPage.getInputValue("filterBrandNameInput"), brandName);
        Assert.assertEquals(apisSystem.brandsPage.getValueFromFirstBrandName("firstPositionBrandEnabled"), enabledStatus);
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
        apisSystem.deleteBrand.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
        apisSystem.deleteBrand.waitInvisibilityPopup();
    }

}
