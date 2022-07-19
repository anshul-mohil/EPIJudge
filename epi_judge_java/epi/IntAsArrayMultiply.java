package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> a, List<Integer> b) {
    // TODO - you fill in here.
    if(a.size()==0 || b.size()==0)
      return new ArrayList<>(0);
    //calculate and remove sign
    int sign = a.get(0) < 0 ^ b.get(0) < 0 ? -1: 1;
    a.set(0, Math.abs(a.get(0)));
    b.set(0, Math.abs(b.get(0)));
    List<Integer> r = new ArrayList<>(Collections.nCopies(a.size()+b.size(), 0));
    for (int i = a.size()-1; i >= 0; i--) {
      for (int j = b.size()-1; j >= 0; j--) {
        int insertIndex = i+j+1;
        int val = r.get(insertIndex) + (a.get(i) * b.get(j));
        r.set(insertIndex,val%10);
        r.set(insertIndex-1,r.get(insertIndex-1)+val/10);
      }
    }
    //initial value can be zero
    // 1. 999*99 generate n+m digits=5.
    // 2. 111*11=1221 generate 4 digit number
    // 2. 999*000000 can generate 000000-000 or simply zero 0.
    int number_of_zeros = 0;
    while(number_of_zeros<r.size() && r.get(number_of_zeros)==0){number_of_zeros++;}
    r= r.subList(number_of_zeros,r.size());
    if(r.isEmpty())
      return List.of(0);
    r.set(0,sign*r.get(0));
    return r;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
