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
    private static final String USER_EMAIL_TEST = PropertyLoader.loadProperty("userEmail.test");
    private static final int actionButtonPosition = 1;
    private static final int ITEM_EDIT_USER = 4;
    private static final int ITEM_EDIT_DESKS = 6;
    private static final String[] DATA_TEST = {"First-Name-Test", "Last-Name-Test"};

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
    public void goToUsersTab() {
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    @Test(priority = 3, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void sortTabs() {
        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j <= 1; j++) {
                if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.usersPage.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"goToUsersTab"})
    public void selectUserCheckboxAndClickToggleButtons() {
        boolean button = true;
        int userPosition = 1;
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.inputUserName(USER_NAME_TEST);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        for (int i = 1; i <= 2; i++) {
            if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
                apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
            }
            apisSystem.usersPage.clickUserCheckbox(userPosition);
            apisSystem.usersPage.clickToggleButton(button);
            apisSystem.usersPage.waitMessageSuccessPresent();
            Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
            button = false;
        }
    }

    @Test(priority = 5, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void clickedOnDisableUser() {
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(1);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 6, dependsOnMethods = {"goToUsersTab"})
    public void clickedOnEnableUser() {
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(2);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 7, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void clickedOnEditUser() {
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit user' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_USER);
        // wait pop up 'Edit user' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
    }

    @Test(priority = 8, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void editUserChangeGroupWithEmptyFields() {
        apisSystem.editUser.inputFistName(false, null);
        apisSystem.editUser.inputLastName(false, null);
        apisSystem.editUser.clickAndSelectGroup();
        // if running on firefox browser, need uncomment string
//        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 9, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void editUserChangeRoleWithEmptyFields() {
        apisSystem.editUser.clickAndSelectRole();
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 10, dependsOnMethods = {"goToUsersTab"})
    public void editUserSelectBrandAndDelete() {
        int brandPosition = 1;
        selectBrand(brandPosition);
        apisSystem.editUser.deleteSelectBrand(brandPosition);
    }

    @Test(priority = 11, dependsOnMethods = {"goToUsersTab"})
    public void editUserSelectBrand() {
        int brandPosition = 1;
        selectBrand(brandPosition);
    }

    @Test(priority = 12, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void editUserInputFirstName() {
        apisSystem.editUser.inputFistName(true, DATA_TEST[0]);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 13, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void editUserInputLastName() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.editUser.inputLastName(true, DATA_TEST[1]);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 14, dependsOnMethods = {"goToUsersTab"})
    public void openEditDesksPopup() {
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit desks' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_DESKS);
        // wait pop up 'Edit desks' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
    }

    @Test(priority = 15, dependsOnMethods = {"goToUsersTab"})
    public void selectBrandAndDeskInPopup() {
        apisSystem.editDesks.clickOnBrandField();
        String brand = apisSystem.editDesks.selectBrandFromDropDownList(1);
        apisSystem.editDesks.waitForDeskToBeActive();
        apisSystem.editDesks.clickOnDeskField();
        String desk = apisSystem.editDesks.selectDeskFromDropDownList(1);
        apisSystem.editDesks.clickAddButton();
        Assert.assertEquals(brand, apisSystem.editDesks.getAddedBrandText("brand"));
        Assert.assertEquals(desk, apisSystem.editDesks.getAddedBrandText("desk"));
    }

    @Test(priority = 16, dependsOnMethods = {"goToUsersTab"})
    public void clickButtonCancel() {
        apisSystem.editDesks.clickButtonSaveOrCancel(false);
    }

    @Test(priority = 17, dependsOnMethods = {"goToUsersTab"})
    public void openEditDesksPopup2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openEditDesksPopup();
    }

    @Test(priority = 18, dependsOnMethods = {"goToUsersTab"})
    public void selectBrandAndDeskInPopup2() {
        selectBrandAndDeskInPopup();
    }

    @Test(priority = 19, dependsOnMethods = {"goToUsersTab"})
    public void clickButtonSave() {
        apisSystem.editDesks.clickButtonSaveOrCancel(true);
    }

    @Test(priority = 20, enabled = true, dependsOnMethods = {"goToUsersTab"})
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
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 21, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputUsername() {
        apisSystem.editUser.waitInvisibilityPopup();
        String username = apisSystem.usersPage.getValue("firstUserUsername");
        apisSystem.usersPage.inputUserName(username);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(username, apisSystem.usersPage.getInputValue("filterUserNameField"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

    @Test(priority = 22, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputFirstName() {
        apisSystem.editUser.waitInvisibilityPopup();
        String firstName = apisSystem.usersPage.getValue("firstUserFirstName");
        apisSystem.usersPage.inputFirstName(firstName);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(firstName, apisSystem.usersPage.getInputValue("filterFirstNameField"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 23, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputLastName() {
        apisSystem.editUser.waitInvisibilityPopup();
        String lastName = apisSystem.usersPage.getValue("firstUserLastName");
        apisSystem.usersPage.inputLastName(lastName);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(lastName, apisSystem.usersPage.getInputValue("filterLastNameField"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 24, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputEmail() {
        apisSystem.editUser.waitInvisibilityPopup();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String email = apisSystem.usersPage.getValue("firstUserEmail");
        apisSystem.usersPage.inputEmail(email);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(email, apisSystem.usersPage.getInputValue("filterEmailField"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 25, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterSelectGroup() {
        apisSystem.editUser.waitInvisibilityPopup();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.selectTableSort(7);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        String group = apisSystem.usersPage.getValue("firstUserGroup");
        apisSystem.usersPage.inputGroupAndClickEnter(group);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickOnGroupFiled();
        Assert.assertEquals(group, apisSystem.usersPage.getGroupValue("filterGroupValue"));
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 26, dependsOnMethods = {"goToUsersTab"})
    public void selectBrand() {
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.filterClickOnBrandsField();
        String brand = apisSystem.usersPage.filterClickAndGetBrand(1);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        if (apisSystem.listEntity.getListResult() == 1) {
            Assert.assertEquals(apisSystem.listEntity.getTextAboutNoResult(), "No matching records found.");
        } else {
            Assert.assertEquals(apisSystem.usersPage.getBrandName(), brand);
        }
        apisSystem.filterEntity.clickSearchOrResetButton(false);
    }

    @Test(priority = 27, dependsOnMethods = {"goToUsersTab"})
    public void openEditDesksAbdDeleteDesk() {
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.inputUserName(USER_NAME_TEST);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit desks' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(ITEM_EDIT_DESKS);
        // wait pop up 'Edit desks' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
        apisSystem.editDesks.clickButtonRemove();
        apisSystem.editDesks.clickButtonSaveOrCancel(true);
    }

    @Test(priority = 28, enabled = true, dependsOnMethods = {"goToUsersTab"})
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

    @Test(priority = 29, enabled = true, dependsOnMethods = {"openPopupEditUserForTestUser"})
    public void deleteBrandAndSave() {
        apisSystem.editUser.deleteSelectBrand(1);
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
