package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv")
    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        if (n < 2)
            return new ArrayList<>();
        List<Integer> p_lst = new ArrayList<>();
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
//    Not needed to setup flag if I start from 2 index
//    isPrime.set(0,false);
//    isPrime.set(1, false);
        for (int num = 2; num <= n; num++) {
            //First time when 2 encounters it's a prime but after that positions are precalculated for non-primes
            if (isPrime.get(num)) {
                p_lst.add(num);
//Important! This is scout&Guide algo:
//All numbers which are addition to nonPrime generates non-prime
//Use above understanding to mark all indexes of isPrime to mark non-prime
                for (int nonPrimeIdx = 0; nonPrimeIdx <= n; nonPrimeIdx += num) {
//          set not a prime
                    isPrime.set(nonPrimeIdx, false);
                }
            }
        }
        return p_lst;
    }

    public static List<Integer> generatePrimesNonOptimized(int n) {

        // Given n, return all primes up to and including n.
        // TODO - you fill in here.
        if (n < 2)
            return new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime)
                l.add(i);
        }
        return l;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
