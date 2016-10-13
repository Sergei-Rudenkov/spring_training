package com.rudenkov.epam.bean_processor;

import com.rudenkov.epam.meta.Random;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by sergei-rudenkov on 4.10.16.
 */
public class RandomAnnotationBeanProcessor implements BeanPostProcessor {
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 5;

    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        java.util.Random rn = new java.util.Random();
        int i = rn.nextInt(MAX_NUM - MIN_NUM) + MIN_NUM;
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field f : fields) {
            Random annotation = f.getAnnotation(Random.class);
            if (annotation != null) {

                f.setAccessible(true);
                ReflectionUtils.setField(f, bean, i);

            }
        }
        return bean;
    }
}