package com.zwp.flyz.interfaces;

import java.util.Collection;

/**
 * 
 * @author zwp12
 *
 * @param <X>
 * @param <Y>
 * @param <V>
 */

public interface TwoDimMap<X,Y,V> {
	
	public V get(X x,Y y);
	
	public V get(X x,Y y, V defV);
	
	public V put(X x,Y y,V v);
	
	public V remove(X x,Y y);
	
	public boolean isContainV(X x,Y y);
	
	public void clear();
	
	public int size();
	
	public boolean isEmpty();
	
	public Collection<V> values();
	
	public Collection<TwoDimKey<X,Y>> keys();
	
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
	}

}
