package pages.message;

import pages.Page;
import utils.WebDriverWrapper;

public class SuccessMessage extends Page implements Message {

    private static final String SUCCESS_MESSAGE = "greenMessage";

    public SuccessMessage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    @Override
    public void waitMessagePresent() {
        web.waitElementToBeVisibility(SUCCESS_MESSAGE);
    }

    @Override
    public void waitMessageInvisibility() {
        web.waitDisappearElement(SUCCESS_MESSAGE);
    }

    @Override
    public boolean isMessagePresent() {
        return web.isElementPresent(SUCCESS_MESSAGE);
    }

    public void waitInvisibilityOverlay() {
        web.waitDisappearElement("overlay");
    }

}
