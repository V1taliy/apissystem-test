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
    private static final int itemEditUser = 4;
    private static final String[] DATA_TEST = {"firstNameTest", "lastNameTest"};

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
                if (apisSystem.usersPage.isLoadedClassHaveAttributeInClass()) {
                    apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
                }
                apisSystem.usersPage.selectTableSort(i);
            }
        }
    }

    @Test(priority = 4, dependsOnMethods = {"goToUsersTab"})
    public void selectUserCheckboxAndClickToggleButtons() {
        boolean button = true;
        int userPosition = 1;
        apisSystem.usersPage.inputUserName(USER_NAME_TEST);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        for (int i = 1; i <= 2; i++) {
            if (apisSystem.usersPage.isLoadedClassHaveAttributeInClass()) {
                apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
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
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(1);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 6, dependsOnMethods = {"goToUsersTab"})
    private void clickedOnEnableUser() {
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(2);
        apisSystem.usersPage.waitMessageSuccessPresent();
        Assert.assertTrue(apisSystem.usersPage.isMessageSuccessPresent());
    }

    @Test(priority = 7, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void clickedOnEditUser() {
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        // select 'Edit user' item from drop down menu
        apisSystem.usersPage.clickItemActionFromDropDownMenu(itemEditUser);
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

    @Test(priority = 10, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void editUserInputFirstName() {
        apisSystem.editUser.inputFistName(true, DATA_TEST[0]);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
        apisSystem.editUser.waitInvisibilityLoading();
        Assert.assertTrue(apisSystem.editUser.isErrorMessagePresent());
    }

    @Test(priority = 11, enabled = true, dependsOnMethods = {"goToUsersTab"})
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

    @Test(priority = 12, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void editUserChangeEnabled() {
        apisSystem.editUser.waitInvisibilityPopup();
        apisSystem.usersPage.clickActionButton(actionButtonPosition);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(itemEditUser);
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

    @Test(priority = 13, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputUsername() {
        apisSystem.editUser.waitInvisibilityPopup();
        String username = apisSystem.usersPage.getValue("firstUserUsername");
        apisSystem.usersPage.inputUserName(username);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(username, apisSystem.usersPage.getInputValue("filterUserNameField"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

    @Test(priority = 14, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputFirstName() {
        apisSystem.editUser.waitInvisibilityPopup();
        String firstName = apisSystem.usersPage.getValue("firstUserFirstName");
        apisSystem.usersPage.inputFirstName(firstName);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(firstName, apisSystem.usersPage.getInputValue("filterFirstNameField"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

    @Test(priority = 15, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterInputLastName() {
        apisSystem.editUser.waitInvisibilityPopup();
        String lastName = apisSystem.usersPage.getValue("firstUserLastName");
        apisSystem.usersPage.inputLastName(lastName);
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(lastName, apisSystem.usersPage.getInputValue("filterLastNameField"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

    @Test(priority = 16, enabled = true, dependsOnMethods = {"goToUsersTab"})
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
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        Assert.assertEquals(email, apisSystem.usersPage.getInputValue("filterEmailField"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

    @Test(priority = 17, enabled = true, dependsOnMethods = {"goToUsersTab"})
    public void filterSelectGroup() {
        apisSystem.editUser.waitInvisibilityPopup();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.selectTableSort(7);
        if (apisSystem.usersPage.isLoadedClassHaveAttributeInClass()) {
            apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        }
        String group = apisSystem.usersPage.getValue("firstUserGroup");
        apisSystem.usersPage.inputGroupAndClickEnter(group);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apisSystem.usersPage.clickButtonSearchOrReset(true);
        apisSystem.usersPage.waitLoadedAttributeToBeEmptyClass();
        apisSystem.usersPage.clickOnGroupFiled();
        Assert.assertEquals(group, apisSystem.usersPage.getGroupValue("filterGroupValue"));
        apisSystem.usersPage.clickButtonSearchOrReset(false);
    }

}
