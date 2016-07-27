package pages.brands;

import org.apache.log4j.Logger;
import pages.Page;
import utils.WebDriverWrapper;

public class BrandsPage extends Page {

    private static final Logger log = Logger.getLogger(BrandsPage.class);

    public BrandsPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

}
