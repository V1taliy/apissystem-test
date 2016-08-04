package pages.deskExpirationTime;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class DeskExpirationTime extends Page {

    private static final Logger log = Logger.getLogger(DeskExpirationTime.class);

    public DeskExpirationTime(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
