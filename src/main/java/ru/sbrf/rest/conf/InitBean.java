package ru.sbrf.rest.conf;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class InitBean implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field field : declaredFields) {
            LoggerAutowired annotation = field.getAnnotation(LoggerAutowired.class);
            if (Objects.nonNull(annotation)){
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, bean, new CustomLogger(LoggerFactory.getLogger(beanName)));
            }
        }

        return bean;
    }



}
