package com.circron.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Random;
import java.util.UUID;
public class RandomGeneration {
    static Field field;

    RandomGeneration(Field field) {
        RandomGeneration.field = field;
    }

    public static <T> T getRandomForField() {
        switch(field.getType().toString()) {
            case "String":
                return (T)getRandomString();
            case "Integer":
                return (T)getRandomInteger();
            default:
                return null;
        }

    }

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Integer getRandomInteger() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
