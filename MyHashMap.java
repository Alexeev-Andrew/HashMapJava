import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class MyHashMap<Key,Value>{
	
	private static final int MIN_LENGTH = 16;
	
	private Pair<Key,Value>[] map; //array of pairs
	
	private int size; //amount of pairs(key-value) in the map
	private int length; //size of arrays(max amount of pairs). Depending on the degree of filling
	 
	/**
	 * 
	 * @param length the initial capacity
	 */
	public MyHashMap(int length) {
		if (length < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + length);
		this.length = length;
		size = 0;
		map = new Pair[length];
	 }
	 
	public MyHashMap(){
		 this(MIN_LENGTH);
	}
	
	/**
	 * Inserts the key-value pair into the map, or change value if the same key already exists
	 * 
	 * @param key the key
	 * @param val the value
	 */
	public void put(Key key, Value val){
		
		if(size >= length/2) resize(2*length+1);
		//if an element with such a key is there, look for the first empty cell
		int i;
		for (i = hash(key); map[i] != null; i = (i+1) % length)
			if (map[i].compareKey(key)) {
				map[i].setValue(val);
				return;
			}; 
		map[i] = new Pair<Key,Value>(key,val);
		
		size++;	
	}
	/**
	 * 
	 * @param key the key
	 * @return the value to which the specified key is mapped or {@code null} if this map contains no mapping for the key
	 * 
	 */
	 public Value get(Key key){
		 for (int i = hash(key); map[i] != null; i = (i+1) % length)
			 if (map[i].compareKey(key))
				 return map[i].getValue();
		 return null;
	 }
	 
	 public void remove(Key key){
		 for(int i = hash(key); map[i] != null; i = (i+1) % length)
			 if(map[i].compareKey(key)){
				 map[i]=null;
				 size--;
			 }
			 
	 }
	
	 /**
	  * 
	  * @return size of map
	  */
	 public int size(){
		 return size;
	 }
	 
	 /**
	  * 
	  * @return length of array
	  */
	 public int length(){
		 return length;
	 }
	 
	 /**
	  * 
	  * @param key the key
	  * @return <tt>true</tt> if this map contains a mapping for the specified
	  */
	 public boolean containsKey(Key key) {
		 return map[hash(key)] != null;
	 }
	 
	 public boolean isEmpty(){
		 return size==0;
	 }
	 
	
	public LinkedStack<Pair<Key,Value>> pairSet() {
		
		LinkedStack<Pair<Key,Value>> set = new LinkedStack();
		
		for(int i=0;i<length;i++)
			if(map[i]!=null) set.push(map[i]);
		
		return set;
	}
	
	 
	 private int hash(Key key){
		 if(key==null)return 0;
		 return (key.hashCode() & 0x7fffffff) % length; //0x7fffffff - the maximum value of int
	 }
	 

	 // resizes the hash table to the given capacity
	 private void resize(int new_length) {
		 MyHashMap<Key, Value> newMap = new MyHashMap<Key, Value>(new_length);
		 for (int i=0; i<length; i++) {
			 if (map[i] != null) {
				 newMap.put(map[i].getKey(), map[i].getValue());
			 }
		 }
		 map = newMap.map;
		 length = newMap.length;
		 return;
	 }
	 
	 
	 
	 static class Pair<K,V> implements Map.Entry<K, V> {
		 
		 final K key;
		 V value;
		 //boolean aviable;//to support the null key
		 
		 Pair(K key, V value){
			 this.key = key;
			 this.value = value;
		 }

		public K getKey() {
			
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
	        return oldValue;
		}
		
		public final boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)obj;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()))
                	return true;
            }
            return false;
        }
		
		public final boolean compareKey(K key){
			if(key==null && this.key==null) return true;
			if(key==null) return this.key.equals(key);
			return key.equals(this.key);
		}
		
		public final String toString() { 
			return "{ "+key + " ; " + value+ " }"; 
		}
		
	 }

	
}
