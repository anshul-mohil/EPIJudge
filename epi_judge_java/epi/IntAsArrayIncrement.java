package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    if(A.size()==0)
      return A;
    int carry=1;
    for (int i = A.size()-1; i >=0 && carry==1 ; i--) {
      int val = A.get(i)+1;
      if(val==10){
        carry=1;
        A.set(i,0);
      }
      else {
        A.set(i,val);
        carry=0;
      }
    }
    if(carry==1)
      A.add(0,1);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
