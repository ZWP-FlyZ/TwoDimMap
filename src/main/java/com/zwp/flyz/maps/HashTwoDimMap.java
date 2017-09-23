package com.zwp.flyz.maps;

import java.io.Serializable;
import java.util.Collection;

import com.zwp.flyz.abstractclass.AbstractTwoDimMap;
import com.zwp.flyz.interfaces.TwoDimMap;

/**
 * 
 * @author zwp-flyz
 * @Date 2017年9月13日
 * @version 1.0
 * @see java.util.HashMap
 * this class is base on jdk HashMap
 */

public class HashTwoDimMap<X,Y,V> extends AbstractTwoDimMap<X, Y, V> 
			implements TwoDimMap<X, Y, V> ,Serializable,Cloneable{

	private static final long serialVersionUID = -2812755994082351258L;
	
	
	/**
	 * default  capacity of x and capacity of y
	 */
	static final int DEFAULT_CAPACITY_X = 16;
	static final int DEFAULT_CAPACITY_Y = 16;
	
	/**
	 * default max capacity of x and every capacity of y
	 */
	static final int MAX_CAPACITY_X = 16;
	static final int MAX_CAPACITY_Y = 16;
	
	/**
	 * default load factory of X and load factory of y 
	 * 
	 */
	static final float DEFAULT_LOAD_FACTORY_X = 1.0f;
	static final float DEFAULT_LOAD_FACTORY_Y = 0.75f;
	
	/**
	 * 
	 * the Node for TwoDimMap 
	 */
	static class Node<X,Y,V> implements TwoDimEntry<X,Y,V>{

		final int hashX;
		final int hashY;
		final X x;
		final Y y;
		V value;
		Node<X,Y,V> next;
		
		public Node(int hashX,int hashY,X x,Y y,V value,Node<X,Y,V> next){
			this.hashX = hashX;
			this.hashY = hashY;
			this.x = x;
			this.y = y;
			this.value = value;
			this.next = next;
		}
		
		public X getX() {
			return x;
		}

		public Y getY() {
			return y;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V v) {
			V ov = value;
			this.value = v;
			return ov;
		}

		@Override
		public boolean equals(Object obj) {
			if(this==obj) return true;
			if(! (obj instanceof TwoDimEntry)) return false;
			TwoDimEntry<?,?,?> no = (TwoDimEntry<?,?,?>)obj;
			if(!x.equals(no.getX())||!y.equals(no.getY())||
						!value.equals(no.getValue()))
				return false;
			return true;
		}
		
		
		
		
	}
	
	static class StatusY{
		int sizeY;
		int capacityY;
		int thresholdY;
	} 
	
	/**
	 *  the table of x 
	 */
	transient Node<X,Y,V>[][] tableX = null;
	
	/**
	 * the number of key-x
	 */
	transient int sizeX;
	
	/**
	 * the size and capacity of all y tables 
	 */
	transient StatusY[] statusY=null;
	/**
	 * the capacity of X
	 */
	transient int capacityX;
	
	/**
	 * the threshold of x and y
	 */
	transient float thresholdX;
	transient float thresholdY;
	
	
	
	/**
	 * 
	 * @param o
	 * @return hash
	 * cal the hash
	 */
	static final int hash(Object o){
		return o.hashCode();
	}
	
	
	
	
	
	@Override
	public Collection<TwoDimEntry<X, Y, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
