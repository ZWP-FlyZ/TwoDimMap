package com.zwp.flyz.abstractclass;

import java.util.Collection;

import com.zwp.flyz.interfaces.TwoDimMap;

public abstract class AbstractTwoDimMap<X, Y, V> implements TwoDimMap<X, Y, V> {

	public V get(X x, Y y) {
		// TODO Auto-generated method stub
		return null;
	}

	public V get(X x, Y y, V defV) {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(X x, Y y, V v) {
		// TODO Auto-generated method stub
		return null;
	}

	public V remove(X x, Y y) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isContainV(X x, Y y) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TwoDimKey<X, Y>> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TwoDimEntry<X, Y, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

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
