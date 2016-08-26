package pages.popups.groups;

import org.apache.log4j.Logger;
import pages.popups.MainPopup;
import utils.WebDriverWrapper;

public class CreateGroup extends MainPopup {

    private static final Logger log = Logger.getLogger(CreateGroup.class);

    public CreateGroup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
