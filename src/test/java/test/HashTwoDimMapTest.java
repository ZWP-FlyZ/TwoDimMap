package test;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.zwp.flyz.interfaces.TwoDimMap.TwoDimEntry;
import com.zwp.flyz.maps.HashTwoDimMap;

public class HashTwoDimMapTest {

	
	HashTwoDimMap<Integer,Integer,Integer> map;
	HashMap<Integer,HashMap<Integer,Integer>> hMap; 
	
	@Before
	public void before(){
//		map = new HashTwoDimMap<Integer,Integer,Integer>();
//		for(int i=0;i<14;i++)
//			map.put(i, i, i);
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		map = new HashTwoDimMap<Integer,Integer,Integer>();
		hMap = new HashMap<Integer,HashMap<Integer,Integer>>();
		for(int i=0;i<16;i++){
			//map.put(i+1, i, i);
			map.put(i, i, i);
			//map.put(i, i+1, i);
		}
		
		
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
		
		Iterator<TwoDimEntry<Integer,Integer,Integer>> i = map.entrySet().iterator();
		while(i.hasNext()){
			System.err.println(i.next().toString());
		}
		
		Iterator<TwoDimEntry<Integer,Integer,Integer>> i2 = map.entrySet().iterator();
		while(i2.hasNext()){
			System.err.println(i2.next().toString());
		}
		
		System.err.println(map.toString());
		
		
	}

}
