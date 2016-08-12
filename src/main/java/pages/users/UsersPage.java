package pages.users;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class UsersPage extends Page {

    private static final Logger log = Logger.getLogger(UsersPage.class);

    public UsersPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    private static int userRandomCheckbox;

    // methods for FILTER

    public void inputUserName(String userName) {
        web.clearAndInput("filterUserNameField", userName);
    }

    public void inputFirstName(String firstName) {
        web.clearAndInput("filterFirstNameField", firstName);
    }

    public void inputLastName(String lastName) {
        web.clearAndInput("filterLastNameField", lastName);
    }

    public void inputEmail(String email) {
        web.clearAndInput("filterEmailField", email);
    }

    public void inputGroupAndClickEnter(String group) {
        web.clearAndInputAndClickEnter("filterGroupField", group);
    }

    public void clickOnGroupFiled() {
        web.clickLink("filterGroupField");
    }

    /**
     * Click button 'Search' or 'Reset'
     *
     * @param selectButton button, where
     *                     true - Search
     *                     false - Reset
     */
    public void clickButtonSearchOrReset(boolean selectButton) {
        if (selectButton) {
            web.clickButton("filterButtonSearch");
        } else {
            web.clickButton("filterButtonReset");
        }
    }

    // methods for LIST

    /**
     * Select and click table sort
     *
     * @param tableNumber number of table, where
     *                    1 - select all checkbox
     *                    2 - ID
     *                    3 - user name
     *                    4 - first name
     *                    5 - last name
     *                    6 - email
     *                    7 - group
     *                    8 - enable
     *                    9 - role
     */
    public void selectTableSort(int tableNumber) {
        List<WebElement> tableList = web.getElements("listDataTableMainTabs");
        tableList.get(tableNumber - 1).click();
    }

    /**
     * Click user checkbox
     *
     * @param userPosition user position in the desk table
     */
    public void clickUserCheckbox(int userPosition) {
        List<WebElement> checkboxList = web.getElements("checkboxList");
        checkboxList.get(userPosition - 1).click();
    }

    /**
     * Click button 'Disable user' or 'Enable user'
     *
     * @param selectButton button, where
     *                     true - 'Enable user'
     *                     false - 'Disable user'
     */
    public void clickToggleButton(boolean selectButton) {
        List<WebElement> buttonList = web.getElements("toggleButtonList");
        if (selectButton) {
            buttonList.get(1).click();
        } else {
            buttonList.get(0).click();
        }
    }

    /**
     * Wait for message present on a page
     */
    public void waitMessageSuccessPresent() {
        web.waitElementToBeVisibility("greenMessage");
    }

    /**
     * Is message 'Success' present on a page
     */
    public boolean isMessageSuccessPresent() {
        return web.isElementPresent("greenMessage");
    }

    /**
     * Click action button
     */
    public void clickActionButton(int buttonPosition) {
        List<WebElement> actionButtonList = web.getElements("actionButtonList");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout3sec")));
        wait.until(ExpectedConditions.elementToBeClickable(actionButtonList.get(buttonPosition - 1)));
        actionButtonList.get(buttonPosition - 1).click();
    }

    /**
     * Wait for loaded element will be class empty
     */
    public void waitLoadedAttributeToBeEmptyClass() {
        WebElement element = web.getElement("isLoadedElement");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout3sec")));
        wait.until(ExpectedConditions.attributeToBe(element, "class", ""));
    }

    /**
     * Check is loaded element have some parameters
     */
    public boolean isLoadedClassHaveAttributeInClass() {
        WebElement element = web.getElement("isLoadedElement");
        return element.getAttribute("class").contains("traditional");
    }

    /**
     * Click action item from drop down menu
     *
     * @param menuItem item from menu, where
     *                 1 - 'Disable user'
     *                 2 - 'Enable user'
     *                 3 - not used
     *                 4 - Edit user
     */
    public void clickItemActionFromDropDownMenu(int menuItem) {
        List<WebElement> elementActionDropDownList = web.getElements("actionItemDropDownList");
        elementActionDropDownList.get(menuItem - 1).click();
        log.info(String.format("click on < %s >",
                elementActionDropDownList.get(menuItem - 1).getTagName()));
    }

    /**
     * Get value from some element
     *
     * @param locator element locator
     */
    public String getValue(String locator) {
        return web.getElement(locator).getText();
    }

    /**
     * Get value from input field
     *
     * @param locator input field locator
     */
    public String getInputValue(String locator) {
        return web.getElement(locator).getAttribute("value");
    }

    public String getGroupValue(String locator) {
        return web.getElement(locator).getText();
    }

}
