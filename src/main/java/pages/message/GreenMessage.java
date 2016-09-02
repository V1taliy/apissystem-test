package pages.message;

import pages.Page;
import utils.WebDriverWrapper;

public class GreenMessage extends Page {

    public GreenMessage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
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

}
