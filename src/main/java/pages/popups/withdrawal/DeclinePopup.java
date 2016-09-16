package pages.popups.withdrawal;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class DeclinePopup extends WithdrawalMainPopup {

    private static final Logger log = Logger.getLogger(DeclinePopup.class);

    public DeclinePopup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public void clickOnUserField() {
        web.clickLink("userFieldLink");
    }

    public void clickOnReasonField() {
        web.clickLink("reasonFieldLink");
    }

    /**
     * Select user from drop down list on user field
     *
     * @param userName user name on drop down list
     */
    public void selectUserFromUserField(String userName) {
        List<WebElement> usersList = web.getElements("usersList");
        for (WebElement user : usersList) {
            if (user.getText().equals(userName)) {
                log.info(String.format("select < %s >", user.getText()));
                user.click();
            }
        }
    }

    /**
     * Select reason value from drop down list on reason field
     *
     * @param reasonValue reason value on drop down list
     */
    public void selectReasonValueFromReasonField(String reasonValue) {
        List<WebElement> reasonList = web.getElements("reasonFieldList");
        for (WebElement reason : reasonList) {
            if (reason.getAttribute("value").equals(reasonValue)) {
                log.info(String.format("select < %s >", reason.getText()));
                reason.click();
            }
        }
    }

}
