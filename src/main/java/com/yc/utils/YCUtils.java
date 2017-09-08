package com.yc.utils;

/**
 * create at 20170829
 * @author yuanchen
 */
public class YCUtils {

	
	/**
	 * 計算 数组中比指定值大的个数
	 * @param array  數組
	 * @param elem   需要找的元素 key
	 * @return
	 */
	public static <T extends Comparable<T>> int countGreatThanKey(T[] array , T elem){
		
		int count = 0 ;
		for(T el : array){
			if(el.compareTo(elem) > 0 ){
				count ++ ;
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		
	}
	
	
}
