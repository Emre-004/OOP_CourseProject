
/**
 * Клас, представляващ местоположение на продукт в склада.
 * Състои се от секция, рафт и номер.
 */
public class Location {
    private String section;
    private String shelf;
    private String number;

    /**
     * Създава инстанция на Location с посочени секция, рафт и номер.
     */
    public Location(String section, String shelf, String number) {
        this.section = section;
        this.shelf = shelf;
        this.number = number;
    }

    /**
     * Връща текстово представяне на местоположението във формат "секция/рафт/номер".
     */
    public String toString() {
        return section + "/" + shelf + "/" + number;
    }
}
