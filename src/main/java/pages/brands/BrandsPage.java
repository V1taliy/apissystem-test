package pages.brands;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class BrandsPage extends Page {

    private static final Logger log = Logger.getLogger(BrandsPage.class);

    public BrandsPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Click action button
     */
    public void clickActionButton(int buttonPosition) {
        List<WebElement> actionButtonList = web.getElements("actionButtonList");
        actionButtonList.get(buttonPosition).click();
    }

    /**
     * Click action item from drop down menu
     *
     * @param menuItem item from menu, where
     *                 0 - Edit brand
     *                 1 - not used
     *                 2 - Disable brand
     *                 3 - Enable brand
     *                 4 - not used
     *                 5 - Delete brand
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
        return web.getElement(locator).getText();
    }

    public int getFirstPosition() {
        return web.getElements("firstPositionBrand").size();
    }

    /* methods for filter entity*/

    public void filterInputBrandName(String brandName) {
        web.clearAndInputAndClickEnter("filterBrandNameInput", brandName);
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
     * Get brand index from list entity
     *
     * @param brandName brand name
     * @return if brand name finding return brand index, otherwise return -1
     */
    public int getBrandIndex(String brandName) {
        List<WebElement> brandsNameList = web.getElements("brandNameList");
        for (int i = 0; i < brandsNameList.size(); i++) {
            if (brandsNameList.get(i).getText().equals(brandName)) {
                log.info(String.format("brand index: " + i));
                return i;
            }
        }
        log.error("brand not found");
        return -1;
    }

}
