package com.rudenkov.epam.service.impl;

import com.rudenkov.epam.meta.PostProxy;
import com.rudenkov.epam.meta.Profiling;
import com.rudenkov.epam.meta.Random;
import com.rudenkov.epam.service.Calculator;

import javax.annotation.PostConstruct;

/**
 * Created by sergei-rudenkov on 4.10.16.
 */
@Profiling
public class CalculatorImpl implements Calculator {

    @Random
    private int myRandom;

    public CalculatorImpl() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
    }

    @PostProxy
    public void calculate(){
        System.out.println("Phase 3");
        for (int i = 0; i <  myRandom; i++) {
            System.out.println("hello!");
        }
    }
}
