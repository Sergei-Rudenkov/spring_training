package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
@Component
public class UserDao {

    static final String DELETE_QUERY = "delete from THEATRE_USER where id = ?";
    static final String INSERT_QUERY = "insert into THEATRE_USER values(?, ?, ?, ?, ?)";
    static final String GET_ALL_QUERY = "select * from THEATRE_USER";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Set<User> getAll() {
        return new HashSet<User>(jdbcTemplate.query(GET_ALL_QUERY, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setEmail(rs.getString(4));
            if(rs.getDate(5) != null) {
                user.setBirthday(rs.getDate(5).toLocalDate());
            }
            return user;
        }));
    }


    public void remove(User user){
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(DELETE_QUERY);
            statement.setLong(1, user.getId());
            return statement;
        });
    }

    public void put(User user){
        Object[] values = {user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthday()};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE};
        jdbcTemplate.update(INSERT_QUERY, values, types);
    }
}




//    CREATE TABLE THEATRE_USER(
//        ID INT PRIMARY KEY     NOT NULL,
//        FIRST_NAME     TEXT    NOT NULL,
//        LAST_NAME      TEXT    NOT NULL,
//        EMAIL          TEXT,
//        BIRTHDAY       DATE
//);