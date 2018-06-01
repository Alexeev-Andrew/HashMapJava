
public class MyIntLongHashMap extends MyHashMap<Integer, Long>{
	
	public MyIntLongHashMap(){
		super();
	}
	
	public MyIntLongHashMap(int length){
		super(length);
	}
	
	public void put(int key, long val) {
		super.put(new Integer(key), new Long(val));
	}
	
	public void put(Integer key, long val) {
		super.put(key, new Long(val));
	}
	
	
}
