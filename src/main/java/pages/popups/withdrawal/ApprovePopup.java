package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class ApprovePopup extends WithdrawalMainPopup {

    private static final Logger log = Logger.getLogger(ApprovePopup.class);

    public ApprovePopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Click on 'Group' field on 'Approve' popup
     */
    public void clickOnGroup() {
        web.clickLink("groupLink");
    }

    /**
     * Select group from drop down list
     *
     * @param groupName group name from drop down list on 'Approve' popup
     */
    public void selectGroup(String groupName) {
        List<WebElement> groupsList = web.getElements("groupList");
        for (WebElement group : groupsList) {
            if (group.getText().equals(groupName)) {
                log.info(String.format("select group < %s >", group.getText()));
                group.click();
            }
        }
    }

}
