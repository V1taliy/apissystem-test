package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import utils.WebDriverWrapper;

public class DeclinePopup extends WithdrawalMainPopup {

    private static final Logger log = Logger.getLogger(DeclinePopup.class);

    public DeclinePopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
