package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.List;
import java.util.Objects;

public class SortedArrayRemoveDups {
    //Second time implementation
    //Note: Not able to think by myself
    //Approach: Base: Since it's a sorted list traverse index should be equal or larger than previously traversed values
    // Now, if we say that we start reading array from index 1 and will update all positions with the greater value than
    // the previously read one(which initially was a.get(0), continuing with this approach by maintaining previously
    // identified the lowest value we will make sure we only insert greater values then already read values at insertIdx.
    // Which for non-duplicate list is always updating value from itself or the bigger next value in array where there
    // are duplicates.
    public static int deleteDuplicates(List<Integer> a) {
        if(a==null || a.size()<1)
            return 0;
        int prev=a.get(0);
        int insertIdx=1;
        for (int i = 1 ; i < a.size() ; i++) {
           if(prev < a.get(i)){
              a.set(insertIdx++,a.get(i));
              prev=a.get(i);
           }
        }
        return insertIdx;
    }
    // Didn't have patience to pursue this idea because it has all the earmarks of messy idea.
    // Idea: traverse array once and mark all values as Integer.MIN_VALUE, now consider all these places to be replaced
    // by other available values at the higher index starting from end of an array. Now when traversing from end of an
    // array you encounter MIN_VALUES, you ignore them and use the next available value(not MIN_Value) while traversing from higher
    // index to lower index and reduce the last index because you actually reduce the size of the array by planting
    // value up in the array to initial portion and you could get rid of the array at that index onwards.
//    public static int deleteDuplicates(List<Integer> a) {
//        if(a== null || a.size() == 0)
//            return 0;
//        if(a.size()==1)
//            return 1;
//        int le = a.size()-1;
//        for (int i = 0; i < a.size()-1; i++) {
//            int next=i+1;
//            while (next<a.size() && Objects.equals(a.get(i), a.get(next)))
//                a.set(next++, Integer.MIN_VALUE);
//        }
//        //TO handle corner case of having integer min value as possible value in array, we could
//        // only apply calculating this value after we know that larger value already been found.
//        boolean isLargeValueFound=false;
//        for (int i = 0; i < a.size(); i++) {
//            if(!isLargeValueFound && a.get(i)>Integer.MIN_VALUE)
//                isLargeValueFound=true;
//            if(isLargeValueFound && a.get(i).equals(Integer.MIN_VALUE))
//                while(a.get(le)>Integer.MIN_VALUE)
//                    le--;
//                a.set(i,a.get(le--));
//        }
//        if(!isLargeValueFound)
//            return 1;
//        return le;
//    }

    //First time implementation
    // Returns the number of valid entries after deletion.
//    public static int deleteDuplicates(List<Integer> a) {
//        if (a==null || a.size() < 1)
//            return 0;
//        int insertIdx = 1;
//        int curr_val = a.get(0);
//        int i;
//        for (i = 1; i < a.size(); i++) {
//            if (a.get(i) > curr_val) {
//                a.set(insertIdx++, a.get(i));
//                curr_val = a.get(i);
//            }
//        }
//        return insertIdx;
//    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                        List<Integer> A)
            throws Exception {
        int end = executor.run(() -> deleteDuplicates(A));
        return A.subList(0, end);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
