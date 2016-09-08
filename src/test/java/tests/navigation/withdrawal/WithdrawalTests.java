package tests.navigation.withdrawal;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import tests.login.LoginTests;
import tests.login.TestUserLogin;
import utils.PropertyLoader;

public class WithdrawalTests extends Fixture {

    private static final Logger log = Logger.getLogger(WithdrawalTests.class);
    private static final String BRANDS_URL = PropertyLoader.loadProperty("brands.url");
    private static final String USERS_URL = PropertyLoader.loadProperty("users.url");
    private static final String TEST_USER_7 = PropertyLoader.loadProperty("testUser7.name");
    private static final String TEST_USER_8 = PropertyLoader.loadProperty("testUser8.name");

    private static int userIndex1 = 0;
    private static int userIndex2 = 0;

    @Test(priority = 1, enabled = true)
    public void testUsersLogin() {
        TestUserLogin.openWebSiteAndLoginTestsUsers();
    }

    @Test(priority = 2)
    public void adminLogin() {
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 3, enabled = true)
    public void switchToBrands() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        Assert.assertEquals(apisSystem.brandsPage.getCurrentPageURL(), BRANDS_URL);
    }

    @Test(priority = 4, enabled = true)
    public void enableBrands() {
        try {
            Thread.sleep(1000);
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.brandsPage.selectTableSort(1);
            apisSystem.brandsPage.clickBrandCheckbox(1);
            apisSystem.brandsPage.clickToggleButton(true);
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 5)
    public void switchToUsers() {
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    @Test(priority = 6)
    public void testUser7EditUserSelectGroupAndBrand() {
        try {
            Thread.sleep(1000);
            apisSystem.usersPage.scrollDown();
            userIndex1 = apisSystem.listEntity.getUserNameIndex(TEST_USER_7);
            log.info(String.format("user index = %s", userIndex1));
            apisSystem.usersPage.clickActionButton(++userIndex1);
            // click edit user
            apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
            apisSystem.editUser.waitPopupLoaded();
            // select Retention TL
            apisSystem.editUser.clickAndSelectGroup("Retention TL");
            apisSystem.editUser.clickOnBrandsField();
            // select toroption
            apisSystem.editUser.clickOnSelectBrand(3);
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            apisSystem.editUser.waitInvisibilityPopup();
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 7)
    public void testUser7EditDesksAddDesk() {
        apisSystem.usersPage.clickActionButton(userIndex1);
        // click edit desks
        apisSystem.usersPage.clickItemActionFromDropDownMenu(6);
        apisSystem.editDesks.waitPopupLoaded();
        apisSystem.editDesks.clickOnBrandField();
        String brandName = apisSystem.editDesks.selectBrandFromDropDownList(1);
        apisSystem.editDesks.waitForDeskToBeActive();
        // select Default Desk
        String deskName = apisSystem.editDesks.selectDeskFromDropDownList(1);
        apisSystem.editDesks.clickAddButton();
        Assert.assertEquals(brandName, "toroption");
        Assert.assertEquals(deskName, "Default Desk");
        apisSystem.editDesks.clickButtonSaveOrCancel(true);
        apisSystem.editDesks.waitInvisibilityPopup();
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 8)
    public void testUser8EditUserSelectGroupAndBrand() {
        apisSystem.greenMessage.waitMessageInvisibility();
        userIndex2 = apisSystem.listEntity.getUserNameIndex(TEST_USER_8);
        log.info(String.format("user index = %s", userIndex2));
        apisSystem.usersPage.clickActionButton(++userIndex2);
        // click edit user
        apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
        apisSystem.editUser.waitPopupLoaded();
        apisSystem.editUser.clickAndSelectGroup("Finance");
        apisSystem.editUser.clickOnBrandsField();
        // select toroption
        apisSystem.editUser.clickOnSelectBrand(3);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());

    }

}
