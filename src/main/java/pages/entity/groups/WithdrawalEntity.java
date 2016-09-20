package pages.entity.groups;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

public class WithdrawalEntity extends Page {

    private static final Logger log = Logger.getLogger(WithdrawalEntity.class);

    public WithdrawalEntity(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Click on checkbox 'view all' from withdrawal entity
     *
     * @param checkboxStatus checkbox status, where
     *                       true - select checkbox
     *                       false - deselect checkbox
     */
    public void selectDeselectCheckBoxViewAll(boolean checkboxStatus) {
        WebElement element = web.getElement("checkBoxViewAll");
        if (checkboxStatus) {
            if (!element.isSelected()) {
                log.info(String.format("checkbox < %s > not selected",
                        element.getAttribute("data-field-name")));
                element.click();
                log.info(String.format("ckeckbox < %s > is selected",
                        element.getAttribute("data-field-name")));
            } else {
                log.info(String.format("checkbox < %s > is selected",
                        element.getAttribute("data-field-name")));
            }
        } else {
            if (element.isSelected()) {
                log.info(String.format("checkbox < %s > is selected",
                        element.getAttribute("data-field-name")));
                element.click();
                log.info(String.format("deselect checkbox < %s >",
                        element.getAttribute("data-field-name")));
            } else {
                log.info(String.format("checkbox < %s > not selected",
                        element.getAttribute("data-field-name")));
            }
        }

    }

}
