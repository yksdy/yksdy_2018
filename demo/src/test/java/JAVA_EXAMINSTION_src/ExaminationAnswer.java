package JAVA_EXAMINSTION_src;

import java.util.Arrays;
import java.util.Comparator;

public class ExaminationAnswer {

  public int getMaxSnest(int[][] envelopes) {
	    // 你的代码
	    if (envelopes == null || envelopes.length == 0)
	      return 0;
	    Arrays.sort(envelopes, new Comparator<int[]>() {
	      public int compare(int[] a1, int[] a2) {
	        return a1[0] - a2[0];
	      }
	    });
	    int res = 0;
	    int[] dp = new int[envelopes.length];
	    for (int i = 0; i < envelopes.length; i++) {
	      for (int j = 0; j < i; j++) {
	        if (envelopes[j][1] < envelopes[i][1] && envelopes[j][0] < envelopes[i][0]) {
	          dp[i] = Math.max(dp[i], dp[j] + 1);
	          res = Math.max(res, dp[i]);
	        }
	      }
	    }
	    int i = 1 + res;
	    return i;
	  }

}
