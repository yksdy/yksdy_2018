package test.java.yksdy.meng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Examination4 {

	
	public static void main(String[] args) {
		
		Examination4 e = new Examination4();
		
		int[][] testData1 = new int[][] {{5,4},{6,4},{6,7},{2,3},{3,4},{4,5},{5,6}};
		System.out.println(e.getMaxSnest(testData1));
		
		int[][] testData2 = new int[][] {{5,4},{6,4},{6,7},{2,3},{1,5}};
		System.out.println(e.getMaxSnest(testData2));
		
		int[][] testData3 = new int[][] {{1,3},{2,2},{10,30},{20,20},{21,21},{22,22}};
		System.out.println(e.getMaxSnest(testData3));
		
		int[][] testData4 = new int[][] {{1,4},{10,30},{20,20},{22,22},{90,19}};
		System.out.println(e.getMaxSnest(testData4));
		
		int[][] testData5 = new int[][] {{1,10},{2,11},{10,1},{11,2}};
		System.out.println(e.getMaxSnest(testData5));
		
		int[][] testData6 = new int[][] {{1,3},{3,5},{6,7},{6,8},{8,4},{9,5}};
		System.out.println(e.getMaxSnest(testData6));
		
		e.getMaxSnest(testData2);

	}
	
	public int getMaxSnest(int[][] envelopws){
		int count = 1;
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		for(int i=0; i<envelopws.length; i++){
			for(int j=0;j<envelopws[i].length;j++){
				if(j==0){
					list1.add(envelopws[i][j]);
				}
				if(j==1){
					list2.add(envelopws[i][j]);
				}
			}
			
		}
		
		for(int i=0; i<list1.size();i++){
			for (int j = 0; j < list1.size()-1; j++) {
				if(list1.get(i)>list1.get(j)){
					int temp1 = list1.get(i);
					list1.set(i, list1.get(j));
					list1.set(j, temp1);
					
					int temp2 = list2.get(i);
					list2.set(i, list2.get(j));
					list2.set(j, temp2);
				}
			}
		}
		
		for(int k=0; k<list1.size()-1;){
			if(list1.get(k) == list1.get(k+1) ){
				if(list2.get(k) > list2.get(k +1)){
					int temp = list2.get(k);
					list2.set(k, list2.get(k+1));
					list2.set(k+1, temp);
					list1.remove(k);
					list2.remove(k);
				}else{
					list1.remove(k);
					list2.remove(k);
				}
			}else if(list2.get(k) == list2.get(k+1)){
				if(list1.get(k) > list1.get(k +1)){
					int temp = list1.get(k);
					list1.set(k, list1.get(k+1));
					list1.set(k+1, temp);
					list1.remove(k);
					list2.remove(k);
				}else{
					list1.remove(k);
					list2.remove(k);
				}
				
			}else if(list1.get(k) > list1.get(k+1) && list2.get(k) < list2.get(k+1)){
				if(k<1){
					list1.remove(k);
					list2.remove(k);
					count = count - k;
					k = 0;
				}else{
					list1.remove(k+1);
					list2.remove(k+1);
					
				}
			}else{
				k++;
				count++;
			}
		}
		
		return count;
	}
}

