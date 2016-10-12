package pages.message;

public interface Message {

    /* Wait for message present on a page */
    void waitMessagePresent();

    /* Wait for invisibility message on a page*/
    void waitMessageInvisibility();

    /* Is message present on a page */
    boolean isMessagePresent();

}
