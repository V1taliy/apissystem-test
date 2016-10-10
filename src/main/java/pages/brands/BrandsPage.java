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
        log.info(String.format("click on action button #%s", buttonPosition));
        actionButtonList.get(buttonPosition).click();
    }

    /**
     * Click action item from drop down menu
     *
     * @param menuItem item from menu, where
     *                 0 - Edit brand
     *                 1 - Disable brand
     *                 2 - Enable brand
     *                 3 - Delete brand
     */
    public void clickItemFromDropDownMenu(int menuItem) {
        List<WebElement> elementList = web.getElements("actionItemDropDownList");
        log.info(String.format("click on < %s >", elementList.get(menuItem).getTagName()));
        elementList.get(menuItem).click();
    }

    /**
     * Click user checkbox
     *
     * @param userPosition user position in the desk table
     */
    public void clickBrandCheckbox(int userPosition) {
        List<WebElement> checkboxList = web.getElements("checkboxList");
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
     *                    0 - ID
     *                    1 - brand name
     *                    2 - enabled
     */
    public void selectTableSort(int tableNumber) {
        List<WebElement> tableList = web.getElements("listDataTableMainTabs");
        tableList.get(tableNumber).click();
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
