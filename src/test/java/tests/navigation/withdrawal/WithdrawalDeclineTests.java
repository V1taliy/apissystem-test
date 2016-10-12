package tests.navigation.withdrawal;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import tests.login.LoginTests;
import tests.login.TestUserLogin;
import utils.PropertyLoader;

public class WithdrawalDeclineTests extends Fixture {

    private static final Logger log = Logger.getLogger(WithdrawalDeclineTests.class);
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String BRANDS_URL = PropertyLoader.loadProperty("brands.url");
    private static final String USERS_URL = PropertyLoader.loadProperty("users.url");
    private static final String GROUPS_URL = PropertyLoader.loadProperty("groups.url");
    private static final String WITHDRAWAL_URL = PropertyLoader.loadProperty("withdrawal.url");
    private static final String TEST_USER_7 = PropertyLoader.loadProperty("testUser7.name");
    private static final String TEST_USER_8 = PropertyLoader.loadProperty("testUser8.name");
    private static final String TEST_CUSTOMER_ID = PropertyLoader.loadProperty("test.customerID");
    private static final String TEST_COMMENT_1 = "TEST_COMMENT: ASSIGN_ON_TEST_USER_7";
    private static final String TEST_COMMENT_2 = "TEST_COMMENT: DECLINE_AND_ASSIGN_ON_TEST_USER_8";
    private static final String TEST_COMMENT_3 = "TEST_COMMENT: TEST_USER_8_DECLINE";

    private static int userIndex1 = 0;
    private static int userIndex2 = 0;
    private static final boolean TEST_STATUS = true;
    private int user_ID = 0;
    private int user_ID_position = 0;

    @Test(priority = 1)
    public void testUsersLogin() {
        TestUserLogin.openWebSiteAndLoginTestsUsers();
    }

    @Test(priority = 2)
    public void adminLogin() {
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 3, enabled = TEST_STATUS)
    public void switchToBrands() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        Assert.assertEquals(apisSystem.brandsPage.getCurrentPageURL(), BRANDS_URL);
    }

    @Test(priority = 4, enabled = TEST_STATUS)
    public void enableBrands() {
        try {
            Thread.sleep(1000);
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.listEntity.clickCheckboxSelectAll();
            apisSystem.brandsPage.clickBrandCheckbox(0);
            apisSystem.brandsPage.clickToggleButton(true);
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 5, enabled = TEST_STATUS)
    public void switchToUsers() {
        switchToUsersPage();
    }

    @Test(priority = 6, enabled = TEST_STATUS)
    public void testUser7EditUserSelectGroupAndBrand() {
        try {
            Thread.sleep(1000);
            apisSystem.mainPage.scrollDown();
            userIndex1 = apisSystem.listEntity.getUserNameIndex(TEST_USER_7);
            log.info(String.format("user index = %s", userIndex1));
            apisSystem.usersPage.clickActionButton(userIndex1);
            // click edit user
            apisSystem.usersPage.clickItemActionFromDropDownMenu(2);
            apisSystem.editUser.waitPopupLoaded();
            // select Retention TL
            apisSystem.editUser.clickAndSelectGroup("Retention TL");
            apisSystem.editUser.clickOnBrandsField();
            // select toroption
            apisSystem.editUser.clickOnSelectBrand(2);
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            apisSystem.editUser.waitInvisibilityPopup();
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 7, enabled = TEST_STATUS)
    public void testUser7EditDesksAddDesk() {
        apisSystem.successMessage.waitMessageInvisibility();
        apisSystem.usersPage.clickActionButton(userIndex1);
        // click edit desks
        apisSystem.usersPage.clickItemActionFromDropDownMenu(3);
        apisSystem.editDesks.waitPopupLoaded();
        apisSystem.editDesks.clickOnBrandField();
        String brandName = apisSystem.editDesks.selectBrandFromDropDownList(1);
        apisSystem.editDesks.waitForDeskToBeActive();
        // select Default Desk
        String deskName = apisSystem.editDesks.selectDeskFromDropDownList(0);
        apisSystem.editDesks.clickAddButton();
        Assert.assertEquals(brandName, "toroption");
        Assert.assertEquals(deskName, "Default Desk");
        apisSystem.editDesks.clickButtonSaveOrCancel(true);
        apisSystem.editDesks.waitInvisibilityPopup();
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 8, enabled = TEST_STATUS)
    public void testUser8EditUserSelectGroupAndBrand() {
        apisSystem.successMessage.waitMessageInvisibility();
        userIndex2 = apisSystem.listEntity.getUserNameIndex(TEST_USER_8);
        log.info(String.format("user index = %s", userIndex2));
        apisSystem.usersPage.clickActionButton(userIndex2);
        // click edit user
        apisSystem.usersPage.clickItemActionFromDropDownMenu(2);
        apisSystem.editUser.waitPopupLoaded();
        apisSystem.editUser.clickAndSelectGroup("Finance");
        apisSystem.editUser.clickOnBrandsField();
        // select toroption
        apisSystem.editUser.clickOnSelectBrand(2);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
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
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
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
            Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
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
            Thread.sleep(3000);
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.withdrawalPage.inputCustomerID(TEST_CUSTOMER_ID);
        apisSystem.withdrawalPage.deleteAllBrands();
        apisSystem.withdrawalPage.inputBrand("toroption");
        apisSystem.withdrawalPage.selectStatus(0);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        user_ID = apisSystem.withdrawalPage.getUserID(0);
        Assert.assertEquals(apisSystem.withdrawalPage.getCustomerID_fromFirstRow(), TEST_CUSTOMER_ID);
    }

    @Test(priority = 14, enabled = TEST_STATUS)
    public void clickOnPlusIcon() {
        apisSystem.withdrawalPage.clickOnPlusOnFirstRow();
        apisSystem.withdrawalPage.waitForLoaders("customer");
        apisSystem.withdrawalPage.scrollToElement("withdrawalDepositsLink");
        apisSystem.withdrawalPage.clickDepositsLink();
        apisSystem.withdrawalPage.waitForLoaders("deposits");
        apisSystem.withdrawalPage.scrollToElement("withdrawalWithdrawalsLink");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.withdrawalPage.clickWithdrawalsLink();
        apisSystem.withdrawalPage.waitForLoaders("withdrawal");
        apisSystem.withdrawalPage.scrollToElementBack("withdrawalWithdrawalsLink");
        apisSystem.withdrawalPage.clickWithdrawalsLink();
        apisSystem.withdrawalPage.scrollToElementBack("withdrawalDepositsLink");
        apisSystem.withdrawalPage.clickDepositsLink();
        for (int i = 3; i > 0; i--) {
            apisSystem.withdrawalPage.clickOnItemsFromUser(i);
        }
        apisSystem.withdrawalPage.clickOnPlusOnFirstRow();
        Assert.assertTrue(apisSystem.withdrawalPage.isPlusItemActive());
    }

    @Test(priority = 15, enabled = TEST_STATUS)
    public void clickOnAssignButton() {
        apisSystem.withdrawalPage.clickButtonAssign();
        apisSystem.assignPopup.waitPopupLoaded();
        Assert.assertTrue(apisSystem.assignPopup.isPopupPresent());
    }

    @Test(priority = 16, enabled = TEST_STATUS)
    public void changeDataOnAssignPopup() {
        apisSystem.assignPopup.clickOnGroupFiled();
        apisSystem.assignPopup.selectGroup(5);
        apisSystem.assignPopup.clickOnUserField();
        apisSystem.assignPopup.selectUser(TEST_USER_7);
        apisSystem.assignPopup.clickOnAddComment();
        apisSystem.assignPopup.inputComment(TEST_COMMENT_1);
        apisSystem.assignPopup.clickButtonSaveOrCancel(true);
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 17, enabled = TEST_STATUS)
    public void clickOnViewAndCheckComment() {
        apisSystem.successMessage.waitInvisibilityOverlay();
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        // select view in 1-st position
        apisSystem.withdrawalPage.clickViewButton(0);
        // if need check view comment
        apisSystem.withdrawalPage.clickOnViewForComment(0);
        apisSystem.viewComment.isCommentDisplayed();
        Assert.assertEquals(apisSystem.viewComment.getCommentText(), TEST_COMMENT_1);
        apisSystem.withdrawalPage.closeCommentMessage();
        try {
            Thread.sleep(1000);
            apisSystem.withdrawalPage.clickViewButton(0);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.withdrawalPage.checkViewButton(1));
    }

    @Test(priority = 18, enabled = TEST_STATUS)
    public void logoutFromAdmin() {
        logoutFromUser();
        wait90seconds();
    }

    @Test(priority = 19, enabled = TEST_STATUS)
    public void loginTestUser7() {
        TestUserLogin.testUserLogin(TEST_USER_7);
    }

    @Test(priority = 20, enabled = TEST_STATUS)
    public void switchToWithdrawalPageForTestUser7() {
        switchToWithdrawalPage();
    }

    @Test(priority = 21, enabled = TEST_STATUS)
    public void clickOnDeclineTestUser7() {
        clickOnDecline();
    }

    @Test(priority = 22, enabled = TEST_STATUS)
    public void changeDataOnDeclinePopupForTestUser7() {
        apisSystem.declinePopup.clickOnUserField();
        apisSystem.declinePopup.selectUserFromUserField(TEST_USER_8);
        apisSystem.declinePopup.clickOnReasonField();
        apisSystem.declinePopup.selectReasonValueFromReasonField("noDocs");
        apisSystem.declinePopup.clickOnAddComment();
        apisSystem.declinePopup.inputComment(TEST_COMMENT_2);
        apisSystem.declinePopup.clickButtonSaveOrCancel(true);
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
    }

    @Test(priority = 23, enabled = TEST_STATUS)
    public void logoutFromTestUser7() {
        logoutFromUser();
        wait90seconds();
    }

    @Test(priority = 24, enabled = TEST_STATUS)
    public void loginTestUser8() {
        TestUserLogin.testUserLogin(TEST_USER_8);
    }

    @Test(priority = 25, enabled = TEST_STATUS)
    public void switchToWithdrawalPageForTestUser8() {
        switchToWithdrawalPage();
    }

    @Test(priority = 26, enabled = TEST_STATUS)
    public void clickOnDeclineTestUser8() {
        clickOnDecline();
    }

    @Test(priority = 27, enabled = TEST_STATUS)
    public void changeDataOnDeclinePopupForTestUser8() {
        apisSystem.declinePopup.clickOnReasonField();
        apisSystem.declinePopup.selectReasonValueFromReasonField("noDocs");
        apisSystem.declinePopup.clickOnAddComment();
        apisSystem.declinePopup.inputComment(TEST_COMMENT_3);
        apisSystem.declinePopup.clickButtonSaveOrCancel(true);
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
    }

    @Test(priority = 28, enabled = TEST_STATUS)
    public void logoutFromTestUser8() {
        logoutFromUser();
        wait90seconds();
    }

    @Test(priority = 29, enabled = TEST_STATUS)
    public void loginAsAdmin() {
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 30, enabled = TEST_STATUS)
    public void switchToWithdrawalAsAdmin() {
        switchToWithdrawalPage();
    }

    @Test(priority = 31, enabled = TEST_STATUS)
    public void searchUserAfterCanceled() {
        try {
            Thread.sleep(3000);
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.withdrawalPage.inputCustomerID(TEST_CUSTOMER_ID);
        apisSystem.withdrawalPage.deleteAllBrands();
        apisSystem.withdrawalPage.inputBrand("toroption");
        apisSystem.withdrawalPage.selectStatus(2);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        for (int i = 0; i < 2; i++) {
            apisSystem.withdrawalPage.sortTab(3);
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
        }
        user_ID_position = apisSystem.withdrawalPage.getUserIndex(user_ID);
        int userID = apisSystem.withdrawalPage.getUserID(user_ID_position);
        if (userID > 0) {
            Assert.assertEquals(userID, user_ID);
        } else {
            Assert.fail("not finding user ID");
        }
    }

    @Test(priority = 32, enabled = TEST_STATUS)
    public void clickViewAndComments() {
        apisSystem.withdrawalPage.clickViewButton(user_ID_position);
        String comment1 = null;
        String comment2 = null;
        String comment3 = null;
        for (int i = 0; i < apisSystem.withdrawalPage.getCommentViewSize(); i++) {
            apisSystem.withdrawalPage.clickOnViewForComment(i);
            apisSystem.viewComment.isCommentDisplayed();
            switch (i) {
                case 0:
                    comment1 = apisSystem.viewComment.getCommentText();
                    break;
                case 1:
                    comment2 = apisSystem.viewComment.getCommentText();
                    break;
                case 2:
                    comment3 = apisSystem.viewComment.getCommentText();
            }
            apisSystem.withdrawalPage.closeCommentMessage();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(comment1, TEST_COMMENT_1);
        Assert.assertEquals(comment2, TEST_COMMENT_2);
        Assert.assertEquals(comment3, TEST_COMMENT_3);
        apisSystem.withdrawalPage.clickViewButton(user_ID_position);
    }

    @Test(priority = 33)
    public void switchToUsers2() {
        switchToUsersPage();
    }

    @Test(priority = 34)
    public void changeTestUsers() {
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        try {
            Thread.sleep(500);
            apisSystem.mainPage.scrollDown();
            userIndex1 = apisSystem.listEntity.getUserNameIndex(TEST_USER_7);
            log.info(String.format("user index = %s", userIndex1));
            apisSystem.usersPage.clickActionButton(userIndex1);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(3);
            apisSystem.editDesks.waitPopupLoaded();
            apisSystem.editDesks.clickButtonRemove();
            apisSystem.editDesks.clickButtonSaveOrCancel(true);
            apisSystem.editDesks.waitInvisibilityPopup();
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.successMessage.waitMessageInvisibility();
            apisSystem.usersPage.clickActionButton(userIndex1);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(2);
            apisSystem.editUser.waitPopupLoaded();
            apisSystem.editUser.clickAndSelectGroup("Select user group");
            apisSystem.editUser.deleteSelectBrand(0);
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            apisSystem.editUser.waitInvisibilityPopup();
//            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
//                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
//            }
            apisSystem.mainPage.scrollDown();
            userIndex2 = apisSystem.listEntity.getUserNameIndex(TEST_USER_8);
            log.info(String.format("user index = %s", userIndex2));
            apisSystem.usersPage.clickActionButton(userIndex2);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(2);
            apisSystem.editUser.waitPopupLoaded();
            apisSystem.editUser.deleteSelectBrand(0);
            apisSystem.editUser.clickAndSelectGroup("Select user group");
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            apisSystem.editUser.waitInvisibilityPopup();
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    private static void switchToWithdrawalPage() {
        apisSystem.mainPage.clickOnNavigationItem(1);
        Assert.assertEquals(apisSystem.withdrawalPage.getCurrentPageURL(), WITHDRAWAL_URL);
    }

    private static void logoutFromUser() {
        apisSystem.successMessage.waitInvisibilityOverlay();
        apisSystem.mainPage.clickLogoutButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(apisSystem.mainPage.getCurrentPageURL(), LOGIN_URL);
    }

    private static void clickOnDecline() {
        try {
            Thread.sleep(3000);
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.withdrawalPage.clickActionButton("Decline", 0);
        apisSystem.declinePopup.waitPopupLoaded();
        Assert.assertTrue(apisSystem.declinePopup.isPopupPresent());
    }

    private static void switchToUsersPage() {
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    private static void wait90seconds() {
        for (int i = 90; i > 0; i--) {
            try {
                log.info(i + " sec.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
