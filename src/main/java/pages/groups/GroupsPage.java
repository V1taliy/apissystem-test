package pages.groups;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class GroupsPage extends Page {

    private static final Logger log = Logger.getLogger(GroupsPage.class);

    public GroupsPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
