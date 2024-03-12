package com.datastructures.set;

import java.util.*;

public class MathSetOperations {


    //Find non-duplicate items/ difference between two arrays:

    public Set<Integer> getSymmetricDifferenceBetweenTwoArrays(int[] array1, int[] array2) {

        Integer[] wrapper1 = Arrays.stream(array1).boxed().toArray(Integer[]::new);
        Integer[] wrapper2 = Arrays.stream(array2).boxed().toArray(Integer[]::new);

        Set<Integer> a = new HashSet<Integer>(Arrays.asList(wrapper1));
        Set<Integer> b = new HashSet<Integer>(Arrays.asList(wrapper2));

        //Using java 8
/*

        Set<Integer> a =Arrays.stream(array1).boxed().collect(Collectors.toSet());
        Set<Integer> b =Arrays.stream(array1).boxed().collect(Collectors.toSet());
*/

        return symmetricDifference(a, b);

    }

    //The name of the operation is symmetric difference
    private Set<Integer> symmetricDifference(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        for (Integer element : b) {
            // .add() returns false if element already exists
            if (!result.add(element)) {
                result.remove(element);
            }
        }
        return result;
    }


    public static Set<Integer> getSymmetricDifference(final Set<Integer> a, final Set<Integer> b) {

        if (a==null||b==null)
            return null;
        final Set<Integer> result = new HashSet<>();
        for (final Integer i : a) {
            if (!b.contains(i))  result.add(i);
        }
        for (final Integer i : b) {
            if (!a.contains(i))  result.add(i);
        }
        return result;
    }



}
