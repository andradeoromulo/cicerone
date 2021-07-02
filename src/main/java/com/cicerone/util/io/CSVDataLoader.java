package com.cicerone.util.io;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public final class CSVDataLoader {

    private CSVDataLoader() {
    }

    public static <T> List<T> load(String fileName, Function<String[], T> dataHandler) {

        List<T> dataList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {

            scanner.nextLine();

            while (scanner.hasNext()) {

                String[] properties = scanner.nextLine().split(",");

                var processedObject = dataHandler.apply(properties);
                dataList.add(processedObject);

            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Error while loading CSV file data: %s.", e.getMessage()));
        }

        return dataList;

    }

}
