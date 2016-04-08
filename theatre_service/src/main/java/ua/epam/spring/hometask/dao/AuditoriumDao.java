package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
@Component
public class AuditoriumDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    static final String DELETE_QUERY = "delete from AUDITORIUM where id = ?";
    static final String INSERT_QUERY = "insert into AUDITORIUM values(?, ?, ?, ?)";
    static final String GET_ALL_QUERY = "select * from AUDITORIUM";

    public Set<Auditorium> getAll() {
        return new HashSet<>(jdbcTemplate.query(GET_ALL_QUERY, (rs, rowNum) -> {
            Auditorium auditorium = new Auditorium();
            auditorium.setId(rs.getLong(1));
            auditorium.setName(rs.getString(2));
            auditorium.setNumberOfSeats(Long.parseLong(rs.getString(3)));
            auditorium.setVipSeats(LongStream.rangeClosed(1, rs.getLong(4)).boxed().collect(Collectors.toSet()));
            return auditorium;
        }));
    }

    public void remove(Auditorium auditorium){
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(DELETE_QUERY);
            statement.setLong(1, auditorium.getId());
            return statement;
        });
    }

    public void put(Auditorium auditorium){
        Object[] values = {auditorium.getId(), auditorium.getName(), auditorium.getAllSeats().size(), auditorium.getVipSeats().size()};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        jdbcTemplate.update(INSERT_QUERY, values, types);
    }

    @PostConstruct
    public void init() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("auditoriums/*.properties");

        for(Resource resource : resources) {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("auditoriums/" + resource.getFilename());

            String name = properties.getProperty("name");
            String numberOfSeats = properties.getProperty("numberOfSeats");
            List<String> vipSeats = Arrays.asList(properties.getProperty("vipSeats", "").split(","));

            if(name != null) {
                Auditorium auditorium = new Auditorium();
                auditorium.setName(name);
                auditorium.setNumberOfSeats(Long.parseLong(numberOfSeats));

                Set<Long> vipSeatsSet = vipSeats.stream()
                        .mapToLong(Long::parseLong)
                        .boxed()
                        .collect(Collectors.toSet());

                auditorium.setVipSeats(vipSeatsSet);

                put(auditorium);
            }
        }
    }
}
