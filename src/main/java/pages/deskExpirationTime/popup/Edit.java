package pages.deskExpirationTime.popup;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.testng.internal.Nullable;
import pages.Page;
import pages.brands.popup.CreateBrand;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

public class Edit extends CreateBrand {

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

    public boolean isPopupNotPresent() {
        if (web.getElementWithTimeout("createBrand", 1) == null) {
            return false;
        } else {
            log.info("popup is present.");
            return true;
        }
    }

}
