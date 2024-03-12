package com.applications.uber;

/*At the end of a ride, the Uber app displays the fare to the customer.
For accessibility, we want the fare to be read out, should the customer so choose.
 Assuming that the ride fare is an integer and that a text to speech engine is available,
  all we need to do is to convert the fare to English words.
  For example, we need to change $120 to one hundred and twenty dollars.
   The fare may be as high as several billion depending on the country the ride is taken in.

We’ll be provided with an integer number, fare, which represents the fare.
Our task will be to convert fare to its English word representation.
 */

//Time complexity #
//We traverse through all n digits of the input, so time complexity will be O(n).
//
//Space complexity #
//The space complexity will be O(1) because constant space is utilized and the output is just a string.
class CovertRideFareInWords {

    public static String ones(int fare) {
        switch(fare) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
        }
        return "";
    }

    public static String teens(int fare) {
        switch(fare) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }
        return "";
    }

    public static String tens(int fare) {
        switch(fare) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
        }
        return "";
    }

    public static String two(int fare) {
        if (fare == 0)
            return "";
        else if (fare < 10)
            return ones(fare);
        else if (fare < 20)
            return teens(fare);
        else {
            int tenner = fare / 10;
            int rest = fare - tenner * 10;
            if (rest != 0)
                return tens(tenner) + " " + ones(rest);
            else
                return tens(tenner);
        }
    }

    public static String three(int fare) {
        int hundred = fare / 100;
        int rest = fare - hundred * 100;
        String res = "";

        if (hundred*rest != 0)
            res = ones(hundred) + " Hundred " + two(rest);
        else if ((hundred == 0) && (rest != 0))
            res = two(rest);
        else if ((hundred != 0) && (rest == 0))
            res = ones(hundred) + " Hundred";
        
        return res;
    }

    public static String FareinWords(int fare) {
        if (fare == 0)
            return "Zero";

        int billion = fare / 1000000000;
        int million = (fare - billion * 1000000000) / 1000000;
        int thousand = (fare - billion * 1000000000 - million * 1000000) / 1000;
        int rest = fare - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        
        if (billion != 0)
            result = three(billion) + " Billion";
        if (million != 0) {
            if (! result.isEmpty())
                result += " ";
            result += three(million) + " Million";
        }
        if (thousand != 0) {
            if (! result.isEmpty())
                result += " ";
            result += three(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (! result.isEmpty())
                result += " ";
            result += three(rest);
        }
        
        return result;
    }


    public static void main( String args[] ) {
        // Driver code

        int fare = 5648;
        System.out.println( "The fare is: " +  FareinWords(fare) + " dollars");
    }
}


/*We can solve this problem by dividing the initial number into a specific set of digits
and then solving those sets.
Initially, we can split the number into sets of three.
For example, if we split the amount 1234567890 into sets of three digits,
we’ll get 1 234 567 890. Now,
the values are separated into billion, million, and thousand parts in the number.

At this stage, our English representation will become 1 Billion 234 Million 567 Thousand 890.

The three digits from each set can be further divided into individual sets
to obtain the values at the hundreds, tens, and ones places.
 For example, 567 can be split into 5 6 7.
  After splitting, 5 6 7 will be converted to the English word representation
  five Hundred sixty seven.
  We need to consider the values from 10 to 19 separately for conversion
  if they are present at the ones and tens places.

 */