package ua.epam.spring.hometask.service;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.service_interfaces.IDiscountService;

import static org.junit.Assert.assertEquals;

/**
 * Created by sergei_rudenkov on 28.3.16.
 */
public class DiscountServiceTest extends BaseTest {

    private static final int QUANTITY_OF_SEATS = 1;
    private static final double DELTA = 0.0;
    private static final double BIRTHDAY_DISCOUNT = 5.0;
    private static IDiscountService discountService;
    private static User testUser;
    private static Event testEvent;


    @BeforeClass
    public static void init(){
        testUser = (User) context.getBean("test_user1");
        testEvent = (Event) context.getBean("test_event1");
        discountService = (IDiscountService) context.getBean("discount_service");
    }

    @Test
    public void testBirthdayDiscount(){
        assertEquals(BIRTHDAY_DISCOUNT, discountService.getDiscount(testUser, testEvent, testEvent.getAirDates().stream().findFirst().get(), QUANTITY_OF_SEATS), DELTA);
    }
}
