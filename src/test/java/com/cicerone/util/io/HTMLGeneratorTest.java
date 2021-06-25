package com.cicerone.util.io;

public class HTMLGeneratorTest {

    public static void main(String[] args) {

        HTMLGenerator htmlGenerator = new HTMLGenerator("src/test/resources/csv", "src/test/resources/html");
        htmlGenerator.generate();

    }

}
