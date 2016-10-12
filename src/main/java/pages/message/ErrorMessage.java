package pages.message;

import pages.Page;
import utils.WebDriverWrapper;

public class ErrorMessage extends Page implements Message {

    private static final String ERROR_MESSAGE = "errorMessage";

    public ErrorMessage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    @Override
    public void waitMessagePresent() {
        web.waitElementToBeVisibility(ERROR_MESSAGE);
    }

    @Override
    public void waitMessageInvisibility() {
        web.waitDisappearElement(ERROR_MESSAGE);
    }

    @Override
    public boolean isMessagePresent() {
        return web.isElementPresent(ERROR_MESSAGE);
    }
}
