package simpleforum.dao;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordDAO {

    private SimpleJdbcTemplate jdbcTemplate;

    private Map<String, Object> params;

    public RecordDAO(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        params = new HashMap<>();
    }

    public List<Record> getRecordsByTopicId(int topicId) {
        String query = "SELECT * FROM records WHERE topic_id=:topicId ORDER BY date_added";
        ParameterizedRowMapper<Record> rowMapper = new ParameterizedRowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int topicId = resultSet.getInt("topic_id");
                LocalDateTime dateAdded = resultSet.getObject("date_added", LocalDateTime.class);
                String content = resultSet.getString("content");
                return new Record(id, userId, topicId, dateAdded, content);
            }
        };
        params.clear();
        params.put("topicId", topicId);
        return jdbcTemplate.query(query, rowMapper, params);
    }

}
