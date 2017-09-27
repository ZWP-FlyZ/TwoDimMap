package test;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.zwp.flyz.maps.HashTwoDimMap;

public class HashTwoDimMapTest {

	
	HashTwoDimMap<Integer,Integer,Integer> map;
	HashMap<Integer,Integer> hMap; 
	
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
		hMap = new HashMap<Integer, Integer>();
		for(int i=0;i<16;i++){
			hMap.put(i, i);
			hMap.put(i+16, i);
			map.put(i, i, i);
//			map.put(i%10, i, i);
			map.put(i+16, i+16, i);
		}
		System.err.println();
	}

}
