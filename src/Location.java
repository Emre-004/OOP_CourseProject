public class Location {
    private String section;
    private String shelf;
    private String number;

    public Location(String section, String shelf, String number) {
        this.section = section;
        this.shelf = shelf;
        this.number = number;
    }

    public String toString() {
        return section + "/" + shelf + "/" + number;
    }
}
