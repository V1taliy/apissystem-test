package tests.navigation.withdrawal;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Fixture;
import tests.login.LoginTests;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

public class WithdrawalFilterAndDatatableTests extends Fixture {

    private static final Logger log = Logger.getLogger(WithdrawalFilterAndDatatableTests.class);

    private static final String LOGIN_URL = PropertyLoader.loadProperty("login.url");
    private static final String WITHDRAWAL_URL = PropertyLoader.loadProperty("withdrawal.url");
    private static final String TEST_CUSTOMER_ID = PropertyLoader.loadProperty("test.customerID");

    private static final int newImpWait = 300;

    @Test(priority = 1)
    public void adminLogin() {
        apisSystem.loginPage.openLoginPage();
        apisSystem.loginPage.waitInvisibilityLoader();
        Assert.assertEquals(apisSystem.loginPage.getPageURL(), LOGIN_URL);
        impWait = String.valueOf(newImpWait);
        driverWrapper.manage().timeouts().implicitlyWait(Long.parseLong(impWait), TimeUnit.MILLISECONDS);
        LoginTests.loginForAdmin(false);
    }

    @Test(priority = 2)
    public void switchToWithdrawal() {
        apisSystem.mainPage.clickOnNavigationItem(1);
        Assert.assertEquals(apisSystem.withdrawalPage.getCurrentPageURL(), WITHDRAWAL_URL);
    }

    @Test(priority = 3)
    public void inputIncorrectCustomerID() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        apisSystem.withdrawalPage.inputCustomerID("testCustomerID");
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertTrue(apisSystem.withdrawalPage.isCustomerIdErrorMessagePresent());
    }

    @Test(priority = 4)
    public void inputCorrectCustomerID() {
        int test_customerID = apisSystem.withdrawalPage.getCustomerID(0);
        apisSystem.withdrawalPage.inputCustomerID(String.valueOf(test_customerID));
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.withdrawalPage.getCustomerID(0), test_customerID);
    }

    @Test(priority = 5)
    public void clickButtonReset() {
        clickResetButton();
    }

    @Test(priority = 6)
    public void selectVerificationStatus() {
        String verificationStatus = "Partial";
        apisSystem.withdrawalPage.clickOnVerificationStatusField();
        apisSystem.withdrawalPage.selectVerificationStatus(verificationStatus);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.withdrawalPage.getVerificationStatus(0), verificationStatus);
    }

    @Test(priority = 7)
    public void clickButtonReset2() {
        clickResetButton();
    }

    @Test(priority = 8)
    public void selectStatus() {
        String status = "Pending";
        apisSystem.withdrawalPage.selectStatus(status);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.withdrawalPage.getStatus(0), status.toLowerCase());
    }

    @Test(priority = 9)
    public void clickButtonReset3() {
        clickResetButton();
    }

    @Test(priority = 10)
    public void findTestUser() {
        String status = "Approved";
        String brand = "toroption";
        int position = 0;
        apisSystem.withdrawalPage.inputCustomerID(TEST_CUSTOMER_ID);
        apisSystem.withdrawalPage.selectStatus(status);
        apisSystem.withdrawalPage.inputBrand(brand);
        apisSystem.filterEntity.clickSearchOrResetButton(true);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
        Assert.assertEquals(apisSystem.withdrawalPage.getCustomerID(position), Integer.parseInt(TEST_CUSTOMER_ID));
        Assert.assertEquals(apisSystem.withdrawalPage.getStatus(position), status.toLowerCase());
        Assert.assertEquals(apisSystem.withdrawalPage.getBrandName(position), brand);
    }

    @Test(priority = 11)
    public void clickButtonReset4() {
        clickResetButton();
    }

    private static void clickResetButton() {
        apisSystem.filterEntity.clickSearchOrResetButton(false);
        if (apisSystem.filterEntity.isLoadedClassHaveAttributeInClass()) {
            apisSystem.filterEntity.waitLoadedAttributeToBeEmptyClass();
        }
    }

}
