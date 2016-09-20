package pages.deskExpirationTime;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class DeskExpirationTime extends Page {

    private static final Logger log = Logger.getLogger(DeskExpirationTime.class);

    public DeskExpirationTime(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
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
        log.info(String.format("select < %s > element", tableList.get(tableNumber - 1).getText()));
        tableList.get(tableNumber - 1).click();
    }

    /**
     * Scroll to one of the buttons (1, 2 ...) and click this button
     *
     * @param buttonIndex button index
     */
    public void scrollAndClickNavigationIndexButton(int buttonIndex) {
        List<WebElement> buttonsList = web.getElements("listNavigationButtons");
        web.scrollToElementBy(buttonsList.get(buttonIndex).getLocation().getY());
        log.info(String.format("scroll to < %s >", buttonsList.get(buttonIndex).getText()));
        ExpectedConditions.elementToBeClickable(buttonsList.get(buttonIndex));
        log.info(String.format("click on < %s > button", buttonsList.get(buttonIndex).getText()));
        buttonsList.get(buttonIndex).click();
    }

    /**
     * Scroll to one of the buttons and click this button
     *
     * @param buttonName button name, where
     *                   first - First button
     *                   previous - Previous button
     *                   next - Next button
     *                   last - Last button
     */
    public void scrollAndClickNavigationButtons(String buttonName) {
        switch (buttonName) {
            case "first":
                web.scrollToElementBy("buttonFirst");
                log.info(String.format("scroll to < %s >", web.getElement("buttonFirst").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonFirst"));
                web.clickLink("buttonFirst");
                log.info(String.format("click on < %s > button", web.getElement("buttonFirst").getText()));
                break;
            case "previous":
                web.scrollToElementBy("buttonPrevious");
                log.info(String.format("scroll to < %s >", web.getElement("buttonPrevious").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonPrevious"));
                web.clickLink("buttonPrevious");
                log.info(String.format("click on < %s > button", web.getElement("buttonPrevious").getText()));
                break;
            case "next":
                web.scrollToElementBy("buttonNext");
                log.info(String.format("scroll to < %s >", web.getElement("buttonNext").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonNext"));
                web.clickLink("buttonNext");
                log.info(String.format("click on < %s > button", web.getElement("buttonNext").getText()));
                break;
            case "last":
                web.scrollToElementBy("buttonLast");
                log.info(String.format("scroll to < %s >", web.getElement("buttonLast").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonLast"));
                web.clickLink("buttonLast");
                log.info(String.format("click on < %s > button", web.getElement("buttonLast").getText()));
                break;
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
     *                 1 - Edit
     */
    public void clickItemFromDropDownMenu(int menuItem) {
        List<WebElement> elementList = web.getElements("actionItemDropDownList");
        elementList.get(menuItem - 1).click();
        log.info(String.format("click on < %s >", elementList.get(menuItem - 1).getTagName()));
    }

    /**
     * Click on select checkbox
     *
     * @param checkboxPosition checkbox position in list table
     */
    public void selectCheckboxInPosition(int checkboxPosition) {
        List<WebElement> checkboxList = web.getElements("checkboxList");
        if (checkboxList.get(checkboxPosition).isSelected()) {
            checkboxList.get(checkboxPosition).click();
        }
    }

    /**
     * Get value from expiration time item in position
     *
     * @param expirationTimePosition position on a table
     */
    public int getExpirationTimeValue(int expirationTimePosition) {
        List<WebElement> elementList = web.getElements("expirationTimeList");
        return Integer.parseInt(elementList.get(expirationTimePosition - 1).getText());
    }

    /**
     * Get first brand name from list data table
     */
    public String getBrandNameFromList() {
        List<WebElement> brandsNameList = web.getElements("expirationBrandList");
        log.info(brandsNameList.get(0).getText());
        return brandsNameList.get(0).getText();
    }

    public String listGetDeskName() {
        log.info(String.format("value = %s", web.getElement("listDeskName").getText()));
        return web.getElement("listDeskName").getText();
    }

    public void waitDeskToBeActive() {
        WebElement element = web.getElement("filterDeskBrand");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout20sec")));
        wait.until(ExpectedConditions.attributeToBe(element, "disabled", ""));
    }

    public void scrollPageToNavigationWrapper() {
        web.scrollToElementBy("navigationWrapper");
    }

    /* Methods for filter entity*/

    /**
     * Get brand value from brand field
     */
    public String filterGetBrandValue() {
        List<WebElement> brandFieldList = web.getElements("filterBrandFiledDefault");
        log.info(brandFieldList.get(0).getText());
        return brandFieldList.get(0).getText();
    }

    /**
     * Clicked on first brand from drop down list 'Brand'
     */
    public void filterClickedOnSelectBrand() {
        List<WebElement> brandsList = web.getElements("filterBrandFieldList");
        int random = (int) Math.random() * brandsList.size();
        log.info("clicked on " + brandsList.get(random).getText());
        brandsList.get(random).click();
    }

    /**
     * Get brand name from drop down list
     *
     * @param position brand position in drop down list
     */
    public String getFilterBrandFiledBrandName(int position) {
        List<WebElement> brandsNameList = web.getElements("filterBrandFieldList");
        log.info(brandsNameList.get(position - 1).getText());
        return brandsNameList.get(position - 1).getText();
    }

    /**
     * Click on first desk value from drop down list
     */
    public int filterClikedOnDesk() {
        List<WebElement> deskList = web.getElements("filterDeskFieldList");
        int random = (int) (Math.random() * deskList.size());
        log.info(String.format("clicked on < %s >", deskList.get(random).getText()));
        deskList.get(random).click();
        return random;
    }

    /**
     * Get desk value from desk field
     */
    public String filterGetDeskValue() {
        log.info(String.format("value = %s", web.getElement("filterDeskBrand").getText()));
        return web.getElement("filterDeskBrand").getText();
    }

    public String filterGetDeskValue(int value) {
        List<WebElement> elementList = web.getElements("filterDeskBrandList");
        log.info(String.format("value = %s", elementList.get(value).getText()));
        return elementList.get(value).getText();
    }

    /**
     * Click on desk filed
     */
    public void filterClickOnDesk() {
        web.clickLink("filterDeskBrand");
    }

}
