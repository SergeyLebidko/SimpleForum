package simpleforum.dao;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    public List<Topic> getAllTopics(){
        String query = "SELECT * FROM topics ORDER BY date_added";
        ParameterizedRowMapper<Topic> rowMapper = new ParameterizedRowMapper<Topic>() {
            @Override
            public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime dateAdded = resultSet.getObject("date_added", LocalDateTime.class);
                String headerText = resultSet.getString("header_text");
                return new Topic(id, userId, dateAdded, headerText);
            }
        };
        return jdbcTemplate.query(query, rowMapper);
    }

}
