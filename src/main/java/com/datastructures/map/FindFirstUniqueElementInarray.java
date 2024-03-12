import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Explanation #
Firstly, we store all the elements from the array into a HashMap. The element is stored as key and the count of multiple occurrences is stored as value in the HashMap. Initially, the count is 0. But if the same element is encountered again, the count is increased by 1 each time.

Afterward, we traverse the array again from the beginning and return the first element which has count equal to 0 in the HashMap.

Time Complexity #
The array is iterated multiple times but the complexity is still linear. The time complexity of this code is O(n)O(n).
*/
class FindFirstUniqueElementInarray {
    public static int findFirstUnique(int[] arr) {

        Map<Integer, Integer> countElements = new HashMap<>();
        //If the element does not exist in the HashMap
        //Add that element with value = 0
        //or else update the number of occurrences of that element by adding 1
        for (int i = 0; i < arr.length; i++) {
            if(countElements.containsKey(arr[i])){
                int occurence = countElements.get(arr[i]) + 1;
                countElements.put(arr[i], occurence);
            }
            else
                countElements.put(arr[i], 0); 
        }
        //Traverse the array and check the number of occurrences
        //Return the first element with 0 occurences
        for (int i = 0; i < arr.length; i++) {
            if (countElements.get(arr[i]) == 0) {
                return arr[i];
            }
        }
        //If no such element is found, return -1
        return -1;
    }

    public static void main(String args[]) {

        int[] arr = {2, 54, 7, 2, 6, 54};

        System.out.println("Array: " + Arrays.toString(arr));

        int unique = findFirstUnique(arr);
        System.out.print("First Unique in an Array: " + unique);

    }
}
