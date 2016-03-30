package ua.epam.spring.hometask.dao;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import ua.epam.spring.hometask.domain.Auditorium;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
public class AuditoriumDao {

    public Set<Auditorium> audiences = new HashSet<>();

    public Set<Auditorium> getAll() {
        return audiences;
    }

    public void remove(Auditorium auditorium){
        audiences.remove(auditorium);
    }

    public void put(Auditorium auditorium){
        audiences.add(auditorium);
    }

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
