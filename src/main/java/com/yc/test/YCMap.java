package com.yc.test;

public interface YCMap<K,V> {
	
	V get(K key);
	V put(K key,V value);
	int size();
	
	interface ycEntry<K,V>{
		K getKey();
		V getValue();
	}
	

}
