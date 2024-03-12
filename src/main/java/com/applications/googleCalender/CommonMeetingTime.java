package com.applications.googleCalender;

import java.util.ArrayList;
import java.util.Arrays;

/*we want to implement a feature that lets us see when two users are busy at the same time.
 We will be given the meeting schedule of two users,
 and we have to find all the overlapping meetings to determine when both of them are unavailable.
 For simplicity, we are mapping the military timing to integers in the input.
 So, for example, 8:00 will be denoted by 8 in the code.
 */

//Time complexity #
//The time complexity is O(n + m),
// where n and m are the number of meetings in meetingsA and meetingsB, respectively.

//Space complexity #
//The space complexity is O(max(n,m)), because,
// in the worst case, all meetings in meetingsA can be overlapping with the meetings in meetingsB and vice versa
class CommonMeetingTime {

    public static int[][] meetingsIntersection(int[][] meetingsA, int[][] meetingsB){
        ArrayList<int []> intersection = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < meetingsA.length && j < meetingsB.length) {
            int start = Math.max(meetingsA[i][0], meetingsB[j][0]);
            int end = Math.min(meetingsA[i][1], meetingsB[j][1]);
            if (start < end)
                intersection.add(new int[]{start, end});

            if (meetingsA[i][1] < meetingsB[j][1])
                i++;
            else
                j++;
        }

        return intersection.toArray(new int[intersection.size()][]);
    }
    public static void main( String args[] ) {
        int[][] meetingsA = {{1, 3}, {5, 6}, {7, 9}};
        int[][] meetingsB = {{2, 3}, {5, 7}};
        System.out.println(Arrays.deepToString(meetingsIntersection(meetingsA, meetingsB)));
    }
}

/*CheckBusySchedule #
Here is how we will implement this feature:

1. We will use two indices, i and j, to traverse both of the meeting schedules, meetingsA and meetingsB, respectively.

2. The indices i and j will both be zero at the beginning.

3. Next, we will check if meetingsA[i] and meetingsB[j] overlap by comparing the start and end times.

4. If the times overlap, the overlapping time interval will be added to the resultant list.

5. Otherwise, we will keep incrementing the indices depending upon the end time of the next meeting.
This means that if the next meeting in meetingsA ends before the next meeting in meetingsB
we will only increment i.
 This is because the current meeting in meetingsB has the possibility to overlap with the next meeting.
 Similarly, vice versa is also true in case of incrementation in j.
 */

