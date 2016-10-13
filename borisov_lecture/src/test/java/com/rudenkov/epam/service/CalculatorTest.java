package com.rudenkov.epam.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by sergei-rudenkov on 4.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    public void assertInjected() throws InterruptedException {


    }
}
