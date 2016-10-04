package pages.users;

import org.apache.log4j.Logger;
import org.openqa.selenium.Beta;
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

    // methods for FILTER

    /**
     * Input data in someone of the fields
     *
     * @param fieldName someone of the fields names, where
     *                  UserName - user name field
     *                  FirstName - first name field
     *                  LastName - last name field
     *                  Email - email field
     *                  Group - group field
     */
    public void filterInput(String fieldName, String inputData) {
        log.info(String.format("input in < %s > field", fieldName));
        if (fieldName.equals("Group")) {
            web.clearAndInputAndClickEnter(String.format("filter%sField", fieldName), inputData);
        } else {
            web.clearAndInput(String.format("filter%sField", fieldName), inputData);
        }
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
     * Click action button
     *
     * @param buttonPosition button position on list entity
     */
    public void clickActionButton(int buttonPosition) {
        List<WebElement> actionButtonList = web.getElements("actionButtonList");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout5sec")));
        wait.until(ExpectedConditions.visibilityOf(actionButtonList.get(buttonPosition)));
        actionButtonList.get(buttonPosition).click();
    }

    /**
     * Click action item from drop down menu
     *
     * @param menuItem item from menu, where
     *                 1 - 'Disable user'
     *                 2 - 'Enable user'
     *                 3 - not used
     *                 4 - Edit user
     *                 5 - not used
     *                 6- Edit desks
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

    /**
     * Get value from group field
     *
     * @param locator group filed locator
     */
    public String getGroupValue(String locator) {
        return web.getElement(locator).getText();
    }

    /**
     * Click on brands filed on filter desk
     */
    public void filterClickOnBrandsField() {
        web.clickLink("filterBrandFiled");
    }

    /**
     * Click on brand from drop down list in Brands field
     *
     * @param brandPosition brand position
     * @return brand name
     */
    public String filterClickAndGetBrand(int brandPosition) {
        List<WebElement> brandsList = web.getElements("filterBrandsList");
        String brandName = null;
        if (brandsList.size() == 1) {
            brandName = brandsList.get(0).getText();
            log.info(String.format("select brand < %s >", brandName));
            brandsList.get(0).click();
        } else {
            brandName = brandsList.get(brandPosition - 1).getText();
            log.info(String.format("select brand < %s >", brandName));
            brandsList.get(brandPosition - 1).click();
        }
        return brandName;
    }

    /**
     * Get brand name from first position
     */
    public String getBrandName() {
        List<WebElement> list = web.getElements("brandsColumnList");
        return list.get(0).getText();
    }

}
