package utils;

public final class Validation {

    private Validation() {
    }

    public static void isNotBlankString(String property, String propertyName) {

        String message = String.format("%s must not be null nor empty", propertyName);

        if(property == null || property.isEmpty())
            throw new IllegalArgumentException(message);
    }

    public static void isValidSlug(String property, String propertyName) {

        String message = String.format("%s must not be null nor empty", propertyName);

        if(!property.matches("^[a-z]+[a-z-]*[a-z]+$"))
            throw new IllegalArgumentException(message);

    }

    public static void isIntegerBetweenRange(Integer property, Integer beggining, Integer end, String propertyName) {

        String message = String.format("%s must be between %d and %d", propertyName, beggining, end);

        if(property < beggining || property > end)
            throw new IllegalArgumentException(message);

    }

}
