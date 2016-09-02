package pages.popups.adminMenu;

import org.apache.log4j.Logger;
import pages.popups.MainPopup;
import utils.WebDriverWrapper;

public class EditMenu extends MainPopup {

    private static final Logger log = Logger.getLogger(EditMenu.class);

    public EditMenu(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
