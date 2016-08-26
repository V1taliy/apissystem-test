package pages.entity;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class FilterEntity extends Page {

    private static final Logger log = Logger.getLogger(FilterEntity.class);

    public FilterEntity(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
