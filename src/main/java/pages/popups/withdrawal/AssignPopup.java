package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import pages.popups.MainPopup;
import utils.WebDriverWrapper;

public class AssignPopup extends MainPopup {

    private static final Logger log = Logger.getLogger(AssignPopup.class);

    public AssignPopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
