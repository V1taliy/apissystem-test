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

    /**
     * Get user index by name
     *
     * @param userName user name from data table
     */
    public int getUserNameIndex(String userName) {
        List<WebElement> userNameList = web.getElements("userNameList");
        for (int i = 0; i < userNameList.size(); i++) {
            // for debug
//            log.info(userNameList.get(i).getText());
//            log.info(String.format("user name: %s", userName));
            if (userNameList.get(i).getText().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

}
