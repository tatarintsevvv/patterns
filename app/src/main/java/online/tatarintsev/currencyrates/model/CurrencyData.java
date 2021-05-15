package online.tatarintsev.currencyrates.model;

public class CurrencyData {
    String userId;
    int id;
    String title;
    boolean completed;

    public String getFormattedInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("Title: " + title + "\n\n");
        builder.append("UserId: " + userId + "\n");
        builder.append("Id: " + id + "\n");
        builder.append("Completed: " + completed + "\n");

        return new String(builder);
    }
}
