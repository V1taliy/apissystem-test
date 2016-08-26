package pages.popups.deskExpirationTime;

import org.apache.log4j.Logger;
import pages.popups.MainPopup;
import utils.WebDriverWrapper;

public class Edit extends MainPopup {

    private static final Logger log = Logger.getLogger(Edit.class);

    public Edit(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Clear and input value in expiration time field
     *
     * @param inputValue new value
     */
    public void inputExpirationTime(int inputValue) {
        web.clearAndInput("editExpirationTimeField", String.valueOf(inputValue));
    }

}
