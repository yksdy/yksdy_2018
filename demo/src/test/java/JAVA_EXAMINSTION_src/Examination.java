package JAVA_EXAMINSTION_src;

import java.util.Arrays;
import java.util.Comparator;

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

}
