package com.m4gik.common;

import java.util.Random;

/**
 * This class is for perform genreating enums.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
class RandomEnum<E extends Enum<?>> {

    private static final Random RND = new Random();

    private final E[] values;

    public RandomEnum(Class<E> token) {
        values = token.getEnumConstants();
    }

    public E random() {
        return values[RND.nextInt(values.length)];
    }
}
