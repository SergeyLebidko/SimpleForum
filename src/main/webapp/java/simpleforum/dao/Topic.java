package simpleforum.dao;

import java.time.LocalDateTime;

public class Topic {

    private int id;
    private int userId;
    private LocalDateTime dateAdded;
    private String headerText;

    public Topic(int id, int user_id, LocalDateTime dateAdded, String headerText) {
        this.id = id;
        this.userId = user_id;
        this.dateAdded = dateAdded;
        this.headerText = headerText;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getHeaderText() {
        return headerText;
    }

}
