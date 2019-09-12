package simpleforum.dao;

import java.time.LocalDateTime;

public class Record {

    private int id;
    private int userId;
    private int topicId;
    private LocalDateTime dateAdded;
    private String content;

    public Record(int id, int userId, int topicId, LocalDateTime dateAdded, String content) {
        this.id = id;
        this.userId = userId;
        this.topicId = topicId;
        this.dateAdded = dateAdded;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getContent() {
        return content;
    }
    
}
