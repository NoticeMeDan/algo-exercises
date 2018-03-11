import java.util.*;
import edu.princeton.cs.algs4.*;

public class Runsort {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sortFrom(a, 0);
        aux = null;
    }

    private static void sortFrom(Comparable[] a, int lo) {
+        while (true) {  // The JVM can't properly handle recursion, so we do this instead
+            int mid, hi;
+            mid = FancyRunsort.getRun(a, lo);
+            if (mid < a.length - 1) {   //does the first run end before the last element?
+                hi = FancyRunsort.getRun(a, mid + 1);
+                if (hi-lo >= 8)
+                    FancyRunsort.merge(a, lo, mid, hi);
+                else
+                    Insertion.sort(a, lo, hi+1);
+                if (hi < a.length - 1)  //does the second run end before the last element?
+                    lo = hi + 1; continue; //(recurse) FancyRunsort.sortFrom(a, hi + 1);
+            } else if (lo == 0 && mid == a.length - 1) {    // a single run from start to end?
+                return; // the list is already sorted
+            }
+            lo = 0; //(recurse) FancyRunsort.sort(a);
+        }
+    }
+

    // Return the index "i" for which a[startIdx..i] is sorted
    //  (for all indices i > 0; a[i-1] <= a[i])
    private static int getRun(Comparable[] a, int startIdx) {
        int i = startIdx;
        while (i < a.length-1 && a[i].compareTo(a[i+1]) < 1) {
            i++;
        }
        return i;
    }

    // Is the list already sorted?
    private static boolean isSorted(Comparable[] a) {
        return Runsort.getRun(a,0) == a.length-1;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // From https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    // From https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Runsort.sort(a);
        show(a);
    }

    // From https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

}