package main.java.yksdy.meng.JAVA_EXAMINSTION_src;
public class ExaminiationRun {

  public static void main(String[] args) {
    int result = 0;
    int answer = 0;
    Examination e = new Examination();
    ExaminationAnswer a = new ExaminationAnswer();
    int[][] testData1 = new int[][] { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
    // except 5
    result = e.getMaxSnest(testData1);
    answer = a.getMaxSnest(testData1);
    if (result == answer) {
      System.out.println("正确,期望" + answer + "，实际" + result + ", test data:{ { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } }");
    } else {
      System.out.println("错误,期望" + answer + "，实际" + result + ", test data:{ { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } }");
    }

    int[][] testData2 = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 1, 5 } };
    // except 3
    result = e.getMaxSnest(testData2);
    answer = a.getMaxSnest(testData2);
    if (result == answer) {
      System.out.println("正确,期望" + answer + "，实际" + result + ", test data:{ { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 1, 5 } }");
    } else {
      System.out.println("错误,期望" + answer + "，实际" + result + ", test data:{ { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 }, { 1, 5 } }");
    }

    int[][] testData3 = new int[][] { { 1, 3 }, { 2, 2 }, { 10, 30 }, { 20, 20 }, { 21, 21 }, { 22, 22 } };
    // except 3
    result = e.getMaxSnest(testData3);
    answer = a.getMaxSnest(testData3);
    if (result == answer) {
      System.out.println("正确,期望" + answer + "，实际" + result + ", test data:{ { 1, 3 }, { 2, 2 }, { 10, 30 }, { 20, 20 }, { 21, 21 }, { 22, 22 } }");
    } else {
      System.out.println("错误,期望" + answer + "，实际" + result + ", test data:{ { 1, 3 }, { 2, 2 }, { 10, 30 }, { 20, 20 }, { 21, 21 }, { 22, 22 } }");
    }

    int[][] testData4 = new int[][] { { 1, 4 }, { 10, 30 }, { 20, 20 }, { 22, 22 }, { 90, 19 } };
    // except 3 1 2 2 3 4 2 5 3
    result = e.getMaxSnest(testData4);
    answer = a.getMaxSnest(testData4);
    if (result == answer) {
      System.out.println("正确,期望" + answer + "，实际" + result + ", test data:{ { 1, 4 }, { 10, 30 }, { 20, 20 }, { 22, 22 }, { 90, 19 } }");
    } else {
      System.out.println("错误,期望" + answer + "，实际" + result + ", test data:{ { 1, 4 }, { 10, 30 }, { 20, 20 }, { 22, 22 }, { 90, 19 } }");
    }

    int[][] testData5 = new int[][] { { 1, 10 }, { 2, 11 }, { 10, 1 }, { 11, 2 } };
    // except 3 1 2 2 3 4 2 5 3
    result = e.getMaxSnest(testData5);
    answer = a.getMaxSnest(testData5);
    if (result == answer) {
      System.out.println("正确,期望" + answer + "，实际" + result + ", test data:{ { 1, 10 }, { 2, 11 }, { 10, 1 }, { 11, 2 } }");
    } else {
      System.out.println("错误,期望" + answer + "，实际" + result + ", test data:{ { 1, 10 }, { 2, 11 }, { 10, 1 }, { 11, 2 } }");
    }

    int[][] testData6 = new int[][] { { 1, 3 }, { 3, 5 }, { 6, 7 }, { 6, 8 }, { 8, 4 }, { 9, 5 } };

    // except 3 1 2 2 3 4 2 5 3
    result = e.getMaxSnest(testData6);
    answer = a.getMaxSnest(testData6);
    if (result == answer) {
      System.out.println("正确,期望" + answer + "，实际" + result + ", test data:{ { 1, 3 }, { 3, 5 }, { 6, 7 }, { 6, 8 }, { 8, 4 }, { 9, 5 } }");
    } else {
      System.out.println("错误,期望" + answer + "，实际" + result + ", test data:{ { 1, 3 }, { 3, 5 }, { 6, 7 }, { 6, 8 }, { 8, 4 }, { 9, 5 } }");
    }

  }

}
