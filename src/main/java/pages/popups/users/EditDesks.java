package pages.popups.users;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.popups.MainPopup;
import pages.popups.deskExpirationTime.Edit;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class EditDesks extends MainPopup {

    private static final Logger log = Logger.getLogger(Edit.class);

    public EditDesks(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Click on brand field
     */
    public void clickOnBrandField() {
        web.clickLink("editDesksBrandSelect");
    }

    public String getBrandNameFromBrandField() {
        return web.getElement("editDesksBrandSelect").getText();
    }

    /**
     * Select brand from drop down list and click on this brand
     *
     * @param brandPosition brand position on drop down list
     * @return true if brand select, otherwise false
     */
    public String selectBrandFromDropDownList(int brandPosition) {
        List<WebElement> brandsList = web.getElements("editDesksBrandsList");
        String brandName = null;
        if (brandsList.size() == 1) {
            brandName = brandsList.get(0).getText();
            log.info(String.format("select brand < %s >", brandName));
            brandsList.get(0).click();
        } else {
            brandName = brandsList.get(brandPosition - 1).getText();
            log.info(String.format("select brand < %s >", brandName));
            brandsList.get(brandPosition - 1).click();
        }
        return brandName;
    }

    /**
     * Wait for desk list to be active
     */
    public void waitForDeskToBeActive() {
        WebElement element = web.getElement("editDesksDeskSelect");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout10sec")));
        wait.until(ExpectedConditions.attributeContains(element, "disabled", ""));
    }

    /**
     * Click on desk field
     */
    public void clickOnDeskField() {
        web.clickLink("editDesksDeskSelect");
    }

    /**
     * Select desk from drop down list and click on this desk
     *
     * @param deskPosition desk position on drop down list
     */
    public String selectDeskFromDropDownList(int deskPosition) {
        List<WebElement> desksList = web.getElements("editDesksDesksList");
        String deskName = null;
        if (desksList.size() == 1) {
            deskName = desksList.get(0).getText();
            log.info(String.format("select desk < %s >", deskName));
            desksList.get(0).click();
        } else {
            deskName = desksList.get(deskPosition - 1).getText();
            log.info(String.format("select desk < %s >", deskName));
            desksList.get(deskPosition - 1).click();
        }
        return deskName;
    }

    /**
     * Click on 'Add' button on pop up window
     */
    public void clickAddButton() {
        web.clickButton("editDesksButtonAdd");
    }

    /**
     * Return text from added brand
     *
     * @param textValue where
     *                  brand - brand name
     *                  desk - desk name
     * @return text from added brand
     */
    public String getAddedBrandText(String textValue) {
        List<WebElement> list = web.getElements("editDesksAddDeskList");
        String text = null;
        if (textValue.equals("brand")) {
            text = list.get(0).getText();
            log.info(String.format("brand name = < %s >", text));
        } else if (textValue.equals("desk")) {
            text = list.get(1).getText();
            log.info(String.format("desk name = < %s >", text));
        }
        return text;
    }

    /**
     * Click button 'Remove' in pop up 'Edit desks'
     */
    public void clickButtonRemove() {
        web.clickButton("editDesksButtonRemove");
    }

}
