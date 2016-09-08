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
    public void editTestUser7selectGroupAndBrand() {
        int userIndex;
        try {
            Thread.sleep(1000);
            apisSystem.usersPage.scrollDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userIndex = apisSystem.listEntity.getUserNameIndex(TEST_USER_7);
        log.info(String.format("user index = %s", userIndex));
        apisSystem.usersPage.clickActionButton(userIndex);
        apisSystem.usersPage.clickItemActionFromDropDownMenu(4);
        apisSystem.editUser.waitPopupLoaded();
        // select Retention RL
        apisSystem.editUser.clickAndSelectGroup(9);
        apisSystem.editUser.clickOnBrandsField();
        apisSystem.editUser.clickOnSelectBrand(3);
        apisSystem.editUser.clickButtonSaveOrCancel(true);
    }

}
