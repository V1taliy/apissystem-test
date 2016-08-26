package pages.popups.groups;

import org.apache.log4j.Logger;
import utils.WebDriverWrapper;

public class EditGroup extends CreateGroup {

    private static final Logger log = Logger.getLogger(EditGroup.class);

    public EditGroup(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
