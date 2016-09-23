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

}
