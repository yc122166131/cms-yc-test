package com.yc.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Map 就是由 entry 数组 组成的 ,数组中的 每一项 为 :  entry链表 或者 单个 entry 或者 是 空 
 * @author yuanchen
 * @param <K>
 * @param <V>
 */
public class YCHashMap<K,V> implements YCMap<K, V> {
	
	/**
	 *  自定义 map 的 默认长度 
	 */
	private static int dafaultLength = 16;
	
	/**
	 *  负载因子(给 Map 进行扩容用 ~)
	 *  ( 当 map 中的 entry 个数 大于   默认长度 * 负载因子  则 进行扩容)
	 */
	private static double defaultLoad = 0.75;
	
	//用来记录原先的 map 
	private MyEntry<K,V>[] table = null;
	
	//用来 记录 map的 大小 
	int size = 0 ;
	
	public YCHashMap(int dafaultLength , double defaultLoad){
		this.dafaultLength = dafaultLength;
		this.defaultLoad = defaultLoad;
		table = new MyEntry[dafaultLength];  //给map 初始化大小 16 （Map 就是由 Entry[]数组组成的   ）
	}
	
	public YCHashMap(){
		this(dafaultLength,defaultLoad);
	}
	
	public V get(K key) {

		//根据 这个key , 计算出key在 Map 中的 下标值
		int keyIndex = getIndex(key);
		return getEntry(table[keyIndex],key);
	}

    private V getEntry(MyEntry<K,V> entry ,K key){
    	
    	if(entry.getKey() == null){
    		return null;
    	}
    	if(key == entry.getKey() || key.equals(entry.getKey())){
    		return entry.getValue();
    	}else{
    		if(entry.next != null){
    			return getEntry(entry.next,key);
    		}else{
    			return null;
    		}
    	}
    }

	public V put(K key, V value) {
		//如果table 的元素个数大于  默认长度 * 负载因子 则进行扩容
		if(size > dafaultLength * defaultLoad){
			doubleSize();
			
		}
		
		
		// 根据这个 key ,根据定义的额函数算法 ,算出 key 对应的 标的 下标 
		int index = getIndex(key);
		MyEntry<K,V> entry = table[index]; //获取该key通过hash算法得到的索引，并获取该索引上的 值 
		if(entry != null){   //如果 新加的 key 查询出的 index 索引下  已经存在 了 entry就 必须 要 占用这个 位置 并且next指针指向 上一个 entry
			MyEntry<K,V> newEntry = newEntry(key,value,entry,index);
			table[index] = newEntry;
		}else{   //当 查询的 当前 Map 中 该 索引 值 为空 即 该位置 不存在 任何 entry的时候 我们 直接往里面加entry 并且 map的 大小 加1 
			MyEntry<K,V> newEntry = newEntry(key,value,null,index);
			table[index] = newEntry;
			size++;  //只是 记录被填的 坑的 数量!
		}		
		return table[index].getValue();
	}
	
	
	/**
	 * 进行扩容操作 
	 * 散列值重新分布
	 */
	private void doubleSize(){
		MyEntry<K,V>[] newTable = new MyEntry[dafaultLength * 2];  //创建一个 新的 map 容量为 之前的两倍 
		
		List<MyEntry<K,V>> list = new ArrayList<MyEntry<K,V>>();
		
		for(int i = 0 ; i < table.length ; i++){
			if(table[i] == null){
				continue;
			}else{
				findEntry(table[i],list);
			}
		}
		//扩容后 重新  Map的分配 
		againHash(newTable,list);
	}
	
	// 将 一个 map 中 的 所有的 index 索引下 entry 包括  一个 Index 下的 链表  entry  一起 存在 list 中 
	private void findEntry(MyEntry<K,V> entry ,List<MyEntry<K,V>> list){
		
		if(entry.next == null ){
			list.add(entry);
		}else{
			list.add(entry);
			findEntry(entry.next,list);
		}
		
	}
	
	/**
	 * 扩容后 hash再散列
	 * newTable 是一个 扩容了一倍的 空 Entry<K,V>[] 数组 
	 * 
	 * @param newTable
	 * @param list
	 */
	private void againHash(YCHashMap<K, V>.MyEntry<K, V>[] newTable,
			List<YCHashMap<K, V>.MyEntry<K, V>> list) {
		
		if(list != null && list.size() > 0 ){
			dafaultLength = dafaultLength * 2;
			size = 0 ; 
			table = newTable;
		}
		
		for(YCHashMap<K, V>.MyEntry<K, V> entry : list){
			put(entry.getKey(),entry.getValue());
		}
		
	}
	
	private MyEntry<K,V> newEntry(K key , V value , MyEntry<K,V> entry , int index){
		return new MyEntry<K,V>( key , value ,  entry ,  index);
	}
	
	
	/**
	 * 通过 hash算法 获取 key 在  map中的 索引值
	 * @param key
	 * @return
	 */
	private int getIndex(K key){
		
		int index =  key.hashCode() % dafaultLength;
		return index > 0 ? index : -index;
	}

	public int size() {
		return size;
	}
	
	/**
	 * 实现内部接口
	 * @author yuanchen
	 *
	 * @param <K>
	 * @param <V>
	 */
	class MyEntry<K,V> implements YCMap.ycEntry<K, V>{
		
		K key;
		V value;
		MyEntry<K,V> next;
		int index;
		
		public MyEntry(K key ,V value,MyEntry<K,V> next,int index){
			this.key = key;
			this.value = value;
			this.next = next;
			this.index = index;
		}
		
		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}

		
	
	public static void main(String[] args) {
		YCMap<Integer,String> map = new YCHashMap<Integer,String>();
		for(int i = 0 ; i < 100 ; i++){
			map.put(i, "yc"+i);
		}
		
		for(int i = 0 ; i < 100 ; i++){
			System.out.println(map.get(i));
		}
		
		//System.out.println(map.get(3));
	}

}
