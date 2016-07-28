package tests.navigation.brands;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

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

    private void clickCreateBrand() {
        apisSystem.brandsPage.createOrDeleteBrand(true);
        apisSystem.createBrand.waitPopupLoaded();
        Assert.assertTrue(apisSystem.createBrand.isPopupPresent());
    }

}
