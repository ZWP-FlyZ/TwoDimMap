package test;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.zwp.flyz.interfaces.TwoDimMap.TwoDimEntry;
import com.zwp.flyz.maps.HashTwoDimMap;

public class HashTwoDimMapTest {

	
	HashTwoDimMap<String,String,Integer> map;
	HashTwoDimMap<Integer,Integer,Integer> hMap; 
	
	@Before
	public void before(){
//		map = new HashTwoDimMap<Integer,Integer,Integer>();
//		for(int i=0;i<14;i++)
//			map.put(i, i, i);
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		map = new HashTwoDimMap<String,String,Integer>();
		hMap = new HashTwoDimMap<Integer,Integer,Integer>();
		for(int i=0;i<500;i++){
			//map.put(i+1, i, i);
			//System.err.println(i+" = "+hash(i+"test") + " |  "+hash("test"+i));
			map.put(i+"test", "test"+i, i);
			hMap.put(i, i, i);
		}
		
		
		//map.put(362+"test", "test"+362, 362);
		
		//map.clear();
//		map.put(0, 0, 0);
//		map.put(64, 64, 1);
//		map.put(32, 32, 2);
//		System.err.println(map.get(2, 2));
//		System.err.println(map.size());
//		System.err.println(map.remove(2, 2));
//		System.err.println(map.size());
//		System.err.println(map.remove(2, 3));
//		System.err.println(map.size());
//		System.err.println(map.get(1, 2));
//		System.err.println(map.size());
//		System.err.println(map.isContain(2, 2));
		
//		Iterator<Integer> iv = map.values().iterator();
//		while(iv.hasNext()){
//			System.err.println(iv.next().toString());
//		}
//		
//		
//		Iterator<TwoDimKey<String,String>> ik = map.keySet().iterator();
//		while(ik.hasNext()){
//			System.err.println(ik.next().toString());
//		}
		
		//map.clear();
		map.remove("1test", "test1");
		System.err.println(""+map.size());
		int i=0;
		Iterator<TwoDimEntry<String,String,Integer>> i2 = map.entrySet().iterator();
		while(i2.hasNext()){
			System.err.println(i++ +"---"+i2.next().toString());
		}
		
		//System.err.println(map.toString());
	}
	
	 final int hash(Object o){
		int h;
		return o==null?0:(h=o.hashCode()) ^ (h>>>16);
	}

}
