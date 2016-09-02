package pages.popups.brands;

import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class DeleteBrand extends Page {

    public DeleteBrand(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public boolean isPopupDeleteBrandPresent() {
        return web.isElementPresent("popupDeleteBrand");
    }

    /**
     * Click on button 'Cancel' or 'Yes, delete it'
     *
     * @param buttonStatus where
     *                     true - 'Yes, delete it' button
     *                     false - 'Cancel' button
     */
    public void clickButtonCancelOrYes(boolean buttonStatus) {
        List<WebElement> buttonsList = web.getElements("popupDeleteBrandButtons");
        if (buttonStatus) {
            buttonsList.get(1).click();
        } else {
            buttonsList.get(0).click();
        }
    }

    public boolean waitPopupLoaded() {
        return web.waitElementToBeVisibility("popupDeleteBrand");
    }

    public boolean waitInvisibilityPopup() {
        return web.waitDisappearElement("popupDeleteBrand");
    }

}
