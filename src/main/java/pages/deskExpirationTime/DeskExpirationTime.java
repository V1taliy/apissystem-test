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
     * Wait for processing disappear
     */
    public void waitLoadedAttributeToBeEmptyClass() {
        WebElement element = web.getElement("isLoadedElement");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout5sec")));
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
        web.scrollToElement(buttonsList.get(buttonIndex).getLocation().getY());
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
                web.scrollToElement("buttonFirst");
                log.info(String.format("scroll to < %s >", web.getElement("buttonFirst").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonFirst"));
                web.clickLink("buttonFirst");
                log.info(String.format("click on < %s > button", web.getElement("buttonFirst").getText()));
                break;
            case "previous":
                web.scrollToElement("buttonPrevious");
                log.info(String.format("scroll to < %s >", web.getElement("buttonPrevious").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonPrevious"));
                web.clickLink("buttonPrevious");
                log.info(String.format("click on < %s > button", web.getElement("buttonPrevious").getText()));
                break;
            case "next":
                web.scrollToElement("buttonNext");
                log.info(String.format("scroll to < %s >", web.getElement("buttonNext").getText()));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonNext"));
                web.clickLink("buttonNext");
                log.info(String.format("click on < %s > button", web.getElement("buttonNext").getText()));
                break;
            case "last":
                web.scrollToElement("buttonLast");
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

    public void scrollPageToNavigationWrapper() {
        web.scrollToElement("navigationWrapper");
    }

}
