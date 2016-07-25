package tests.navigation.users;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import utils.PropertyLoader;

public class UsersTest extends Fixture {

    private static final Logger log = Logger.getLogger(UsersTest.class);
    private static final String ADMIN_NAME = PropertyLoader.loadProperty("admin.name");
    private static final String ADMIN_PASSWORD = PropertyLoader.loadProperty("admin.password");
    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String USERS_URL = PropertyLoader.loadProperty("users.url");


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
    public void goToUsersTab() {
        apisSystem.mainPage.clickOnNavigationItem(3);
        Assert.assertEquals(apisSystem.usersPage.getCurrentPageURL(), USERS_URL);
    }

    @Test(priority = 3, dependsOnMethods = {"goToUsersTab"})
    public void sortTabs() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 0; j <= 1; j++) {
                if (apisSystem.usersPage.isProcessingDisplayed()) {
                    apisSystem.usersPage.waitInvisibilityProcessing();
                }
                apisSystem.usersPage.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"sortTabs"})
    public void selectUserCheckboxAndClickToggleButtons() {
        boolean button = true;
        if (apisSystem.usersPage.isProcessingDisplayed()) {
            apisSystem.usersPage.waitInvisibilityProcessing();
        }
        for (int i = 1; i <= 2; i++) {
            apisSystem.usersPage.clickUserCheckbox(i);
            apisSystem.usersPage.clickToggleButton(button);
            apisSystem.usersPage.waitMessageSuccessPresent();
            Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
            button = false;
        }
    }

    @Test(priority = 5, dependsOnMethods = {"selectUserCheckboxAndClickToggleButtons"})
    public void clickedOnDisableAndEnableUser() {
        for (int i = 1; i <= 2; i++) {
            apisSystem.usersPage.clickActionButton(i);
            apisSystem.usersPage.clickItemActionFromDropDownMenu(i);
            apisSystem.usersPage.waitMessageSuccessPresent();
            Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
        }
    }

    @Test(priority = 6, dependsOnMethods = {"clickedOnDisableAndEnableUser"})
    public void clickedOnEditUser() {
        // select first user position
        apisSystem.usersPage.clickActionButton(1);
        // select 'Edit user' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
        // wait pop up 'Edit user' loaded
        apisSystem.editUser.waitPopupLoaded();
        Assert.assertTrue(apisSystem.editUser.isPopupPresent());
    }

    @Test(priority = 7, dependsOnMethods = {"clickedOnEditUser"})
    public void editUserChangeGroupWithEmptyFields() {
        apisSystem.editUser.inputFistName(false, null);
        apisSystem.editUser.inputLastName(false, null);
        apisSystem.editUser.clickAndSelectGroup();
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 8, dependsOnMethods = {"editUserChangeGroupWithEmptyFields"})
    public void editUserChangeRoleWithEmptyFields() {
        apisSystem.editUser.clickAndSelectRole();
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 9, dependsOnMethods = {"editUserChangeRoleWithEmptyFields"})
    public void editUserInputFirstName() {
        apisSystem.editUser.inputFistName(true, "firstNameTest");
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 10, dependsOnMethods = {"editUserInputFirstName"})
    public void editUserInputLastName() {
        apisSystem.editUser.inputLastName(true, "lastNameTest");
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 11, dependsOnMethods = {"editUserInputLastName"})
    public void editUserChangeEnabled() {
        // TODO long wait
        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.usersPage.clickActionButton(1);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
        apisSystem.editUser.waitPopupLoaded();
        if (apisSystem.editUser.isFirstNameEmpty().isEmpty() ||
                apisSystem.editUser.isLastNameEmpty().isEmpty()) {
            apisSystem.editUser.inputFistName(true, "firstNameTest");
            apisSystem.editUser.inputLastName(true, "lastNameTest");
        }
        apisSystem.editUser.clickOnEnabled();
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 12, dependsOnMethods = {"editUserChangeEnabled"})
    public void filterInputUsername() {
        String username = apisSystem.usersPage.getValue("firstUserUsername");
        apisSystem.usersPage.inputUserName(username);
        clickSearchAndReset();
        // TODO ASSERT
    }

    @Test(priority = 13, dependsOnMethods = {"filterInputUsername"})
    public void filterInputFirstName() {
        String firstName = apisSystem.usersPage.getValue("firstUserFirstName");
        apisSystem.usersPage.inputFirstName(firstName);
        clickSearchAndReset();
        // TODO ASSERT
    }

    @Test(priority = 14, dependsOnMethods = {"filterInputFirstName"})
    public void filterInputLastName() {
        String lastName = apisSystem.usersPage.getValue("firstUserLastName");
        apisSystem.usersPage.inputLastName(lastName);
        clickSearchAndReset();
        // TODO ASSERT
    }

    @Test(priority = 15, dependsOnMethods = {"filterInputLastName"})
    public void filterInputEmail() {
        String email = apisSystem.usersPage.getValue("firstUserEmail");
        apisSystem.usersPage.inputEmail(email);
        clickSearchAndReset();
        // TODO ASSERT
    }

    private void clickSearchAndReset() {
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.usersPage.waitInvisibilityProcessing();
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

}
