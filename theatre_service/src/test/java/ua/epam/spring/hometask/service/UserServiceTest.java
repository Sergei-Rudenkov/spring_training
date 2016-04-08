package ua.epam.spring.hometask.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.service_interfaces.IUserService;

import java.util.NoSuchElementException;


/**
 * Created by sergei_rudenkov on 28.3.16.
 */
public class UserServiceTest extends BaseTest {

    private static final String TEST_USER_EMAIL = "s@email.com";
    private static final long TEST_USER_ID = 1;
    private static IUserService userService;
    private static User testUser;

    @BeforeClass
    public static void init(){
        testUser = (User) context.getBean("test_user1");
        userService = (IUserService) context.getBean("user_service");
    }

    @Test
    public void testUserSave() {
        userService.save(testUser);
        assertEquals(userService.getUserByEmail(TEST_USER_EMAIL), testUser);
    }

    @Test(expected = NoSuchElementException.class)
    public void testUserRemove(){
        userService.save(testUser);
        userService.remove(testUser);
        assertNull(userService.getUserByEmail(TEST_USER_EMAIL));
    }

    @Test
    public void testGetById(){
        userService.save(testUser);
        assertEquals(userService.getById(TEST_USER_ID), testUser);

    }

    @After
    public void cleanUp(){
        userService.remove(testUser);
    }
}
