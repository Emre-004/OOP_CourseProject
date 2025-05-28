

/**
 * Клас, представляващ лог запис за действие, извършено с продукт.
 * Всеки запис съдържа действие, продукт и времеви печат.
 */
public class LogEntry {
    private String action;
    private Product product;
    private String timestamp;

    /**
     * Създава инстанция на LogEntry с действие, продукт и времеви печат.
     */
    public LogEntry(String action, Product product, String timestamp) {
        this.action = action;
        this.product = product;
        this.timestamp = timestamp;
    }

    /**
     * Връща текстово представяне на лог записа.
     */
    public String toString() {
        return timestamp + " - " + action + ": " + product;
    }

    /**
     * Връща времевия печат на записа.
     */
    public String getTimestamp() {
        return timestamp;
    }
}
