package pages.entity;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class ListEntity extends Page {

    private static final Logger log = Logger.getLogger(ListEntity.class);

    public ListEntity(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public int getListResult() {
        List<WebElement> list = web.getElements("noMatchingRecords");
        int result = 0;
        if (list.size() == 1) {
            // No matching records found
            log.info(String.format(list.get(0).getText()));
            result = list.size();
        } else {
            result = list.size();
        }
        return result;
    }

    /**
     * Get text 'No matching records found.'
     */
    public String getTextAboutNoResult() {
        return web.getElement("noMatchingRecords").getText();
    }

}
