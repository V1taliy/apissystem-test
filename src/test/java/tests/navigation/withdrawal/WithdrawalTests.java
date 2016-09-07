package tests.navigation.withdrawal;

import org.testng.annotations.Test;
import tests.Fixture;
import tests.login.TestUserLogin;

public class WithdrawalTests extends Fixture {

    @Test(priority = 1)
    public void testUsersLogin() {
        TestUserLogin.openWebSiteAndLoginTestsUsers();
    }

}
