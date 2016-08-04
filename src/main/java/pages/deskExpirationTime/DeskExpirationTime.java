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
        log.info(String.format("select on < %s >", tableList.get(tableNumber - 1).getText()));
        tableList.get(tableNumber - 1).click();
    }

    /**
     * Click on one of the buttons
     *
     * @param buttonName button name, where
     *                   first - First button
     *                   previous - Previous button
     *                   next - Next button
     *                   last - Last button
     */
    public void testScrollAndClick(String buttonName) {
        switch (buttonName) {
            case "first":
                web.scrollToElement("buttonFirst");
                log.info(String.format("scroll to < %s >", web.getElement("buttonFirst")));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonFirst"));
                web.clickLink("buttonFirst");
                log.info(String.format("click on < %s >", web.getElement("buttonFirst")));
                break;
            case "previous":
                web.scrollToElement("buttonPrevious");
                log.info(String.format("scroll to < %s >", web.getElement("buttonPrevious")));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonPrevious"));
                web.clickLink("buttonPrevious");
                log.info(String.format("click on < %s >", web.getElement("buttonPrevious")));
                break;
            case "next":
                web.scrollToElement("buttonNext");
                log.info(String.format("scroll to < %s >", web.getElement("buttonNext")));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonNext"));
                web.clickLink("buttonNext");
                log.info(String.format("click on < %s >", web.getElement("buttonNext")));
                break;
            case "last":
                web.scrollToElement("buttonLast");
                log.info(String.format("scroll to < %s >", web.getElement("buttonLast")));
                ExpectedConditions.elementToBeClickable(web.getElement("buttonLast"));
                web.clickLink("buttonLast");
                log.info(String.format("click on < %s >", web.getElement("buttonLast")));
                break;
        }
    }

}
