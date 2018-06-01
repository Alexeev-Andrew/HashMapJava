public class Test {

	public static void main(String[] args) {
		
		//перевіряємо, чи виникнуть помилки
		//я змінив версію Eclipse тиждень тому, і в цій версії немає JUnit, тому написав тестер вручну
		try {
			testMyIntLongHashMapDefaultConstructor();
			testMyIntLongHashMapPutAndGet();
			testMyIntLongHashMapResize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ну і щоб показати що працює дійсно коректно, виведу трохи інформації в консоль
		
		MyIntLongHashMap tester = new MyIntLongHashMap(6);
		tester.put(10, 20);
		
		tester.put(23,12);
		tester.put(1,null);
		tester.put(11,1212323);
		tester.put(422,92233720364775807l);
		tester.put(null,41);
		
		System.out.print("1) ");
		for(MyIntLongHashMap.Pair<Integer,Long> pair : tester.pairSet()){
			System.out.print(pair+("  "));
		}
		System.out.println("\nSize = "+tester.size());
		tester.put(22,null);
		tester.put(null,null);//change value
		tester.put(0,21232l);
		
		System.out.print("2) ");
		for(MyIntLongHashMap.Pair<Integer,Long> pair : tester.pairSet()){
			System.out.print(pair+("  "));
		}
		System.out.println("\nSize = "+tester.size());
		
		//тестую видалення
		tester.remove(null);
		tester.remove(422);
		tester.remove(11);
		
		System.out.print("3) ");
		for(MyIntLongHashMap.Pair<Integer,Long> pair : tester.pairSet()){
			System.out.print(pair+("  "));
		}
		System.out.println("\nSize = "+tester.size());
	}
	
	
	public static void testMyIntLongHashMapDefaultConstructor() throws Exception{
		MyIntLongHashMap tester = new MyIntLongHashMap();
		if(tester==null) throw new Exception("variable is null");;
	}
	
	public static void testMyIntLongHashMapPutAndGet() throws Exception{
		MyIntLongHashMap tester = new MyIntLongHashMap();
		tester.put(110, 20);
		if(tester.isEmpty()) throw new Exception("This map is empty");;
		
		tester.put(1,12);
		tester.put(2,null);
		tester.put(10,1212323);
		tester.put(312,9223372036854775807l);
		tester.put(null,3);
		tester.put(null,32);//change value
		tester.put(22,null);
		
		if(tester.size()!=7) throw new Exception("Incorrect value");
		if(tester.get(2)!=null) throw new Exception("Incorrect value");
		if(tester.get(10)!=1212323) throw new Exception("Incorrect value");
		if(tester.get(312)!=9223372036854775807l) throw new Exception("Incorrect value");
		if(tester.get(110)!=20) throw new Exception("Incorrect value");
		if(tester.get(22)!=null) throw new Exception("Incorrect value");
		if(tester.get(null)!=32) throw new Exception("Incorrect value");
		
		if(tester.get(1000)!=null) throw new Exception("Incorrect value");
		
	}
	
	
	public static void testMyIntLongHashMapResize() throws Exception{
		MyIntLongHashMap tester = new MyIntLongHashMap(5);
		if( tester.length()!=5) throw new Exception("incorrect length");
		tester.put(3202,132l);
		tester.put(212,null);
		tester.put(130,231l);
		if( tester.length()!=11) throw new Exception("length!=10(2*previous_length+1)");
		tester.put(312,425357368857475313l);
		tester.put(null,32l);
		//System.out.println(tester.length());
		if(tester.length()!=11) throw new Exception("length!=10(2*previous_length+1)");
	}
	

}
