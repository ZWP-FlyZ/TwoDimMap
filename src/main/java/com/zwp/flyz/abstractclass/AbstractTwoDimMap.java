package com.zwp.flyz.abstractclass;

import java.util.Collection;
import java.util.Iterator;

import com.zwp.flyz.interfaces.TwoDimMap;



/**
 * 
 * @author zwp12
 *
 * @param <X> the type of  key1 
 * @param <Y> the type of  key2
 * @param <V> the type of value
 * 
 * 
 */
public abstract class AbstractTwoDimMap<X, Y, V> implements TwoDimMap<X, Y, V> {

	/**
	 * if x==null or y == null : return null;<br>
	 * if isContain(x,y) == false : return null;<br>
	 * other : return V(x,y); // V(x,y)==null is OK;<br>
	 * 
	 * @throws NullPointerException
	 * 
	 */
	public V get(X x, Y y) {
		if(x==null||y==null) return null;
		Iterator<TwoDimEntry<X, Y, V>> iterator = entrySet().iterator();
		TwoDimEntry<X, Y, V> tmp = null;
		while(iterator.hasNext()){
			tmp = iterator.next();
			if(x.equals(tmp.getX())&&
					y.equals(tmp.getY()))
				return tmp.getValue();
		}
		return null;
	}

	
	
	
	/**
	 * if x==null or y == null : return defV;<br>
	 * if isContain(x,y) == false : return defV;<br>
	 * other : return V(x,y); // V(x,y)==null is OK;<br>
	 * 
	 * 
	 * @throws NullPointerException
	 * 
	 */
	public V get(X x, Y y, V defV) {
		if(x==null||y==null) return defV;
		Iterator<TwoDimEntry<X, Y, V>> iterator = entrySet().iterator();
		TwoDimEntry<X, Y, V> tmp = null;
		while(iterator.hasNext()){
			tmp = iterator.next();
			if(x.equals(tmp.getX())&&
					y.equals(tmp.getY()))
				return tmp.getValue();
		}
		return defV;
	}
	
	
	
	
	
	/**
	 * 
	 * WARN: throws UnsupportedOperationException
	 * 
	 * 
	 * @throws NullPointerException
	 * @throws UnsupportedOperationException
	 * 
	 */
	
	public V put(X x, Y y, V v) {
		throw new UnsupportedOperationException("Override put()!");
	}

	
	/**
	 * 
	 * 
	 * 
	 * if x==null or y == null : return null;<br>
	 * if isContain(x,y) == false : return null;<br>
	 * other : remove V(x,y)  and return V(x,y); // V(x,y)==null is OK;<br>
	 * 
	 * 
	 * @throws NullPointerException
	 * @throws UnsupportedOperationException
	 * 
	 */
	
	public V remove(X x, Y y) {
		
		return null;
	}

	public boolean isContain(X x, Y y) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	public void clear() {
		entrySet().clear();
	}

	/**
	 * @return 返回所有条目
	 */
	public int size() {
		
		return entrySet().size();
	}

	/**
	 * @return size()==0 
	 */
	public boolean isEmpty() {
		
		return size()==0;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TwoDimKey<X, Y>> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract Collection<TwoDimEntry<X, Y, V>> entrySet();

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	
}
