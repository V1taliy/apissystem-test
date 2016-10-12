package pages.popups.brands;

import pages.popups.MainPopup;
import utils.WebDriverWrapper;

public class CreateBrand extends MainPopup {

    public CreateBrand(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Input data in someone of the fields
     *
     * @param inputData input data in someone fields
     * @param fieldName someone fields name, where
     *                  AppKey - app key field
     *                  ApiUser - api user filed
     *                  ApiPassword - api password field
     *                  BrandName - brand name field
     *                  Domain - domain field
     */
    public void inputField(String fieldName, String inputData) {
        web.clearAndInput(String.format("input%s", fieldName), inputData);
    }

    public void selectCheckboxEnable() {
        web.clickLink("checkboxEnable");
    }

    public boolean isErrorMessagePresent() {
        return web.isElementPresent("editUserErrorMessage");
    }

}
