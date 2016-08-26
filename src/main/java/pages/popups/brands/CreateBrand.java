package pages.popups.brands;

import pages.popups.MainPopup;
import utils.WebDriverWrapper;

public class CreateBrand extends MainPopup {

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

    public void selectCheckboxEnable() {
        web.clickLink("checkboxEnable");
    }

    public boolean isErrorMessagePresent() {
        return web.isElementPresent("errorMessage");
    }

}
