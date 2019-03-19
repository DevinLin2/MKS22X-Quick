import java.util.*;

public class Quick {

  private static Random randgen = new Random();

  public static void main(String[]args){
    // System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    // int[]MAX_LIST = {1000000000,500,10};
    // for(int MAX : MAX_LIST){
    //   for(int size = 31250; size < 2000001; size*=2){
    //     long qtime=0;
    //     long btime=0;
    //     //average of 5 sorts.
    //     for(int trial = 0 ; trial <=5; trial++){
    //       int []data1 = new int[size];
    //       int []data2 = new int[size];
    //       for(int i = 0; i < data1.length; i++){
    //         data1[i] = (int)(Math.random()*MAX);
    //         data2[i] = data1[i];
    //       }
    //       long t1,t2;
    //       t1 = System.currentTimeMillis();
    //       Quick.quicksort(data2);
    //       t2 = System.currentTimeMillis();
    //       qtime += t2 - t1;
    //       t1 = System.currentTimeMillis();
    //       Arrays.sort(data1);
    //       t2 = System.currentTimeMillis();
    //       btime+= t2 - t1;
    //       if(!Arrays.equals(data1,data2)){
    //         System.out.println("FAIL TO SORT!");
    //         System.exit(0);
    //       }
    //     }
    //     System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    //   }
    //   System.out.println();
    // }
    int[] data = new int[]{123, 1234,123, 435, 234, 56, 23, 5, 245};
    insertionSort(data, 0, data.length - 1);
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
  private static int partition (int[] data, int start, int end) {
    int pivotIndex = Math.abs(randgen.nextInt() % (end - start + 1)) + start;
    if (start - end > 3) {
      pivotIndex = median(data, start, end);
    }
    int pivot = data[pivotIndex];
    int temp = 0;
    int startIndex = start;
    // System.out.println("pivot: " + pivot);
    // swap start and the pivot if they are different index else move start up one
    data[pivotIndex] = data[start];
    data[start] = pivot;
    start ++;
    while (start < end) {
      if (data[start] > pivot) {
        //System.out.println("top");
        // System.out.println("S: " + s);
        // System.out.println("E: " + e);
        temp = data[end];
        data[end] = data[start];
        data[start] = temp;
        end--;
        //System.out.println(Arrays.toString(data));
      }
      if (data[start] < pivot) {
        //System.out.println("bottom");
        start++;
      }
      if (data[start] == pivot) {
        int randInt = Math.abs(randgen.nextInt()) % 2;
        if (randInt == 0) {
          temp = data[end];
          data[end] = data[start];
          data[start] = temp;
          end--;
        }
        if (randInt == 1) {
          start ++;
        }
      }
    }
    // for (int i = start+1; i <= end; i++) {
    //   if (pivot < data[i]) {
    //     pivotIndex = i - 1;
    //     temp = data[pivotIndex];
    //     data[pivotIndex] = pivot;
    //     data[start] = temp;
    //     return pivotIndex;
    //   }
    // }// if this loop runs without returning anything then the pivot is the largest number
    // temp = data[end];
    // pivotIndex = end;
    // data[end] = pivot;
    // data[start] = temp;
    // return pivotIndex;
    if (data[start] < pivot) {
      data[startIndex] = data[start];
      data[start] = pivot;
      return start;
    }
    if (data[start] > pivot) {
      data[startIndex] = data[start - 1];
      data[start - 1] = pivot;
      return start - 1;
    }
    return start;
  }
  private static int median(int[] data, int start, int end) {
    int startNum = data[start];
    int endNum = data[end];
    int midNum = data[data.length/2];
    if ((startNum < endNum && startNum > midNum) || (startNum > endNum && startNum < midNum)) {
      return start;
    }
    if ((endNum < startNum && endNum > midNum) || (endNum > startNum && endNum < midNum)) {
      return end;
    }
    if ((midNum < endNum && midNum > startNum) || (midNum > endNum && midNum < startNum)) {
      return data.length/2;
    }
    return -1;
  }
  // return the value that is the kth smallest value of the array.
  public static int quickselect(int[] data, int k) {
    if (data.length == 1) {
      return data[0];
    }
    int start = 0;
    int end = data.length - 1;
    int index = partition(data, start, end);
    while (index != k) {
      if (index < k) {
        start = index + 1;
      }
      if (index > k) {
        end = index - 1;
      }
      if (start == end) {
        return data[start];
      }
      index = partition(data, start, end);
    }
    return data[k];
  }
  // Modify the array in increasing order
  public static void quicksort(int[] data) {
    quicksortH(data, 0, data.length - 1);
  }
  private static void quicksortH(int[] data, int start, int end) {
    if (start >= end) {
      return;
    }
    int pivot = partition(data, start, end);
    quicksortH(data, start, pivot - 1);
    quicksortH(data, pivot + 1, end);
  }

  private static boolean isSorted(int[] data) {
    for (int i = 1; i < data.length; i++) {
      if (data[i] < data[i-1]) {
        return false;
      }
    }
    return true;
  }

  private static void insertionSort(int[] data, int start, int end) {
    for (int i = start + 1; i <= end; i++){
      int storage = data[i];
      int index = i - 1;
      while(index >= start && storage < data[index]){
        data[i] = data[index]; //this is just changing data[i] fix this
        index--;
      }
      data[index] = storage;
      index = i - 1;
    }
  }
}
