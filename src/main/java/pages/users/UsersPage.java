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

    public void clickOnGroupField() {
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
            log.info(String.format("click on < %s > button",
                    web.getElement("filterButtonSearch").getText()));
            web.clickButton("filterButtonSearch");
        } else {
            web.clickButton("filterButtonReset");
            log.info(String.format("click on < %s > button",
                    web.getElement("filterButtonReset").getText()));
        }
    }

    // methods for LIST

    /**
     * Select and click table sort
     *
     * @param tableNumber number of table, where
     *                    0 - ID
     *                    1 - user name
     *                    2 - first name
     *                    3 - last name
     *                    4 - email
     *                    5 - group
     *                    6 - brands
     *                    7 - enable
     *                    8 - role
     */
    public void selectTableSort(int tableNumber) {
        List<WebElement> tableList = web.getElements("listDataTableMainTabs");
        log.info(String.format("select < %s > tab sort", tableList.get(tableNumber).getText()));
        tableList.get(tableNumber).click();
    }

    /**
     * Click user checkbox
     *
     * @param userPosition user position in the desk table
     */
    public void clickUserCheckbox(int userPosition) {
        List<WebElement> checkboxList = web.getElements("checkboxList");
        log.info(String.format("click on checkbox in  < %s > position", userPosition));
        checkboxList.get(userPosition).click();
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
            log.info(String.format("click on \'Enable user\'"));
            buttonList.get(1).click();
        } else {
            log.info(String.format("click on \'Disable user\'"));
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
        log.info(String.format("click on action button in < %s > position", buttonPosition));
        actionButtonList.get(buttonPosition).click();
    }

    /**
     * Click action item from drop down menu
     *
     * @param menuItem item from menu, where
     *                 0 - 'Disable user'
     *                 1 - 'Enable user'
     *                 2 - Edit user
     *                 3- Edit desks
     */
    public void clickItemActionFromDropDownMenu(int menuItem) {
        List<WebElement> elementActionDropDownList = web.getElements("actionItemDropDownList");
        log.info(String.format("click on < %s >",
                elementActionDropDownList.get(menuItem).getTagName()));
        elementActionDropDownList.get(menuItem).click();
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
        String brandName;
        if (brandsList.size() == 1) {
            brandName = brandsList.get(0).getText();
            log.info(String.format("select brand < %s >", brandName));
            brandsList.get(0).click();
        } else {
            brandName = brandsList.get(brandPosition).getText();
            log.info(String.format("select brand < %s >", brandName));
            brandsList.get(brandPosition).click();
        }
        return brandName;
    }

    public String filterClickAndGetBrand() {
        List<WebElement> brandsList = web.getElements("filterBrandsList");
        String brandName;
        if (brandsList.size() == 1) {
            brandName = brandsList.get(0).getText();
            log.info(String.format("select brand \'%s\'", brandName));
            brandsList.get(0).click();
        } else {
            int random = (int) (Math.random() * brandsList.size());
            brandName = brandsList.get(random).getText();
            log.info(String.format("select brand \'%s\'", brandName));
            brandsList.get(random).click();
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
