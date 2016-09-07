package tests.navigation.withdrawal;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import tests.login.LoginTests;
import tests.login.TestUserLogin;
import tests.navigation.brands.BrandsTests;
import utils.PropertyLoader;

public class WithdrawalTests extends Fixture {

    private static final String BRANDS_URL = PropertyLoader.loadProperty("brands.url");

    @Test(priority = 1)
    public void testUsersLogin() {
        TestUserLogin.openWebSiteAndLoginTestsUsers();
    }

    @Test(priority = 2)
    public void adminLogin() {
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 3)
    public void switchToBrands() {
        apisSystem.mainPage.clickOnNavigationItem(2);
        Assert.assertEquals(apisSystem.brandsPage.getCurrentPageURL(), BRANDS_URL);
    }

}
