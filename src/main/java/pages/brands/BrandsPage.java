package pages.brands;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class BrandsPage extends Page {

    private static final Logger log = Logger.getLogger(BrandsPage.class);

    public BrandsPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public void filterInputBrandName(String brandName) {
        web.clearAndInput("filterBrandNameInput", brandName);
    }

    /**
     * Click enabled field and select 'Enabled' or 'Disabled'
     *
     * @param status where
     *               true - enabled
     *               false - disabled
     * @info elementList.get(0) - Select (don't click)
     */
    public void clickAndSelectEnabled(boolean status) {
        web.clickLink("filterEnabledLink");
        List<WebElement> elementList = web.getElements("filterEnabledList");
        if (status) {
            elementList.get(1).click();
        } else {
            elementList.get(2).click();
        }
    }

    /**
     * Click on button 'Search' or 'Reset'
     *
     * @param button where
     *               true - Search
     *               false - Reset
     */
    public void clickSearchOrReset(boolean button) {
        List<WebElement> buttonsList = web.getElements("filterButtonList");
        if (button) {
            buttonsList.get(0).click();
        } else {
            buttonsList.get(1).click();
        }
    }

}
