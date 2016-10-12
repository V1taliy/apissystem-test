package tests.navigation.users;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class UsersTests extends Fixture {

    private static final Logger log = Logger.getLogger(UsersTests.class);
    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String USERS_URL = PropertyLoader.loadProperty("users.url");
    private static final String USER_NAME_TEST = PropertyLoader.loadProperty("userName.test");
    private static final int actionButtonPosition = 0;
    private static final int ITEM_EDIT_USER = 2;
    private static final int ITEM_EDIT_DESKS = 3;
    private static final String[] DATA_TEST = {"First-Name-Test", "Last-Name-Test"};

    private static final boolean TEST_STATUS = true;

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
    public void switchToUsersPage() {
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    @Test(priority = 3, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void sortTabsOnUsersPage() {
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j <= 1; j++) {
                if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.usersPage.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void selectUserCheckboxAndClickToggleButtons() {
        boolean button = true;
        int userPosition = 0;
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.filterInput("UserName", USER_NAME_TEST);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        for (int i = 1; i <= 2; i++) {
            if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.usersPage.clickUserCheckbox(userPosition);
            apisSystem.usersPage.clickToggleButton(button);
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
            apisSystem.successMessage.waitMessagePresent();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
            button = false;
        }
    }

    @Test(priority = 5, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void clickedOnDisableUser() {
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(0);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 6, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void clickedOnEnableUser() {
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(1);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.successMessage.waitMessagePresent();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 7, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void clickedOnEditUser() {
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit user' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_USER);
        // wait pop up 'Edit user' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
    }

    @Test(priority = 8, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void editUserChangeGroupWithEmptyFields() {
        apisSystem.editUser.inputFistName(false, null);
        apisSystem.editUser.inputLastName(false, null);
        apisSystem.editUser.clickAndSelectGroup("Manager");
        // if running on firefox browser, need uncomment string
//        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 9, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void editUserChangeRoleWithEmptyFields() {
        apisSystem.editUser.clickAndSelectRole();
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 10, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void editUserSelectBrandAndDelete() {
        int brandPosition = 0;
        selectBrand(brandPosition);
        apisSystem.editUser.deleteSelectBrand(brandPosition);
    }

    @Test(priority = 11, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void editUserSelectBrand() {
        int brandPosition = 0;
        selectBrand(brandPosition);
    }

    @Test(priority = 12, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void editUserInputFirstName() {
        apisSystem.editUser.inputFistName(true, DATA_TEST[0]);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 13, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void editUserInputLastName() {
        try {
            Thread.sleep(500);
            apisSystem.editUser.inputLastName(true, DATA_TEST[1]);
            apisSystem.editUser.clickButtonSaveOrCancel(true);
            Thread.sleep(300);
            apisSystem.successMessage.waitMessagePresent();
            Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 14, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void openEditDesksPopupFailed() {
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.successMessage.waitInvisibilityOverlay();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit desks' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_DESKS);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.errorMessage.isMessagePresent());
    }

    @Test(priority = 15, enabled = true, dependsOnMethods = {"switchToUsersPage"})
    public void openEditUserAndChangeGroup() {
        apisSystem.successMessage.waitInvisibilityOverlay();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit user' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_USER);
        // wait pop up 'Edit user' loaded
        apisSystem.editUser.waitPopupLoaded();
        apisSystem.editUser.clickAndSelectGroup("Retention");
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityPopup();
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 16, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void openEditDesksPopup() {
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.successMessage.waitInvisibilityOverlay();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit desks' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_DESKS);
        // wait pop up 'Edit desks' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
    }

    @Test(priority = 17, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void selectBrandAndDeskInPopup() {
        apisSystem.editDesks.clickOnBrandField();
        String brand = apisSystem.editDesks.selectBrandFromDropDownList(0);
        apisSystem.editDesks.waitForDeskToBeActive();
        apisSystem.editDesks.clickOnDeskField();
        String desk = apisSystem.editDesks.selectDeskFromDropDownList(1);
        apisSystem.editDesks.clickAddButton();
        Assert.assertEquals(brand, apisSystem.editDesks.getAddedBrandText("brand"));
        Assert.assertEquals(desk, apisSystem.editDesks.getAddedBrandText("desk"));
    }

    @Test(priority = 18, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void clickButtonCancel() {
        apisSystem.editDesks.clickButtonSaveOrCancel(false);
    }

    @Test(priority = 19, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void openEditDesksPopup2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openEditDesksPopup();
    }

    @Test(priority = 20, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void selectBrandAndDeskInPopup2() {
        selectBrandAndDeskInPopup();
    }

    @Test(priority = 21, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void clickButtonSave() {
        apisSystem.editDesks.clickButtonSaveOrCancel(true);
    }

    @Test(priority = 22, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void editUserChangeEnabled() {
        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_USER);
        apisSystem.editUser.waitPopupLoaded();
        if (apisSystem.editUser.isFirstNameEmpty().isEmpty() ||
                apisSystem.editUser.isLastNameEmpty().isEmpty()) {
            apisSystem.editUser.inputFistName(true, DATA_TEST[0]);
            apisSystem.editUser.inputLastName(true, DATA_TEST[1]);
        }
        apisSystem.editUser.clickOnEnabled();
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.successMessage.waitMessagePresent();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(apisSystem.successMessage.isMessagePresent());
    }

    @Test(priority = 23, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void filterInputUsername() {
        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        String username = apisSystem.usersPage.getValue("firstUserUsername");
        apisSystem.usersPage.filterInput("UserName", username);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(username, apisSystem.usersPage.getInputValue("filterUserNameField"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

    @Test(priority = 24, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void filterInputFirstName() {
//        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.usersPage.selectTableSort(2);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        String firstName = apisSystem.usersPage.getValue("firstUserFirstName");
        apisSystem.usersPage.filterInput("FirstName", firstName);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(firstName, apisSystem.usersPage.getInputValue("filterFirstNameField"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 25, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void filterInputLastName() {
//        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.usersPage.selectTableSort(3);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        String lastName = apisSystem.usersPage.getValue("firstUserLastName");
        apisSystem.usersPage.filterInput("LastName", lastName);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(lastName, apisSystem.usersPage.getInputValue("filterLastNameField"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 26, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void filterInputEmail() {
//        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String email = apisSystem.usersPage.getValue("firstUserEmail");
        apisSystem.usersPage.filterInput("Email", email);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(email, apisSystem.usersPage.getInputValue("filterEmailField"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 27, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void filterSelectGroup() {
//        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.selectTableSort(5);
        if (apisSystem.listEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        }
        String group = apisSystem.usersPage.getValue("firstUserGroup");
        apisSystem.usersPage.filterInput("Group", group);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(group, apisSystem.usersPage.getGroupValue("filterGroupValue"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 28, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void selectBrand() {
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.filterClickOnBrandsField();
        String brand = apisSystem.usersPage.filterClickAndGetBrand();
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        if (apisSystem.listEntity.getListResult() == 1) {
            Assert.assertEquals(apisSystem.listEntity.getTextAboutNoResult(), "No matching records found.");
        } else {
            Assert.assertEquals(apisSystem.usersPage.getBrandName(), brand);
        }
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 29, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void openEditDesksAbdDeleteDesk() {
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.filterInput("UserName", USER_NAME_TEST);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.listEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit desks' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_DESKS);
        // wait pop up 'Edit desks' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
        apisSystem.editDesks.clickButtonRemove();
        apisSystem.editDesks.clickButtonSaveOrCancel(true);
    }

    @Test(priority = 30, enabled = TEST_STATUS, dependsOnMethods = {"switchToUsersPage"})
    public void openPopupEditUserForTestUser() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit user' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_USER);
        // wait pop up 'Edit user' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
    }

    @Test(priority = 31, enabled = TEST_STATUS, dependsOnMethods = {"openPopupEditUserForTestUser"})
    public void deleteBrandAndSave() {
        apisSystem.editUser.deleteSelectBrand(0);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
    }

    private void selectBrand(int brandPositionFromDropDownList) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.editUser.clickOnBrandsField();
        String actualResult = apisSystem.editUser.clickOnSelectBrand(brandPositionFromDropDownList);
        Assert.assertEquals(actualResult, apisSystem.editUser.getAddBrandName(brandPositionFromDropDownList));
    }

}
