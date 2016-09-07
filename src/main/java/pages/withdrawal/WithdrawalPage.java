package pages.withdrawal;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class WithdrawalPage extends Page {

    private static final Logger log = Logger.getLogger(WithdrawalPage.class);

    public WithdrawalPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
