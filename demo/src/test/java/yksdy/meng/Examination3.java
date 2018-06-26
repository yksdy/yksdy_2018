package yksdy.meng;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Examination3 {
  public int getMaxSnest(int[][] envelopes) {
    int a = getLeft(envelopes);
    int b = getRight(envelopes);
    return a>b ? a:b;
  }
  
  public int getLeft(int[][] envelopes){
	  Map<Integer,Set<Integer>> map = new TreeMap<Integer, Set<Integer>>();
	    for(int[] array : envelopes){
	    	if(map.containsKey(array[0])){
	    		Set<Integer> set = map.get(array[0]);
	    		set.add(array[1]);   		
	    	}else{
	    		Set<Integer> set = new TreeSet<Integer>();
	    		set.add(array[1]);
	    		map.put(array[0], set);
	    	}
	    }

	    Iterator<Integer> it = map.keySet().iterator();
	    int maxWidth = 0;
	    int count = 0;
	    while(it.hasNext()){
	    	int temp = it.next();
	    	Set<Integer> set = map.get(temp);
	    	for(int i : set){
	    		if(i > maxWidth){
	    			maxWidth = i;
	    			count ++;
	    			break;
	    		}
	    	}
	    }
		return count;
  }
  
  public int getRight(int[][] envelopes){
	  Map<Integer,Set<Integer>> map = new TreeMap<Integer, Set<Integer>>();
	    for(int[] array : envelopes){
	    	if(map.containsKey(array[1])){
	    		Set<Integer> set = map.get(array[1]);
	    		set.add(array[0]);   		
	    	}else{
	    		Set<Integer> set = new TreeSet<Integer>();
	    		set.add(array[0]);
	    		map.put(array[1], set);
	    	}
	    }

	    Iterator<Integer> it = map.keySet().iterator();
	    int maxWidth = 0;
	    int count = 0;
	    while(it.hasNext()){
	    	int temp = it.next();
	    	Set<Integer> set = map.get(temp);
	    	for(int i : set){
	    		if(i > maxWidth){
	    			maxWidth = i;
	    			count ++;
	    			break;
	    		}
	    	}
	    }
		return count;
  }

  public static void main(String[] args) {
    Examination3 e = new Examination3();
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
    int[][] testData6 = new int[][] { { 5,4 }, { 6,4 }, { 6,7 }, { 2,3} };
    System.out.println(e.getMaxSnest(testData6));
  }
}