import java.util.*;

public class Quick {

  private static Random randgen = new Random();

  public static void main(String[] args) {
    int[] data = new int[] {2, 5, 123, 245, 33, 454, 12, 425, 12, 3543, 11, 33, 52};
    System.out.println(partition(data, 0, data.length -1));
    System.out.println(Arrays.toString(data));
  }
  /*Modify the array such that:
  *1. Only the indices from start to end inclusive are considered in range
  *2. A random index from start to end inclusive is chosen, the corresponding
  *   element is designated the pivot element.
  *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
  *4. all elements in range that are larger than the pivot element are placed after the pivot element.
  *@return the index of the final position of the pivot element.
  */
  private static int partition (int [] data, int start, int end) {
    int pivotIndex = Math.abs(randgen.nextInt() % (end - start + 1)) + start;
    int pivot = data[pivotIndex];
    int temp = 0;
    int s = start;
    int e = end;
    System.out.println("pivot: " + pivot);
    // swap start and the pivot if they are different index else move start up one
    if (pivotIndex != s) {
      data[pivotIndex] = data[s];
      data[s] = pivot;
      s ++;
    } else {
      s ++;
    }
    while (s != e) {
      if (data[s] > pivot && s != e) {
        //System.out.println("top");
        // System.out.println("S: " + s);
        // System.out.println("E: " + e);
        temp = data[s];
        data[s] = data[e];
        data[e] = temp;
        e--;
        //System.out.println(Arrays.toString(data));
      }
      if (data[s] <= pivot && s != e) {
        //System.out.println("bottom");
        s++;
      }
    }
    for (int i = start+1; i <= end; i++) {
      if (pivot <= data[i]) {
        pivotIndex = i - 1;
        temp = data[pivotIndex];
        data[pivotIndex] = pivot;
        data[start] = temp;
        return pivotIndex;
      }
    }// if this loop runs without doing anything then the pivot is the largest number
    temp = data[end];
    pivotIndex = end;
    data[end] = pivot;
    data[start] = temp;
    return pivotIndex;
  }
  // return the value that is the kth smallest value of the array.
  public static int quickselect(int[] data, int k) {
    return -1; // so it compiles
  }
}
