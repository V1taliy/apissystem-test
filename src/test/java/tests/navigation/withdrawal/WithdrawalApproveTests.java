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

}
