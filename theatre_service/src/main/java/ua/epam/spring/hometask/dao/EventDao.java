package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
@Component
public class EventDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    static final String DELETE_QUERY = "delete from EVENT where id = ?";
    static final String INSERT_QUERY = "insert into EVENT values(?, ?, ?, ?)";
    static final String GET_ALL_QUERY = "select * from EVENT";

    public Set<Event> getAll() {
        return new HashSet<>(jdbcTemplate.query(GET_ALL_QUERY, (rs, rowNum) -> {
            Event event = new Event();
            event.setId(rs.getLong(1));
            event.setName(rs.getString(2));
            event.setBasePrice(rs.getLong(3));
            event.setRating(EventRating.valueOf(rs.getString(4)));
            return event;
        }));
    }

    public void remove(Event event){
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(DELETE_QUERY);
            statement.setLong(1, event.getId());
            return statement;
        });
    }

    public void put(Event event){
        Object[] values = {event.getId(), event.getName(), event.getBasePrice(), event.getRating().name()};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        jdbcTemplate.update(INSERT_QUERY, values, types);
    }
}




