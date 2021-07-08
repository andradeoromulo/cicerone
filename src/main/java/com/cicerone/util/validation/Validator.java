package com.cicerone.util.validation;

public final class Validator {

    private Validator() {
    }

    public static void isNotBlankString(String property) {

        String message = "Property must not be null nor empty";
        if(property == null || property.isEmpty()) throw new IllegalArgumentException(message);

    }

    public static void isNotNullObject(Object object) {

        String message = "Object must not be null";
        if(object == null) throw new IllegalArgumentException(message);

    }

    public static void isValidCode(String property) {

        String message = "Code can only contain low case letters and hyphens";
        if(!property.matches("^[a-z]+[a-z-]*[a-z]+$")) throw new IllegalArgumentException(message);

    }

    public static void isIntegerBetweenRange(Integer property, Integer beggining, Integer end) {

        String message = String.format("Value must be between %d and %d", beggining, end);
        if(property < beggining || property > end) throw new IllegalArgumentException(message);

    }

}
