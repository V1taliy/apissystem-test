package pages.brands.popup;

import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class EditBrand extends Page {

    public EditBrand(WebDriverWrapper driverWrapper) {
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
        web.selectCheckBox("checkboxEnabled");
    }

    /**
     * Click on button 'Save' or 'Cancel'
     *
     * @param selectButton where
     *                     true - 'Save' button
     *                     false - 'Cancel' button
     */
    public void clickSaveOrCancelButton(boolean selectButton) {
        List<WebElement> buttonsList = web.getElements("createBrandButtons");
        if (selectButton) {
            buttonsList.get(0).click();
        } else {
            buttonsList.get(1).click();
        }
    }

    public boolean isErrorMessagePresent() {
        return web.isElementPresent("errorMessage");
    }

}
