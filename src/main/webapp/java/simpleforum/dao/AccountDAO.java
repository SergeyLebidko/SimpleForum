package simpleforum.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDAO {

    private JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account getAccountByUsername(String username){
        return null;
    }

}
