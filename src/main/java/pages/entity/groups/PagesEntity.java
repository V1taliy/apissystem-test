package pages.entity.groups;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class PagesEntity extends Page {

    private static final Logger log = Logger.getLogger(PagesEntity.class);

    public PagesEntity(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Click on checkbox 'withdrawal' from pages entity
     */
    public void selectCheckBoxWithdrawal() {
        String checkBoxClass = "fa pages-check fa-tree-check";
        if (!web.getElement("checkBoxWithdrawal").getAttribute("class").equals(checkBoxClass)) {
            log.info(String.format("checkbox \'withdrawal\' not selected. Select \'withdrawal\'"));
            web.getElement("checkBoxWithdrawal").click();
            log.info(String.format("click on checkbox \'withdrawal\' and checkbox is selected"));
            return;
        }
        log.info(String.format("checkbox withdrawal is selected"));
    }

}
