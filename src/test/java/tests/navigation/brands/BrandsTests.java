package tests.navigation.brands;

import org.apache.log4j.Logger;
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
    private static final String[] TEST_DATA_2 = {"testAppKey2", "testApiUser2",
            "testApiPassword2", "testBrandName2", "testDomain2"};
    private static final String[] FIELDS_ARRAY = {"AppKey", "ApiUser", "ApiPassword",
            "BrandName", "Domain"};

    private static int firstBrandIndex = 0;
    private static int secondBrandIndex = 0;

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
                if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.brandsPage.selectTableSort(i);
            }
        }
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.brandsPage.selectTableSort(2);
    }

    @Test(priority = 4, dependsOnMethods = {"goToBrandsTab"})
    public void selectBrandAndClickToggleButtons() {
        boolean button = false;
        int brandPosition = 2;
        for (int i = 1; i <= 2; i++) {
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.brandsPage.clickBrandCheckbox(brandPosition);
            apisSystem.brandsPage.clickToggleButton(button);
            apisSystem.greenMessage.waitMessageSuccessPresent();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
            button = true;
            brandPosition++;
        }
    }

    @Test(priority = 6, dependsOnMethods = {"goToBrandsTab"})
    public void popupCreateBrandInputFieldsAndClickCancel() {
        clickCreateBrand();
        apisSystem.createBrand.inputField(FIELDS_ARRAY[0], TEST_DATA[0]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[1], TEST_DATA[1]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[2], TEST_DATA[2]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[3], TEST_DATA[3]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[4], TEST_DATA[4]);
        apisSystem.createBrand.selectCheckboxEnable();
        Assert.assertEquals(apisSystem.createBrand.clickButtonSaveOrCancel(false), true);
    }

    @Test(priority = 7, dependsOnMethods = {"goToBrandsTab"})
    public void popupCreateBrandFailedDomain() {
        apisSystem.createBrand.waitInvisibilityPopup();
        clickCreateBrand();
        apisSystem.createBrand.inputField(FIELDS_ARRAY[0], TEST_DATA[0]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[1], TEST_DATA[1]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[2], TEST_DATA[2]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[3], TEST_DATA[3]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[4], TEST_DATA[4]);
        apisSystem.createBrand.selectCheckboxEnable();
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.createBrand.isErrorMessagePresent());
    }

    @Test(priority = 8, dependsOnMethods = {"goToBrandsTab"})
    public void popupCreateBrandCorrectDomain() {
        apisSystem.createBrand.inputField(FIELDS_ARRAY[4], TEST_DATA[4] + ".com");
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        apisSystem.createBrand.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 9, dependsOnMethods = {"goToBrandsTab"})
    public void createSecondBrand() {
        apisSystem.createBrand.waitInvisibilityPopup();
        clickCreateBrand();
        apisSystem.createBrand.inputField(FIELDS_ARRAY[0], TEST_DATA_2[0]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[1], TEST_DATA_2[1]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[2], TEST_DATA_2[2]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[3], TEST_DATA_2[3]);
        apisSystem.createBrand.inputField(FIELDS_ARRAY[4], TEST_DATA_2[4] + ".com");
        apisSystem.createBrand.selectCheckboxEnable();
        apisSystem.createBrand.clickButtonSaveOrCancel(true);
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 10, dependsOnMethods = {"goToBrandsTab"}, enabled = true)
    public void deleteFirstTestBrandClickCancel() {
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        firstBrandIndex = apisSystem.brandsPage.getBrandIndex(TEST_DATA[3]);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.greenMessage.waitInvisibilityOverlay();
        apisSystem.brandsPage.clickActionButton(firstBrandIndex);
        apisSystem.brandsPage.clickItemFromDropDownMenu(6);
        apisSystem.deleteBrand.waitPopupLoaded();
        Assert.assertTrue(apisSystem.deleteBrand.isPopupDeleteBrandPresent());
        apisSystem.deleteBrand.clickButtonCancelOrYes(false);
    }

    @Test(priority = 11, dependsOnMethods = {"goToBrandsTab"}, enabled = true)
    public void deleteFirstTestBrandClickYes() {
        apisSystem.deleteBrand.waitInvisibilityPopup();
        deleteBrand(firstBrandIndex);
    }

    @Test(priority = 12, dependsOnMethods = {"goToBrandsTab"}, enabled = true)
    public void editSecondTestBrand() {
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        secondBrandIndex = apisSystem.brandsPage.getBrandIndex(TEST_DATA_2[3]);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.brandsPage.clickActionButton(secondBrandIndex);
        apisSystem.brandsPage.clickItemFromDropDownMenu(1);
        apisSystem.editBrand.inputField(FIELDS_ARRAY[1], TEST_DATA_2[1] + "edit");
        apisSystem.editBrand.inputField(FIELDS_ARRAY[3], TEST_DATA_2[3] + "edit");
        apisSystem.editBrand.clickButtonSaveOrCancel(true);
        apisSystem.editBrand.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
        apisSystem.editBrand.waitInvisibilityPopup();
    }

    @Test(priority = 13, dependsOnMethods = {"goToBrandsTab"}, enabled = true)
    public void deleteSecondTestBrandClickYes() {
        deleteBrand(secondBrandIndex);
    }

    @Test(priority = 14, dependsOnMethods = {"goToBrandsTab"}, enabled = true)
    public void clickDisableFromCogwheel() {
        apisSystem.brandsPage.clickActionButton(2);
        apisSystem.brandsPage.clickItemFromDropDownMenu(3);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 15, dependsOnMethods = {"goToBrandsTab"}, enabled = true)
    public void clickEnableFromCogwheel() {
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.brandsPage.clickActionButton(2);
        apisSystem.brandsPage.clickItemFromDropDownMenu(4);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    private void clickCreateBrand() {
        apisSystem.brandsPage.createOrDeleteBrand(true);
        apisSystem.createBrand.waitPopupLoaded();
        Assert.assertTrue(apisSystem.createBrand.isPopupPresent());
    }

    private void deleteBrand(int brandIndex) {
        apisSystem.brandsPage.clickActionButton(brandIndex);
        apisSystem.brandsPage.clickItemFromDropDownMenu(6);
        apisSystem.deleteBrand.waitPopupLoaded();
        apisSystem.deleteBrand.clickButtonCancelOrYes(true);
        apisSystem.deleteBrand.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
        apisSystem.deleteBrand.waitInvisibilityPopup();
    }

}
