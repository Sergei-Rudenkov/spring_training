package com.epam.springdata_example;

import com.epam.springdata_example.repository.MyRepository;
import com.epam.springdata_example.spring_configuration.SpringConfig;
import com.epam.springdata_example.model.UserModel;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Autowired
    MyRepository repository;

//    @Test
//    public void setup() {
//        repository.save(new UserModel("5", "Sergei", "12345678"));
//    }

    @Test
    public void test() {
        Iterable<UserModel> document = repository.findПожалуйстаByFirstnameAndPassword("Sergei", "12345678");
        document.forEach(user -> System.out.println(user.getFirstname()));
    }
}
