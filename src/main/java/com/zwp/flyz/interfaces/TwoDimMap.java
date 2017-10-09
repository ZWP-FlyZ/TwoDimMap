package com.zwp.flyz.interfaces;

import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author zwp-flyz
 * @Date 2017年9月7日
 * @version 1.0
 * 
 */

public interface TwoDimMap<X,Y,V> {
	
	/**
	 * 
	 * @param x the key x
	 * @param y the key y
	 * @return the value of key (x,y).
	 */
	public V get(Object x,Object y);
	
	/**
	 * 
	 * @param x the key x
	 * @param y the key y
	 * @param defV the default value if not contain value
	 * @return
	 */
	public V get(Object x,Object y, V defV);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param v
	 * @return
	 */
	public V put(X x,Y y,V v);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public V remove(Object x,Object y);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isContain(Object x,Object y);
	
	/**
	 * clear all elements
	 */
	public void clear();
	
	/**
	 * 
	 * @return the number of all elements
	 */
	public int size();
	
	/**
	 * 
	 * @return return true is the map is empty
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @return all values
	 */
	public Collection<V> values();
	
	/**
	 * 
	 * @return all keys (x,y)
	 */
	public Set<TwoDimKey<X,Y>> keySet();
	
	public Set<TwoDimEntry<X,Y,V>> entrySet();
	
	public boolean equals(Object o);
	
	public interface TwoDimKey<X,Y>{
		X getX();
		Y getY();
	}
	
	public interface Entry<K,V>{
		K getKey();
		V getValue();
	}
	
	public interface TwoDimEntry<X,Y,V>{
		X getX();
		Y getY();
		V getValue();
		
		V setValue(V v);	
		
		
	}

}
