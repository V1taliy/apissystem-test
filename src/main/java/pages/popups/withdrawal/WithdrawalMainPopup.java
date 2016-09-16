package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.popups.MainPopup;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

public class WithdrawalMainPopup extends MainPopup {

    private static final Logger log = Logger.getLogger(WithdrawalMainPopup.class);

    public WithdrawalMainPopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    @Override
    public boolean clickButtonSaveOrCancel(boolean button) {
        return clickButtonOkOrCancel(button);
    }

    /**
     * Click on button 'OK' or 'Cancel'
     *
     * @param button button status, where
     *               true - click button 'OK'
     *               false - click button 'Cancel'
     */
    private boolean clickButtonOkOrCancel(boolean button) {
        if (button) {
            log.info("click on button 'OK'");
            waitButtonOkToBeClickable();
            web.clickButton("assignButtonOK");
            return true;
        } else {
            log.info("click on button 'Cancel'");
            web.clickButton("buttonCancel");
            return true;
        }
    }

    /**
     * Private method for wait clickable button 'OK'
     */
    private void waitButtonOkToBeClickable() {
        WebElement buttonOK = web.getElement("assignButtonOK");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout5sec")));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOK));
    }

    @Override
    public boolean waitPopupLoaded() {
        return web.waitElementToBeVisibility("popupAssignLoaded");
    }

}
