package pages.message;

import pages.Page;
import utils.WebDriverWrapper;

public class GreenMessage extends Page {

    private static final String GREEN_MESSAGE = "greenMessage";

    public GreenMessage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Wait for message present on a page
     */
    public void waitMessageSuccessPresent() {
        web.waitElementToBeVisibility(GREEN_MESSAGE);
    }

    public void waitMessageInvisibility() {
        web.waitDisappearElement(GREEN_MESSAGE);
    }

    /**
     * Is message 'Success' present on a page
     */
    public boolean isMessageSuccessPresent() {
        return web.isElementPresent(GREEN_MESSAGE);
    }

    public void waitInvisibilityOverlay() {
        web.waitDisappearElement("overlay");
    }

}
