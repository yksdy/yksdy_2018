package test.java.yksdy.meng;

import java.util.ArrayList;

public class Examination2 {
  public int getMaxSnest(int[][] envelopes) {
    int max = 1;
    ArrayList<int[]> sult = new ArrayList<int[]>();
    int[][] data = envelopes;
    for (int i = 0; i < data.length - 1; i++) {
      for (int j = 0; j < data.length - i - 1; j++) {
        if (data[j][1] < data[j + 1][1]) {
          int[] a = data[j];
          data[j] = data[j + 1];
          data[j + 1] = a;
        }
      }
    }
    int s1 = 0;
    int s2 = -1;
    for (int i = 0; i < 1000; i++) {
      s1 = -1;
      sult.clear();
      s2 = (int) (Math.random() * (data.length));
      while (s2 != data.length - 1) {
        if (sult.size() == 0) {
          sult.add(data[s2]);
          s1 = s2;
          s2 = 1 + s2 + (int) (Math.random() * (data.length - 1 - s2));
        } else {
          if (!(data[s2][0] < sult.get(sult.size() - 1)[0])) {
            s2 = 1 + s1 + (int) (Math.random() * (data.length - 1 - s1));
          } else {
            sult.add(data[s2]);
            s1 = s2;
            s2 = 1 + s2 + (int) (Math.random() * (data.length - 1 - s2));
          }
        }
      }
      if (sult.size() != 0) {
        if (data[data.length - 1][0] < sult.get(sult.size() - 1)[0]) {
          sult.add(data[data.length - 1]);
        }
      }
      for (int j = 0; j < sult.size() - 1; j++) {
        if (sult.get(j)[1] == sult.get(j + 1)[1]) {
          sult.remove(j + 1);
          j--;
        }
      }
      if (max < sult.size()) {
        max = sult.size();
      }
    }
    return max;
  }

  public static void main(String[] args) {
    Examination2 e = new Examination2();
    int[][] testData1 = new int[][] { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
    System.out.println(e.getMaxSnest(testData1));
    int[][] testData2 = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 1, 5 } };
    System.out.println(e.getMaxSnest(testData2));
    int[][] testData3 = new int[][] { { 1, 3 }, { 2, 2 }, { 10, 30 }, { 20, 20 }, { 21, 21 }, { 22, 22 } };
    System.out.println(e.getMaxSnest(testData3));
    int[][] testData4 = new int[][] { { 1, 3 }, { 2, 2 }, { 10, 30 }, { 20, 20 }, { 21, 21 }, { 22, 22 } };
    System.out.println(e.getMaxSnest(testData4));
    int[][] testData5 = new int[][] { { 1, 10 }, { 2, 11 }, { 10, 1 }, { 11, 2 } };
    System.out.println(e.getMaxSnest(testData5));
  }
}
