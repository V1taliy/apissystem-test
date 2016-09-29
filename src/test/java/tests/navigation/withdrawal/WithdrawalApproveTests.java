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
    private static final String TEST_CONFIRM_CODE = "10";

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
        switchToWithdrawalPage();
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

    @Test(priority = 14)
    public void clickOnAssignButton() {
        apisSystem.withdrawalPage.clickActionButton("Assign", 0);
        apisSystem.assignPopup.waitPopupLoaded();
        Assert.assertTrue(apisSystem.assignPopup.isPopupPresent());
    }

    @Test(priority = 15, enabled = TEST_STATUS)
    public void changeDataOnAssignPopup() {
        apisSystem.assignPopup.clickOnGroupFiled();
        apisSystem.assignPopup.selectGroup(5);
        apisSystem.assignPopup.clickOnUserField();
        apisSystem.assignPopup.selectUser(TEST_USER_7);
        apisSystem.assignPopup.clickOnAddComment();
        apisSystem.assignPopup.inputComment(TEST_COMMENT_1);
        apisSystem.assignPopup.clickButtonSaveOrCancel(true);
        apisSystem.greenMessage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 16, enabled = TEST_STATUS)
    public void clickOnViewAndCheckComment() {
        apisSystem.greenMessage.waitInvisibilityOverlay();
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
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

    @Test(priority = 17, enabled = TEST_STATUS)
    public void logoutFromAdmin() {
        logoutFromUser();
        wait90seconds();
    }

    @Test(priority = 18)
    public void loginTestUser7() {
        TestUserLogin.testUserLogin(TEST_USER_7);
    }

    @Test(priority = 19)
    public void switchToWithdrawalPageForTestUser7() {
        switchToWithdrawalPage();
    }

    @Test(priority = 20)
    public void clickOnApproveForTestUser7() {
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.withdrawalPage.clickActionButton("Approve", 0);
        apisSystem.approvePopup.waitPopupLoaded();
        Assert.assertTrue(apisSystem.approvePopup.isPopupPresent());
    }

    @Test(priority = 21)
    public void changeDataOnApprovePopupTestUser7() {
        apisSystem.approvePopup.clickOnGroupFiled();
        apisSystem.approvePopup.selectUser("Finance");
        apisSystem.approvePopup.clickOnUserField();
        apisSystem.approvePopup.selectUser(TEST_USER_8);
        apisSystem.approvePopup.selectBonusRadioButton(0);
        apisSystem.approvePopup.clickOnAddComment();
        apisSystem.approvePopup.inputComment(TEST_COMMENT_2);
        apisSystem.approvePopup.clickButtonSaveOrCancel(true);
        apisSystem.approvePopup.waitInvisibilityPopup();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 22)
    public void logoutTestUser7() {
        logoutFromUser();
        wait90seconds();
    }

    @Test(priority = 23)
    public void loginAsTestUser8() {
        TestUserLogin.testUserLogin(TEST_USER_8);
    }

    @Test(priority = 24)
    public void switchToWithdrawalPageForTestUser8() {
        switchToWithdrawalPage();
    }

    @Test(priority = 25)
    public void clickOnApproveButtonForTestUser8() {
        try {
            Thread.sleep(5000);
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.withdrawalPage.clickActionButton("Approve", 0);
        apisSystem.approvePopup.waitPopupLoaded();
        Assert.assertTrue(apisSystem.approvePopup.isPopupPresent());
    }

    @Test(priority = 26)
    public void changeDataOnApprovePopupForTestUser8() {
        apisSystem.approvePopup.selectBonusRadioButton(0);
        apisSystem.approvePopup.inputConfirmCode(TEST_CONFIRM_CODE);
        apisSystem.approvePopup.clickOnMethod();
        apisSystem.approvePopup.selectMethod("CreditCard");
        apisSystem.approvePopup.clickOnAddComment();
        apisSystem.approvePopup.inputComment(TEST_COMMENT_2 + "_2");
        apisSystem.approvePopup.clickButtonSaveOrCancel(true);
        apisSystem.approvePopup.waitInvisibilityPopup();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    @Test(priority = 27)
    public void logoutTestUser8() {
        logoutFromUser();
        wait90seconds();
    }

    @Test(priority = 28)
    public void loginAsAdmin2() {
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 29)
    public void switchToWithdrawal2() {
        switchToWithdrawalPage();
    }

    @Test(priority = 30, enabled = TEST_STATUS)
    public void searchUserAfterCanceled() {
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
        apisSystem.withdrawalPage.selectStatus(1);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        for (int i = 0; i < 2; i++) {
            apisSystem.withdrawalPage.sortTab(3);
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
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

    @Test(priority = 31, enabled = TEST_STATUS)
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
        Assert.assertEquals(comment3, TEST_COMMENT_2 + "_2");
        apisSystem.withdrawalPage.clickViewButton(user_ID_position);
    }

    @Test(priority = 32)
    public void switchToUsers2() {
        switchToUsersPage();
    }

    @Test(priority = 33)
    public void changeTestUsers() {
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        try {
            Thread.sleep(500);
            apisSystem.usersPage.scrollDown();
            userIndex1 = apisSystem.listEntity.getUserNameIndex(TEST_USER_7);
            log.info(String.format("user index = %s", userIndex1));
            apisSystem.usersPage.clickActionButton(userIndex1);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(6);
            apisSystem.editDesks.waitPopupLoaded();
            apisSystem.editDesks.clickButtonRemove();
            apisSystem.editDesks.clickButtonSaveOrCancel(true);
            apisSystem.editDesks.waitInvisibilityPopup();
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.greenMessage.waitMessageInvisibility();
            apisSystem.usersPage.clickActionButton(userIndex1);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
            apisSystem.editUser.waitPopupLoaded();
            apisSystem.editUser.clickAndSelectGroup("Select user group");
            apisSystem.editUser.deleteSelectBrand(1);
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            apisSystem.editUser.waitInvisibilityPopup();
//            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
//                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
//            }
            apisSystem.usersPage.scrollDown();
            userIndex2 = apisSystem.listEntity.getUserNameIndex(TEST_USER_8);
            log.info(String.format("user index = %s", userIndex2));
            apisSystem.usersPage.clickActionButton(userIndex2);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
            apisSystem.editUser.waitPopupLoaded();
            apisSystem.editUser.deleteSelectBrand(1);
            apisSystem.editUser.clickAndSelectGroup("Select user group");
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            apisSystem.editUser.waitInvisibilityPopup();
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.greenMessage.isMessageSuccessPresent());
    }

    private static void switchToUsersPage() {
//        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
//            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
//        }
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    private static void switchToWithdrawalPage() {
        apisSystem.mainPage.clickOnNavigationItem(1);
        Assert.assertEquals(apisSystem.withdrawalPage.getCurrentPageURL(), WITHDRAWAL_URL);
    }

    private static void logoutFromUser() {
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.greenMessage.waitInvisibilityOverlay();
        apisSystem.mainPage.clickLogoutButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Assert.assertEquals(apisSystem.mainPage.getCurrentPageURL(), LOGIN_URL);
    }

    private static void wait90seconds() {
        for (int i = 90; i > 0; i--) {
            try {
                log.info(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
