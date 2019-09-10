package simpleforum.dao;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDAO {

    private SimpleJdbcTemplate jdbcTemplate;

    public AccountDAO(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account getAccountByUsername(String username) {
        String query = "SELECT * FROM accounts WHERE username=:username";
        ParameterizedRowMapper<Account> rowMapper = new ParameterizedRowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new Account(id, firstName, lastName, username, password);
            }
        };
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        List<Account> list = jdbcTemplate.query(query, rowMapper, param);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

}
