package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DutchNationalFlag {
    public enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> a) {
        int pivotElem = a.get(pivotIndex).ordinal();
        int currIdx=0;
        int minIdx=currIdx;
        int lastIdx=a.size()-1;
        while(currIdx<=lastIdx){
            //Oder of the conditions doesn't matter
            int currElem = a.get(currIdx).ordinal();

            if(currElem<pivotElem)
                Collections.swap(a,currIdx++, minIdx++);
            else if(currElem==pivotElem)
                currIdx++;
            else Collections.swap(a,currIdx,lastIdx--);
            //else if(currElem>pivotElem) //explicit condition
        }
    }

   //Same algorithm implemented in the book.
    public static void dutchFlagPartition_same_but_confusing(int pivotIndex, List<Color> a) {
        int pE = a.get(pivotIndex).ordinal();
        int minI = 0;
        int maxI = a.size();
        int eqI = minI;

        while(eqI<maxI){
            int eqE=a.get(eqI).ordinal();
            if(eqE<pE){
                Collections.swap(a,minI++,eqI++);
            } else if (eqE==pE) {
                eqI++;
            } else {
//                 else if (eqE>pE) {3
                Collections.swap(a,eqI,--maxI);
            }
        }
    }
    //O(n) or O(2n) implementation
    public static void dutchFlagPartition_OrderOfN(int pivotIndex, List<Color> A) {
        // TODO - you fill in here.
        int pivotElem = A.get(pivotIndex).ordinal();
        int smallI = 0;
        for (int i = 0; i < A.size(); i++)
            if (A.get(i).ordinal() < pivotElem)
                Collections.swap(A, i, smallI++);
        int maxI = A.size() - 1;
        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivotElem; i--)
            if (A.get(i).ordinal() > pivotElem)
                Collections.swap(A, i, maxI--);
    }

    // O(n^2) implementation.
    public static void dutchFlagPartition_On2(int pivotIndex, List<Color> A) {
        // TODO - you fill in here.
        int pivotElem = A.get(pivotIndex).ordinal();

        for (int i = 0; i < A.size(); ++i) {
            for (int j = i + 1; j < A.size(); ++j) {
                if (A.get(j).ordinal() < pivotElem) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }

        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivotElem; --i) {
            for (int j = i - 1; j >= 0 && A.get(j).ordinal() >= pivotElem; --j) {
                if (A.get(j).ordinal() > pivotElem) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }
        for (Color a : A) {
            System.out.println(a.ordinal());
            System.out.println(a);
        }
    }

    @EpiTest(testDataFile = "dutch_national_flag.tsv")
    public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                                 List<Integer> A, int pivotIdx)
            throws Exception {
        List<Color> colors = new ArrayList<>();
        int[] count = new int[3];

        Color[] C = Color.values();
        for (int i = 0; i < A.size(); i++) {
            count[A.get(i)]++;
            colors.add(C[A.get(i)]);
        }

        Color pivot = colors.get(pivotIdx);
        executor.run(() -> dutchFlagPartition(pivotIdx, colors));

        int i = 0;
        while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        if (i != colors.size()) {
            throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                    "th element");
        } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
            throw new TestFailure("Some elements are missing from original array");
        }
    }


    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
