package pages.popups;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class MainPopup extends Page {

    private static final Logger log = Logger.getLogger(MainPopup.class);

    public MainPopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Check is pop up window displayed on a page
     */
    public boolean isPopupPresent() {
        log.info(String.format("pop up < %s > is present",
                web.getElement("popupHeader").getText()));
        return web.isElementPresent("popupHeader");
    }

    /**
     * Click on button 'Save' or 'Cancel' in pop up
     *
     * @param button where
     *               true - button 'Save'
     *               false - button 'Cancel'
     */
    public boolean clickButtonSaveOrCancel(boolean button) {
        if (button) {
            log.info("click on button 'Save'");
            web.clickButton("buttonSave");
            return true;
        } else {
            log.info("click on button 'Cancel'");
            web.clickButton("buttonCancel");
            return true;
        }
    }

    /**
     * Close pop up (click on 'Close' element)
     */
    public void closePopup() {
        web.clickLink("closePopup");
    }

    /**
     * Wait for pop up loaded
     *
     * @return true if pop up down buttons loaded, otherwise false
     */
    public boolean waitPopupLoaded() {
        return web.waitElementToBeVisibility("popupLoaded");
    }

    /**
     * Wait invisibility a pop up
     */
    public boolean waitInvisibilityPopup() {
        return web.waitDisappearElement("popupLoaded");
    }

}
