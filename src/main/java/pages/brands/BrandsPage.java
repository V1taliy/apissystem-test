package pages.brands;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class BrandsPage extends Page {

    private static final Logger log = Logger.getLogger(BrandsPage.class);

    public BrandsPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public void filterInputBrandName(String brandName) {
        web.clearAndInput("filterBrandNameInput", brandName);
    }

    /**
     * Click enabled field and select 'Enabled' or 'Disabled'
     *
     * @param status where
     *               true - enabled
     *               false - disabled
     * @info elementList.get(0) - Select (don't click)
     */
    public void filterClickAndSelectEnabled(boolean status) {
        web.clickLink("filterEnabledLink");
        List<WebElement> elementList = web.getElements("filterEnabledList");
        if (status) {
            elementList.get(1).click();
        } else {
            elementList.get(2).click();
        }
    }

    /**
     * Click on button 'Search' or 'Reset'
     *
     * @param button where
     *               true - Search
     *               false - Reset
     */
    public void filterClickSearchOrReset(boolean button) {
        List<WebElement> buttonsList = web.getElements("filterButtonList");
        if (button) {
            buttonsList.get(0).click();
        } else {
            buttonsList.get(1).click();
        }
    }

    /**
     * Click action button
     */
    public void clickActionButton(int buttonPosition) {
        List<WebElement> actionButtonList = web.getElements("actionButtonList");
        actionButtonList.get(buttonPosition - 1).click();
    }

    /**
     * Click action item from drop down menu
     *
     * @param menuItem item from menu, where
     *                 1 - Edit brand
     *                 2 - not used
     *                 3 - Disable brand
     *                 4 - Enable brand
     *                 5 - not used
     *                 6 - Delete brand
     */
    public void clickItemFromDropDownMenu(int menuItem) {
        List<WebElement> elementList = web.getElements("actionItemDropDownList");
        elementList.get(menuItem - 1).click();
        log.info(String.format("click on < %s >", elementList.get(menuItem - 1).getTagName()));
    }

    /**
     * Click user checkbox
     *
     * @param userPosition user position in the desk table
     */
    public void clickBrandCheckbox(int userPosition) {
        List<WebElement> checkboxList = web.getElements("checkboxList");
        checkboxList.get(userPosition - 1).click();
    }

    /**
     * Click button 'Create' or 'Delete' brand
     *
     * @param selectButton where
     *                     true - 'Create brand' button
     *                     false - 'Delete brand' button
     */
    public void createOrDeleteBrand(boolean selectButton) {
        if (selectButton) {
            web.clickButton("createBrandButton");
        } else {
            web.clickButton("deleteBrandButton");
        }
    }

    /**
     * Click button 'Disable user' or 'Enable user'
     *
     * @param selectButton button, where
     *                     true - 'Enable user'
     *                     false - 'Disable user'
     */
    public void clickToggleButton(boolean selectButton) {
        List<WebElement> buttonList = web.getElements("brandToggleButtonList");
        if (selectButton) {
            buttonList.get(1).click();
        } else {
            buttonList.get(0).click();
        }
    }

    /**
     * Select and click table sort
     *
     * @param tableNumber number of table, where
     *                    1 - select all checkbox
     *                    2 - ID
     *                    3 - brand name
     *                    4 - enabled
     *                    5 - last name
     */
    public void selectTableSort(int tableNumber) {
        List<WebElement> tableList = web.getElements("listDataTableMainTabs");
        tableList.get(tableNumber - 1).click();
    }

    /**
     * Wait for processing disappear
     */
    public void waitLoadedAttributeToBeEmptyClass() {
        WebElement element = web.getElement("isLoadedElement");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout3sec")));
        wait.until(ExpectedConditions.attributeToBe(element, "class", ""));
    }

    /**
     * Check is processing element present on the page
     *
     * @return true if processing element present, otherwise false
     */
    public boolean isLoadedClassHaveAttributeInClass() {
        WebElement element = web.getElement("isLoadedElement");
        return element.getAttribute("class").contains("traditional");
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

    public void waitInvisibilityOverlay() {
        web.waitDisappearElement("overlay");
    }

    /**
     * get value (brand name) from first position
     *
     * @param locator input field locator
     */
    public String getValueFromFirstBrandName(String locator) {
        log.info(web.getElement(locator).getText());
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

    public int getFirstPosition() {
        return web.getElements("firstPositionBrand").size();
    }

}
