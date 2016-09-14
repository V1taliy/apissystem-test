package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.popups.MainPopup;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class AssignPopup extends MainPopup {

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

    public void clickOnAddComment() {
        web.clickLink("addCommentLink");
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

    /**
     * Input data to comment field
     *
     * @param commentText text for comment
     */
    public void inputComment(String commentText) {
        web.clearAndInput("addCommentField", commentText);
    }

    @Override
    public boolean clickButtonSaveOrCancel(boolean button) {
        return clickButtonOkOrCancel(button);
    }

    /**
     * Click on button 'OK' or 'Cancel'
     *
     * @param button button status, where
     *               true - click button 'OK'
     *               false - click button 'Cancel'
     */
    private boolean clickButtonOkOrCancel(boolean button) {
        if (button) {
            log.info("click on button 'OK'");
            waitButtonOkToBeClickable();
            web.clickButton("assignButtonOK");
            return true;
        } else {
            log.info("click on button 'Cancel'");
            web.clickButton("buttonCancel");
            return true;
        }
    }

    /**
     * Private method for wait clickable button 'OK'
     */
    private void waitButtonOkToBeClickable() {
        WebElement buttonOK = web.getElement("assignButtonOK");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout5sec")));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOK));
    }

    @Override
    public boolean waitPopupLoaded() {
        return web.waitElementToBeVisibility("popupAssignLoaded");
    }
}
