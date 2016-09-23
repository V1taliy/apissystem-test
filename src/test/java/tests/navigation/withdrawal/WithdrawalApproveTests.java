package tests.navigation.withdrawal;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import tests.login.LoginTests;
import tests.login.TestUserLogin;
import utils.PropertyLoader;

public class WithdrawalApproveTests extends Fixture {

    private static final Logger log = Logger.getLogger(WithdrawalApproveTests.class);
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String BRANDS_URL = PropertyLoader.loadProperty("brands.url");
    private static final String USERS_URL = PropertyLoader.loadProperty("users.url");
    private static final String GROUPS_URL = PropertyLoader.loadProperty("groups.url");
    private static final String WITHDRAWAL_URL = PropertyLoader.loadProperty("withdrawal.url");
    private static final String TEST_USER_7 = PropertyLoader.loadProperty("testUser7.name");
    private static final String TEST_USER_8 = PropertyLoader.loadProperty("testUser8.name");
    private static final String TEST_CUSTOMER_ID = PropertyLoader.loadProperty("test.customerID");
    private static final String TEST_COMMENT_1 = "TEST_COMMENT_FOR_TEST_USER_7";
    private static final String TEST_COMMENT_2 = "TEST_COMMENT_FOR_TEST_USER_8";

    private static int userIndex1 = 0;
    private static int userIndex2 = 0;
    private static final boolean TEST_STATUS = true;
    private int user_ID = 0;
    private int user_ID_position = 0;

    @Test(priority = 1)
    public void testUserLogin() {
        TestUserLogin.openWebSiteAndLoginTestsUsers();
    }

    @Test(priority = 2)
    public void loginAsAdmin() {
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 3)
    public void switchToBrandsPage() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        Assert.assertEquals(apisSystem.brandsPage.getCurrentPageURL(), BRANDS_URL);
    }

    @Test(priority = 4)
    public void enableBrand() {
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
    public void switchToUsersPage1() {
        switchToUsersPage();
    }

    @Test(priority = 6, enabled = TEST_STATUS)
    public void testUser7EditUserSelectGroupAndBrand() {
        try {
            Thread.sleep(1000);
            apisSystem.usersPage.scrollDown();
            userIndex1 = apisSystem.listEntity.getUserNameIndex(TEST_USER_7);
            log.info(String.format("user index = %s", userIndex1));
            apisSystem.usersPage.clickActionButton(userIndex1);
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

    @Test(priority = 7, enabled = TEST_STATUS)
    public void testUser7EditDesksAddDesk() {
        apisSystem.greenMessage.waitMessageInvisibility();
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

    @Test(priority = 8, enabled = TEST_STATUS)
    public void testUser8EditUserSelectGroupAndBrand() {
        apisSystem.greenMessage.waitMessageInvisibility();
        userIndex2 = apisSystem.listEntity.getUserNameIndex(TEST_USER_8);
        log.info(String.format("user index = %s", userIndex2));
        apisSystem.usersPage.clickActionButton(userIndex2);
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

    @Test(priority = 9, enabled = TEST_STATUS)
    public void switchToGroups() {
        apisSystem.mainPage.clickOnNavigationItem(4);
        Assert.assertEquals(apisSystem.groupsPage.getCurrentPageURL(), GROUPS_URL);
    }

    @Test(priority = 10, enabled = TEST_STATUS)
    public void selectRetentionTL() {
        apisSystem.groupsPage.selectGroup("Retention TL");
        apisSystem.withdrawalEntity.selectDeselectCheckBoxViewAll(false);
        apisSystem.pagesEntity.selectCheckBoxWithdrawal();
        apisSystem.groupsPage.clickButtonSave();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 11, enabled = TEST_STATUS)
    public void selectFinance() {
        apisSystem.groupsPage.selectGroup("Finance");
        try {
            Thread.sleep(300);
            apisSystem.withdrawalEntity.selectDeselectCheckBoxViewAll(false);
            apisSystem.pagesEntity.selectCheckBoxWithdrawal();
            apisSystem.groupsPage.clickButtonSave();
            Thread.sleep(500);
            Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 12, enabled = TEST_STATUS)
    public void switchToWithdrawal() {
        apisSystem.mainPage.clickOnNavigationItem(1);
        Assert.assertEquals(apisSystem.withdrawalPage.getCurrentPageURL(), WITHDRAWAL_URL);
    }

    @Test(priority = 13, enabled = TEST_STATUS)
    public void selectCustomerIDandBrand() {
        try {
            Thread.sleep(5000);
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.withdrawalPage.inputCustomerID(TEST_CUSTOMER_ID);
        apisSystem.withdrawalPage.deleteAllBrands();
        apisSystem.withdrawalPage.inputBrand("toroption");
        apisSystem.withdrawalPage.selectStatus(0);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        user_ID = apisSystem.withdrawalPage.getUserID(0);
        Assert.assertEquals(apisSystem.withdrawalPage.getCustomerID_fromFirstRow(), TEST_CUSTOMER_ID);
    }

    private static void switchToUsersPage() {
        // TODO  need uncomment
//        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
//            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
//        }
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

}
