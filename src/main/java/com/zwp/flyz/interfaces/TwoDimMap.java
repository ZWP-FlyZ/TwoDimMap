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
	
	public V get(X x,Y y);
	
	public V get(X x,Y y, V defV);
	
	public V put(X x,Y y,V v);
	
	public V remove(X x,Y y);
	
	public boolean isContain(X x,Y y);
	
	public void clear();
	
	public int size();
	
	public boolean isEmpty();
	
	public Collection<V> values();
	
	public Set<TwoDimKey<X,Y>> keySet();
	
	public Collection<TwoDimEntry<X,Y,V>> entrySet();
	
	public boolean equals(Object o);
	
	public interface TwoDimKey<X,Y>{
		X getX();
		void setX(X x);
		Y getY();
		void setY(Y y);
	}
	
	public interface Entry<K,V>{
		K getKey();
		V getValue();
	}
	
	public interface TwoDimEntry<X,Y,V>{
		X getX();
		Y getY();
		V getValue();
		
		void setX();
		void setY();
		void setValue();	
		
		
	}

}
