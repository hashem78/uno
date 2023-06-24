package org.hashem.uno.engine;

public record Color(String color) {
    final public static Color Red = new Color("Red");
    final public static Color Green = new Color("Green");
    final public static Color Blue = new Color("Blue");
    final public static Color Yellow = new Color("Yellow");
    final public static Color NoColor = new Color("");
}