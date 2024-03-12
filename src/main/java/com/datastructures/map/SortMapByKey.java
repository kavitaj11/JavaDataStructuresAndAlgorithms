package com.datastructures.map;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class SortMapByKey {

    public Map<String, Integer> getMapSortedByKeys(Map<String, Integer> unSortedMap) {

        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        unSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }


    private static void sortByKeyUsingTreeMap(Map<String, Integer> unSortedMap )
    {
        Map<String, Integer> sortedMap = new TreeMap<String, Integer>(unSortedMap);

        System.out.println("Sorted Map   : " + sortedMap);

        Map<String, Integer> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());
        reverseSortedMap.putAll(unSortedMap);

        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
    }
}
