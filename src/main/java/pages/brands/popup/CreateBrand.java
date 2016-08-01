package pages.brands.popup;

import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class CreateBrand extends Page {

    public CreateBrand(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public void inputAppKey(String appKey) {
        web.clearAndInput("inputAppKey", appKey);
    }

    public void inputApiUser(String apiUser) {
        web.clearAndInput("inputApiUser", apiUser);
    }

    public void inputApiPassword(String apiPassword) {
        web.clearAndInput("inputApiPassword", apiPassword);
    }

    public void inputBrandName(String brandName) {
        web.clearAndInput("inputBrandName", brandName);
    }

    public void inputDomain(String domain) {
        web.clearAndInput("inputDomain", domain);
    }

    public void selectCheckboxEnabled() {
        web.clickLink("checkboxEnabled");
    }

    /**
     * Click on button 'Save' or 'Cancel'
     *
     * @param selectButton where
     *                     true - 'Save' button
     *                     false - 'Cancel' button
     */
    public boolean clickButtonSaveOrCancel(boolean selectButton) {
        List<WebElement> buttonsList = web.getElements("createBrandButtons");
        if (selectButton) {
            buttonsList.get(0).click();
            return true;
        } else {
            buttonsList.get(1).click();
            return true;
        }
    }

    public boolean isErrorMessagePresent() {
        return web.isElementPresent("errorMessage");
    }

    /**
     * Check is pop up window displayed on a page
     */
    public boolean isPopupPresent() {
        return web.isElementPresent("createBrand");
    }

    /**
     * Check is pop up window 'Edit user' loaded
     *
     * @return true if pop up window down buttons loaded, otherwise false
     */
    public boolean waitPopupLoaded() {
        return web.waitElementToBeVisibility("createBrandDown");
    }

    public boolean waitInvisibilityPopup() {
        return web.waitDisappearElement("createBrandDown");
    }

}
