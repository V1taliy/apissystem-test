package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class AssignPopup extends WithdrawalMainPopup {

    private static final Logger log = Logger.getLogger(AssignPopup.class);

    public AssignPopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public void clickOnGroupFiled() {
        web.clickLink("groupLink");
    }

    public void clickOnUserField() {
        web.clickLink("userLink");
    }

    /**
     * Select group from drop down list
     *
     * @param groupPosition group, where
     *                      1 - Manager
     *                      2 - Withdrawal Representative
     *                      3 - Withdrawal Manager
     *                      4 - Retention
     *                      5 - Retention TL
     *                      6 - Desk Manager
     *                      7 - CEO
     *                      8 - Finance
     *                      9 - Risk
     *                      10 - Compliance
     *                      11 - General
     */
    public void selectGroup(int groupPosition) {
        List<WebElement> groupsList = web.getElements("groupList");
        log.info(String.format("select group: < %s >", groupsList.get(groupPosition - 1).getText()));
        groupsList.get(groupPosition - 1).click();
    }

    /**
     * Select user from user drop down list
     *
     * @param userName user name
     */
    public void selectUser(String userName) {
        List<WebElement> usersList = web.getElements("userList");
        for (WebElement user : usersList) {
            if (user.getText().equals(userName)) {
                log.info(String.format("select user: < %s >", user.getText()));
                user.click();
            }
        }
    }

}
