package com.company.lessons_average.Search_multiple_values;

import java.util.List;

public class Search {
    public static void main(String[] args) {
        PointsCollection places = createCollection(10000);
        System.out.println("Collections created");
        String seq = "1";

        System.out.format("Searching for: \"%s\"\n", seq);
        List<Points> verifySearch = verifySearch(places, seq);
        //show(verifySearch);
    }

    private static void show(List<Points> verifySearch) {
        int i = 1;
        for (Points p : verifySearch) {
            System.out.println(i + ": " + p.name + ", " + p.address);
            i++;
        }
    }

    private static List<Points> verifySearch(PointsCollection places, String seq) {
        long start = System.currentTimeMillis();
        List<Points> searchResult = places.findIdsContaining(seq);
        System.out.println("Search results: " + searchResult.size());
        long end = System.currentTimeMillis();
        System.out.println("Operation time: " + formatTime(end - start));
        return searchResult;
    }

    private static String formatTime(long elapsed) {
        return elapsed + " miliseconds";
    }

    private static PointsCollection createCollection(int number) {
        PointsCollection coll = new PointsCollection();
        while (number > 0) {
            coll.add(createSamplePoint(number));
            number--;
        }
        return coll;
    }

    private static Points createSamplePoint(int number) {
        Points p = new Points();
        p.name = "VeryVeryLongName: " + number;
        p.address = "VeryVeryLongLongAddress: " + number;
        p.coord1 = 123;
        p.coord2 = 456;
        return p;
    }
}