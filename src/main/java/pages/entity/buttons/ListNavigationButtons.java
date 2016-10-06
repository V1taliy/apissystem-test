package pages.entity.buttons;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class ListNavigationButtons extends Page {

    private final static Logger log = Logger.getLogger(ListNavigationButtons.class);

    public ListNavigationButtons(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public void scrollPageToNavigationWrapper() {
        web.scrollToElementBy("navigationWrapper");
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
     *                   First - first button
     *                   Previous - previous button
     *                   Next - next button
     *                   Last - last button
     */
    public void scrollAndClickNavigationButtons(String buttonName) {
        web.scrollToElementBy(String.format("button%s", buttonName));
        log.info(String.format("scroll to < %s >", web.getElement("button" + buttonName).getText()));
        ExpectedConditions.elementToBeClickable(web.getElement("button" + buttonName));
        web.clickLink("button" + buttonName);
        log.info(String.format("click on < %s > button", web.getElement("button" + buttonName).getText()));
    }

}
