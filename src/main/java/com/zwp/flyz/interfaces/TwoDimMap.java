package com.zwp.flyz.interfaces;

import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author zwp12
 *
 * @param <X>
 * @param <Y>
 * @param <V>
 */

public interface TwoDimMap<X,Y,V> {
	
	public V get(Object x,Object y);
	
	public V get(Object x,Object y, V defV);
	
	public V put(X x,Y y,V v);
	
	public V remove(Object x,Object y);
	
	public boolean isContain(Object x,Object y);
	
	public void clear();
	
	public int size();
	
	public boolean isEmpty();
	
	public Collection<V> values();
	
	public Set<TwoDimKey<X,Y>> keySet();
	
	public Collection<TwoDimEntry<X,Y,V>> entrySet();
	
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
