package com.yc.utils;

/**
 * create at 20170829
 * @author yuanchen
 */
public class YCUtils {

	
	/**
	 * Ӌ�� �����б�ָ��ֵ��ĸ���
	 * @param array  ���M
	 * @param elem   ��Ҫ�ҵ�Ԫ�� key
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
