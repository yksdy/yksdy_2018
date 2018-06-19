
/**
 * Hello world!
 *
 */

public class Examination {
	  public int getMaxSnest(int[][] envelopes) {
		    // 你的代码
			for (int i = 0; i < envelopes.length - 1; i++) {
				for (int j = 0; j < envelopes.length - 1 - i; j++) {
					if (envelopes[j][0] < envelopes[j + 1][0]
							|| envelopes[j][0] >= envelopes[j + 1][0] && envelopes[j][1] < envelopes[j + 1][1]) {
						int tempHead = envelopes[j][0];
						int tempBody = envelopes[j][1];
						envelopes[j][0] = envelopes[j + 1][0];
						envelopes[j][1] = envelopes[j + 1][1];
						envelopes[j + 1][0] = tempHead;
						envelopes[j + 1][1] = tempBody;
					}
				}
			}
			int result = 1;
			for (int i = envelopes.length - 1; i >= 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]) {
						result++;
						i = j + 1;
						break;
					}
				}
			}
			return result;
	  }

	  public static void main(String[] args) {
	    Examination e = new Examination();
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
