package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergei_rudenkov on 4/9/16.
 */
@Component
public class CallCounterDao {

    static final String GET_OCCURRENCES = "select * from CALL_COUNTER where name = ?";
    static final String SET_OCCURRENCES = "update CALL_COUNTER set occurrences=? where name = ?";
    static final String INSERT = "insert into CALL_COUNTER values(?, ?)";
    static final String GET_ALL = "select * from CALL_COUNTER";


    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String, Integer> getAll() {
        Map<String, Object> result = jdbcTemplate.queryForMap(GET_ALL);
        Map<String, Integer> result2 = new HashMap<String, Integer>();
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            result2.put(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
        }
        return result2;
    }

    public int get(String eventName) {
        int nOccurrences = (int)jdbcTemplate.queryForObject(
                GET_OCCURRENCES, new Object[] { eventName },
                new BeanPropertyRowMapper(Integer.class));
        return nOccurrences;
    }

    public void put(String name, int occurrences){
        Object[] values = {name, occurrences};
        int[] types = {Types.INTEGER, Types.VARCHAR};
        jdbcTemplate.update(SET_OCCURRENCES, values, types);
    }
}
