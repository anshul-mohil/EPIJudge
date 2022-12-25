package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

// Consider each position contains only single digit value i.e. 0-9 with no negative signs or double-digit values
public class IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> a) {
        int lIdx = a.size()-1;
        int carry = 1;
        while (lIdx != -1) {
            //get current index value
            int v = a.get(lIdx) + carry;
            //calculate carry based on curr value
            carry = v / 10;
            // reset current value before moving ahead
            a.set(lIdx, v % 10);
            lIdx--;
            //Optimization: if no carry then no need to process further
            if(carry==0)return a;
        }
        //if carry is left over then create new array and add 1 as init variable
        if(carry==1){
            ArrayList<Integer> newList = new ArrayList<>(Collections.nCopies(a.size() + 1, 0));
            newList.set(0,1);
            return newList;
        }
        return a;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IntAsArrayIncrement.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }
}
