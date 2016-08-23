package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class PopupWindow extends Page {

    private static final Logger log = Logger.getLogger(PopupWindow.class);

    public PopupWindow(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Check is pop up window displayed on a page
     */
    public boolean isPopupPresent() {
        return web.isElementPresent("popupHeader");
    }

}
