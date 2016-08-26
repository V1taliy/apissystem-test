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
        return web.isElementPresent("popupHeader");
    }

}
