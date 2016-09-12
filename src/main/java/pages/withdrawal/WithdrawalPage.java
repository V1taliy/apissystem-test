package pages.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class WithdrawalPage extends Page {

    private static final Logger log = Logger.getLogger(WithdrawalPage.class);

    public WithdrawalPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Input data in customer ID field from filter entity
     *
     * @param customerID customer id
     */
    public void inputCustomerID(String customerID) {
        web.clearAndInput("filterFieldCustomerID", customerID);
    }

    public void deleteAllBrands() {
        List<WebElement> brandsNamesList = web.getElements("filterBrandsNames");
        List<WebElement> brandsClosesList = web.getElements("filterCloseBrands");
        List<WebElement> brandsList = web.getElements("filterBrandsCount");
        final int BRANDS_COUNT = brandsList.size();
        for (int i = BRANDS_COUNT; i > 0; i--) {
            log.info(String.format("delete brand < %s >", brandsNamesList.get(i - 1).getText()));
            brandsClosesList.get(i - 1).click();
        }
    }

}
