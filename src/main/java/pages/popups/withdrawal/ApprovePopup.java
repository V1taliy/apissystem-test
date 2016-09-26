package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class ApprovePopup extends WithdrawalMainPopup {

    private static final Logger log = Logger.getLogger(ApprovePopup.class);

    public ApprovePopup(WebDriverWrapper driverWrapper) {
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

    /**
     * Get text from group field, where must be only one group status - 'Finance'
     */
    public String getGroupStatus() {
        return web.getElement("groupList").getText();
    }

    /**
     * Select and click radio button 'Keep bonus' or 'Remove bonus'
     *
     * @param radioButtonPosition radio button position on pop up 'Approve', where
     *                            0 - Keep bonus
     *                            1 - Remove bonus
     */
    public void selectBonusRadioButton(int radioButtonPosition) {
        List<WebElement> radioButtonList = web.getElements("bonusList");
        log.info(String.format("select < %s > radio button",
                radioButtonList.get(radioButtonPosition)
                        .findElement(By.cssSelector("span.show-inline")).getText()));
        radioButtonList.get(radioButtonPosition)
                .findElement(By.cssSelector("input[class*=bonus-checkbox-input]"))
                .click();
    }

    /**
     * Input data to 'Confirm code' field
     *
     * @param confirmCode
     */
    public void inputConfirmCode(String confirmCode) {
        web.clearAndInput("confirmCodeField", confirmCode);
    }

    public void clickOnMethod() {
        web.getElement("methodField").click();
    }

    /**
     * Select method from drop down list on 'Approve' pop up
     *
     * @param methodName method name, from drop down list, where
     *                   ccTerminal
     *                   Wire
     *                   CreditCard
     *                   Pending
     *                   Chargeback
     *                   Bonus
     *                   Withdrawal
     *                   Fraund
     */
    public void selectMethod(String methodName) {
        List<WebElement> methodsList = web.getElements("methodList");
        for (WebElement method : methodsList) {
            if (method.getText().equals(methodName)) {
                log.info(String.format("select < %s >", method.getText()));
                method.click();
                return;
            }
        }
    }

}
