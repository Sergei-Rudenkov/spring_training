package ua.epam.spring.hometask.service;


import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Created by sergei_rudenkov on 28.3.16.
 */
public abstract class BaseTest {

    protected static ApplicationContext context;

    @BeforeClass
    public static void prepareContext(){
        context = new ClassPathXmlApplicationContext("test_config.xml");
    }
}
