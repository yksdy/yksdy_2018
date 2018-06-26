package yksdy.meng.JAVA_EXAMINSTION_src;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//public class ExaminationAnswer {
//
//  public int getMaxSnest(int[][] envelopes) {
//	    int a = getLeft(envelopes);
//	    int b = getRight(envelopes);
//	    return a>b ? a:b;
//	  }
//	  
//	  public int getLeft(int[][] envelopes){
//		  Map<Integer,Set<Integer>> map = new TreeMap<Integer, Set<Integer>>();
//		    for(int[] array : envelopes){
//		    	if(map.containsKey(array[0])){
//		    		Set<Integer> set = map.get(array[0]);
//		    		set.add(array[1]);   		
//		    	}else{
//		    		Set<Integer> set = new TreeSet<Integer>();
//		    		set.add(array[1]);
//		    		map.put(array[0], set);
//		    	}
//		    }
//
//		    Iterator<Integer> it = map.keySet().iterator();
//		    int maxWidth = 0;
//		    int count = 0;
//		    while(it.hasNext()){
//		    	int temp = it.next();
//		    	Set<Integer> set = map.get(temp);
//		    	for(int i : set){
//		    		if(i > maxWidth){
//		    			maxWidth = i;
//		    			count ++;
//		    			break;
//		    		}
//		    	}
//		    }
//			return count;
//	  }
//	  
//	  public int getRight(int[][] envelopes){
//		  Map<Integer,Set<Integer>> map = new TreeMap<Integer, Set<Integer>>();
//		    for(int[] array : envelopes){
//		    	if(map.containsKey(array[1])){
//		    		Set<Integer> set = map.get(array[1]);
//		    		set.add(array[0]);   		
//		    	}else{
//		    		Set<Integer> set = new TreeSet<Integer>();
//		    		set.add(array[0]);
//		    		map.put(array[1], set);
//		    	}
//		    }
//
//		    Iterator<Integer> it = map.keySet().iterator();
//		    int maxWidth = 0;
//		    int count = 0;
//		    while(it.hasNext()){
//		    	int temp = it.next();
//		    	Set<Integer> set = map.get(temp);
//		    	for(int i : set){
//		    		if(i > maxWidth){
//		    			maxWidth = i;
//		    			count ++;
//		    			break;
//		    		}
//		    	}
//		    }
//			return count;
//	  }
//}

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ExaminationAnswer {
public int getMaxSnest(int[][] envelopes) {
    if (envelopes.length <= 0) {
        return 0;
    }
    TreeSet<Env> set = new TreeSet<>((o1, o2) -> {
        if (o1.width != o2.width) {
            return o1.width- o2.width;
        } else {
            return o1.length - o2.length;
        }
    });
    for (int[] envelope : envelopes) {
        if (envelope.length != 2) {
            throw new IllegalArgumentException("ArgumentException");
        }
        if (envelope[0] < 0 || envelope[1] < 0) {
            throw new IllegalArgumentException("ArgumentException");
        }
        Env env = new Env(envelope[0], envelope[1]);
        set.add(env);
    }
    List<Env> list = new ArrayList<>(set);

    int[] temp = new int[envelopes.length];
    temp[0] = list.get(0).length;
    int result = 0;
    int left;
    int right;
    int middle;
    for (int i = 1; i < list.size(); i++) {
        left = 0;
        right = result;
        while (left <= right) {
            middle = (left + right) / 2;
            if (list.get(i).length > temp[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        result = Math.max(result, left);
        temp[left] = list.get(i).length;
    }
    return result + 1;
}

public static class Env {
    private int length;
    private int width;

    public Env(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return length + "," + width;
    }
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






