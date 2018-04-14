import java.util.*;
import edu.princeton.cs.algs4.*;

public class Runsort {
  private static Comparable[] aux;

  private Runsort() {}

  //// From https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html   
  public static void sort(Comparable[] a, int startIndex) {
    aux = new Comparable[a.length];
    int lo = startIndex, hi;
    int mid = Runsort.getRun(a, lo); //Getting the index, when the order of characters isn't increasing anymore

    if(mid < a.length - 1){ //Making sure that the list isn't sorted
      hi = Runsort.getRun(a, mid + 1);
      Runsort.merge(a, lo, mid, hi);
      if(hi < a.length - 1){ //Checking if we can make another run
        Runsort.sort(a, hi + 1); //recursive call
      }
    
    }
  }

  private static int getRun(Comparable[] a, int startIndex){
    int index = startIndex; 
    while(index < a.length - 1 && less(a[index], a[index+1])){
      index++;
    }
    return index;
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

  /***************************************************************************
  Helper sorting functions.
  Taken from https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
  ***************************************************************************/

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }


  /***************************************************************************
  *  Check if array is sorted - useful for debugging.
  Taken from https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
  ***************************************************************************/
  private static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i-1])) return false;
    return true;
  }

  //Taken from https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }

  /**
  * Reads in a sequence of strings from standard input; bottom-up
  * mergesorts them; and prints them to standard output in ascending order. 
  *
  * @param args the command-line arguments
  */

  //Taken from https://algs4.cs.princeton.edu/22mergesort/MergeBU.java.html
  public static void main(String[] args) {
    String[] a = StdIn.readAllStrings();
    Runsort.sort(a, 0);
    show(a);
  }
}