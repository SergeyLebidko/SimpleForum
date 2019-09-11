package simpleforum.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TopicDAO {

    private SimpleJdbcTemplate jdbcTemplate;

    public TopicDAO(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTopic(int userId, LocalDateTime dateAdded, String headerText){
        String query = "INSERT INTO topics (user_id, date_added, header_text) " +
                "values(:userId, make_timestamp(:year, :month, :day, :hour, :min, :sec), :headerText)";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("year", dateAdded.getYear());
        params.put("month", dateAdded.getMonthValue());
        params.put("day", dateAdded.getDayOfMonth());
        params.put("hour", dateAdded.getHour());
        params.put("min", dateAdded.getMinute());
        params.put("sec", dateAdded.getSecond());
        params.put("headerText", headerText);
        jdbcTemplate.update(query, params);
    }

}
