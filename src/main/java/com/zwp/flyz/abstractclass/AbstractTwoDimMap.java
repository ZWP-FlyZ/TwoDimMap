package com.zwp.flyz.abstractclass;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

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
		
		if(x==null||y==null) return null;
		Iterator<TwoDimEntry<X,Y,V>> it = entrySet().iterator();
		V tmpV= null;
		TwoDimEntry<X,Y,V> tmp = null;
		while(it.hasNext()){
			tmp = it.next();
			if(x.equals(tmp.getX())&&y.equals(tmp.getY())){
				tmpV = tmp.getValue();
				it.remove();
				break;
			}
		}
		
		return tmpV;
	}

	/**
	 * 
	 * 
	 * 
	 * if x==null or y == null : return false;<br>
	 * only if V(x,y) in entrySet : return true;<br>
	 * other : return false;<br>
	 * 
	 * 
	 * @throws NullPointerException
	 * 
	 * 
	 */
	public boolean isContain(X x, Y y) {
		if(x==null||y==null) return false;
		Iterator<TwoDimEntry<X, Y, V>> iterator = entrySet().iterator();
		TwoDimEntry<X, Y, V> tmp = null;
		while(iterator.hasNext()){
			tmp = iterator.next();
			if(x.equals(tmp.getX())&&
					y.equals(tmp.getY()))
				return true;
		}
		return false;
	}

	/**
	 * clear up
	 */
	public void clear() {
		entrySet().clear();
	}

	/**
	 * @return size of all
	 */
	public int size() {
		
		return entrySet().size();
	}

	/**
	 * @return size()==0 ? 
	 */
	public boolean isEmpty() {
		
		return size()==0;
	}

	
	/**
	 * the template for keySet and value
	 */
    private transient volatile AbstractSet<com.zwp.flyz.interfaces.TwoDimMap.TwoDimKey<X, Y>>  keySet;
    private transient volatile Collection<V> values;
	
	public Collection<V> values() {
		if(values==null){
			values = new AbstractCollection<V>() {

				@Override
				public Iterator<V> iterator() {
					return new Iterator<V>(){
						private Iterator<TwoDimEntry<X,Y,V>> i = 
								entrySet().iterator();
						public boolean hasNext() {
							return i.hasNext();
						}

						public V next() {
							return i.next().getValue();
						}
						
					};
				}

				@Override
				public int size() {
					// TODO Auto-generated method stub
					return AbstractTwoDimMap.this.size();
				}
			};
		}
		return values;
	}

	public Set<TwoDimKey<X, Y>> keySet() {
		if(keySet==null){
			keySet = new AbstractSet<TwoDimKey<X, Y>>(){
				@Override
				public Iterator<TwoDimKey<X, Y>> iterator() {
					return new Iterator<TwoDimKey<X, Y>>(){
						private Iterator<TwoDimEntry<X,Y,V>> i = 
											entrySet().iterator();
						public boolean hasNext() {
							return i.hasNext();
						}

						public TwoDimKey<X, Y> next() {
							TwoDimKey<X, Y> td = new TwoDimKeyImp<X, Y>();
							TwoDimEntry<X,Y,V> tmp = i.next();
							td.setX(tmp.getX());
							td.setY(tmp.getY());
							return td;
						}

						public void remove() {
							i.remove();
						}
						
						
					};
				}

				@Override
				public int size() {
					return AbstractTwoDimMap.this.size();
				}
				
			};
		}
		return keySet;
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


	public static class TwoDimKeyImp<X,Y> implements TwoDimKey<X, Y>,Serializable{

		private static final long serialVersionUID = 12377777L;
		private X x;
		private Y y;
		public X getX() {
			return x;
		}
		public void setX(X x) {
			this.x = x;
		}
		public Y getY() {
			return y;
		}
		public void setY(Y y) {
			this.y = y;
		}
		
		

		
	}
	
	
	
	
	
}
