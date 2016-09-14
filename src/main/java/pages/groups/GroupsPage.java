package pages.groups;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.List;

public class GroupsPage extends Page {

    private static final Logger log = Logger.getLogger(GroupsPage.class);

    public GroupsPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Select group by index
     *
     * @param groupPosition group index
     */
    public void selectGroup(int groupPosition) {
        List<WebElement> groupsList = web.getElements("groupsList");
        log.info(String.format("select group < %s >",
                groupsList.get(groupPosition - 1).getText()));
        groupsList.get(groupPosition - 1).click();
    }

    /**
     * Select group by name
     *
     * @param groupName group name
     */
    public void selectGroup(String groupName) {
        List<WebElement> groupsList = web.getElements("groupsList");
        for (WebElement group : groupsList) {
            if (group.getText().equals(groupName)) {
                log.info(String.format("select group < %s >", group.getText()));
                group.click();
                return;
            }
        }
    }

    /**
     * Click on button 'Save'
     */
    public void clickButtonSave() {
        web.getElement("groupButtonSave").click();
    }

}
