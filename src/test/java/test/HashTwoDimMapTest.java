package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zwp.flyz.maps.HashTwoDimMap;

public class HashTwoDimMapTest {

	
	HashTwoDimMap<Integer,Integer,Integer> map;
	
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
		for(int i=0;i<20;i++){
			map.put(1, i, i);
//			map.put(i%10, i, i);
			map.put(1, i+16, i);
		}
		System.err.println();
	}

}
