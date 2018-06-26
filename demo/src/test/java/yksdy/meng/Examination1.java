package yksdy.meng;

public class Examination1 {
	  public int getMaxSnest(int[][] envelopes) {
	// 你的代码
		  int[] tmp=new int[envelopes.length];
		  for(int i=0;i<tmp.length;i++){
			  tmp[i]=1;
		  }
		  int count=1;
		  for(int i=1;i<envelopes.length;i++){
			  for(int j=0;j<i;j++){
				  if(envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]
								  ||envelopes[i][0]>envelopes[j][1]&&envelopes[i][1]>envelopes[j][0]
										)
					tmp[i]=Math.max(tmp[i],tmp[j]+1);
					
			}
			count=Math.max(count,tmp[i]);
		}
	    return count+1;
	  }
	  
	  public static void main(String[] args) {
	    Examination1 e = new Examination1();
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
