package pages.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class WithdrawalPage extends Page {

    private static final Logger log = Logger.getLogger(WithdrawalPage.class);

    public WithdrawalPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Sort data table by some tab
     *
     * @param tabIndex tab index on list data table, where
     *                 0 - ID (not used)
     *                 1 - Brand
     *                 2 - Customer ID
     *                 3 - Date
     *                 4 - Days of expire (not used)
     *                 5 - Assignee (not used)
     *                 6 - Desk (not used)
     *                 7 - Verification status (not used)
     *                 8 - Amount
     *                 9 - Customer (not used)
     *                 10 - Status (not used)
     */
    public void sortTab(int tabIndex) {
        List<WebElement> tabsList = web.getElements("listDataTableMainTabs");
        tabsList.get(tabIndex).click();
    }

    /**
     * Input data in customer ID field from filter entity
     *
     * @param customerID customer id
     */
    public void inputCustomerID(String customerID) {
        web.clearAndInput("filterFieldCustomerID", customerID);
    }

    /**
     * Input brand name in brand field
     *
     * @param brandName brand name
     */
    public void inputBrand(String brandName) {
        web.clearAndInputAndClickEnter("filterWithdrawalBrandFiled", brandName);
    }

    /**
     * Delete all brands from Brand field
     */
    public void deleteAllBrands() {
        // brands names
        List<WebElement> brandsNamesList = web.getElements("filterBrandsNames");
        // brands close elements
        List<WebElement> brandsClosesList = web.getElements("filterCloseBrands");
        // brands count
        List<WebElement> brandsList = web.getElements("filterBrandsCount");
        // number of brands in the field
        final int BRANDS_COUNT = brandsList.size();
        for (int i = BRANDS_COUNT; i > 0; i--) {
            log.info(String.format("delete brand < %s >", brandsNamesList.get(i - 1).getText()));
            brandsClosesList.get(i - 1).click();
        }
    }

    /**
     * Get customer ID from first row on list
     */
    public String getCustomerID_fromFirstRow() {
        WebElement element = web.getElement("withdrawalCustomerID_1row");
        log.info(String.format("customer ID: %s", element.getText()));
        return element.getText();
    }

    /**
     * Select status from drop down list
     *
     * @param statusPosition status, where
     *                       0 - Pending
     *                       1 - Approved
     *                       2 - Canceled
     */
    public void selectStatus(int statusPosition) {
        // click on status field
        web.getElement("filterStatus");
        List<WebElement> statusList = web.getElements("filterStatusList");
        log.info(String.format("select status: %s", statusList.get(statusPosition).getText()));
        statusList.get(statusPosition).click();
    }

    /**
     * Click on plus from first row
     */
    public void clickOnPlusOnFirstRow() {
        web.getElement("withdrawalCustomerItem_1row").click();
    }

    /**
     * Click on Deposits plus/minus from some user
     */
    public void clickDepositsLink() {
        web.getElement("withdrawalDepositsLink").click();
    }

    /**
     * Click on Withdrawals plus/minus from some user
     */
    public void clickWithdrawalsLink() {
        web.getElement("withdrawalWithdrawalsLink").click();
    }

    /**
     * Click on some item link from user
     *
     * @param linkPosition where
     *                     1 - General link
     *                     2 - Status link
     *                     3 - Activity link
     */
    public void clickOnItemsFromUser(int linkPosition) {
        List<WebElement> itemsList = web.getElements("withdrawalOtherLinks");
        List<WebElement> itemsTextList = web.getElements("withdrawalOtherLinksText");
        log.info(String.format("click on < %s >", itemsTextList.get(linkPosition - 1).getText()));
        itemsList.get(linkPosition - 1).click();
    }

    /**
     * Wait for loaders
     *
     * @param selectLoader where
     *                     customer - loader for customer
     *                     deposits - loader for deposits
     *                     withdrawal - loader for withdrawal
     */
    public void waitForLoaders(String selectLoader) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout15sec")));
        if (selectLoader.equals("customer")) {
            element = web.getElement("withdrawalCustomerLoader");
        } else if (selectLoader.equals("deposits")) {
            element = web.getElement("withdrawalDepositsLoader");
        } else if (selectLoader.equals("withdrawal")) {
            element = web.getElement("withdrawalWithdrawalsLoader");
        }
        wait.until(ExpectedConditions.attributeContains(element, "class", "loader-hide"));
    }

    /**
     * Click on 'View' button in some position
     *
     * @param viewPosition view position on a page
     */
    public void clickViewButton(int viewPosition) {
        List<WebElement> viewList = web.getElements("withdrawalActivityViewList");
        if (viewList.size() == 0) {
            log.info("'View' buttons missing on a page");
        } else if (viewList.size() == 1) {
            log.info(String.format("select 'View' on < %s > position", viewList.size()));
            viewList.get(0).click();
        } else {
            log.info(String.format("select 'View' on < %s > position", viewPosition));
            viewList.get(viewPosition - 1).click();
        }
    }

    /**
     * Click on 'View' link for comment
     *
     * @param viewPosition view position op table
     */
    public void clickOnViewForComment(int viewPosition) {
        List<WebElement> viewList = web.getElements("withdrawalCommentView");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout3sec")));
        wait.until(ExpectedConditions.elementToBeClickable(viewList.get(viewPosition - 1)));
        viewList.get(viewPosition - 1).click();
    }

    public boolean checkViewButton(int viewPosition) {
        List<WebElement> viewList = web.getElements("withdrawalActivityViewList");
        return viewList.get(viewPosition - 1).getAttribute("class")
                .equals("scope-directive-withdrawal-activity before-click");
    }

    /**
     * Scroll to element(+) without header
     */
    public void scrollToElement(String elementLocator) {
        web.scrollToElementTo(elementLocator, "navigationWrapper");
//        web.scrollToElementBy(elementLocator, "withdrawalToggleBlock");
    }

    /**
     * Scroll to element(-) without header
     */
    public void scrollToElementBack(String elementLocator) {
        web.scrollToElementBack(elementLocator, "navigationWrapper");
    }

    /**
     * Is button plus (+) to be clicked
     */
    public boolean isPlusItemActive() {
        return web.isElementPresent("withdrawalCustomerPlus_1row", 500);
    }

    /**
     * Click on button 'Assign'
     */
    public void clickButtonAssign() {
        web.clickLink("assignButtonPosition1");
    }

    public void closeCommentMessage() {
        web.clickLink("withdrawalCommentModal");
    }

    /**
     * Get user data from column
     *
     * @param columnPosition column position from user row, where
     *                       0 - ID
     *                       1 - Brand
     *                       2 - Customer ID
     *                       3 - Date
     *                       4 - Days of expire
     *                       5 - Assignee
     *                       6 - Desk
     *                       7 - Verification status
     *                       8 - Amount
     *                       9 - Customer (not used)
     *                       10 - Status
     */
    public String getCustomerDataFromColumn(int columnPosition) {
        List<WebElement> userDataList = web.getElements("userRowListPosition1");
        String result = userDataList.get(columnPosition).getText();
        log.info(String.format("customer ID: %s", result));
        return result;
    }

    /**
     *
     * */
    public void clickOnDecline(int declinePosition) {
        List<WebElement> declineList = web.getElements("withdrawalDeclineList");
        log.info(String.format("click on < %s > in position %s",
                declineList.get(declinePosition).getText(), declinePosition));
        declineList.get(declinePosition).click();
    }

    public int getUserID(int userPosition) {
        List<WebElement> id_list = web.getElements("withdrawalUsersID_list");
        String userID = id_list.get(userPosition).getText();
        log.info(String.format("user ID: < %s >", userID));
        return Integer.parseInt(userID);
    }

    public int getUserIndex(int userID) {
        List<WebElement> usersID_list = web.getElements("usersRowsIdList");
        for (int i = 0; i < usersID_list.size(); i++) {
            if (userID == Integer.parseInt(usersID_list.get(i).getText())) {
                log.info(String.format("user index: " + i));
                return i;
            }
        }
        log.info("user index: -1");
        return -1;
    }

}
