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
 * @author zwp-flyz
 * @Date 2017年9月7日
 * @version 1.0
 * @param <X> the type of  key1 
 * @param <Y> the type of  key2
 * @param <V> the type of value
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
	public V get(Object x, Object y) {
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
	public V get(Object x, Object y, V defV) {
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
	
	public V remove(Object x, Object y) {
		
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
	public boolean isContain(Object x, Object y) {
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
	protected transient volatile AbstractSet<TwoDimKey<X, Y>>  keySet;
    protected transient volatile Collection<V> values;
	
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
							TwoDimEntry<X,Y,V> tmp = i.next();
							TwoDimKey<X, Y> td = new TwoDimKeyImp<X, Y>(tmp.getX(),tmp.getY());
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
	
	public abstract Set<TwoDimEntry<X, Y, V>> entrySet();

	@Override
	public int hashCode() {
		int hash = 0;
		Iterator<TwoDimEntry<X, Y, V>> i = entrySet().iterator();
		while(i.hasNext()){
			hash+=i.next().hashCode();
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(!( obj instanceof TwoDimMap) ) return false;
		
		try {
			
			TwoDimMap<?,?,?> map = (TwoDimMap<?,?,?>)obj;
			if(map.size()!=this.size()) return false;
			Iterator<TwoDimEntry<X,Y,V>> iterator = entrySet().iterator();
			TwoDimEntry<X,Y,V> entry = null;
			X tx = null;
			Y ty = null;
			V tv = null;
			
			while(iterator.hasNext()){
				entry = iterator.next();
				tx = entry.getX();
				ty = entry.getY();
				tv = entry.getValue();
				if(!map.isContain(tx, ty)) return false;
				map.get(tx, ty);
				
				if(tv==null){
					if(!map.isContain(tx, ty)||map.get(tx, ty)!=null)
						return false;	
				}else{
					if(!tv.equals(map.get(tx, ty)))  return false;
					
				}		
			}	
		}  catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
        return true;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		AbstractTwoDimMap<?, ?, ?> map = (AbstractTwoDimMap<?, ?, ?>)super.clone();
		map.keySet =null;
		map.values =null;
		return map;
	}

	@Override
	public String toString() {
		Iterator<TwoDimEntry<X,Y,V>> iterator = entrySet().iterator();
		if(!iterator.hasNext()) return "{}";
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		TwoDimEntry<X,Y,V> tde = null;
		while(true){
			tde = iterator.next();
			sb.append("(").
				append(tde.getX()).
				append(",").
				append(tde.getY()).
				append(")");
			sb.append("->").append(tde.getValue());
			
			if(!iterator.hasNext()){
				sb.append("}");
				return sb.toString();
			}
			sb.append(" | ");
		}
		
	}


	public static class TwoDimKeyImp<X,Y> implements TwoDimKey<X, Y>,Serializable{

		private static final long serialVersionUID = -3391093777123254603L;
		private final X x;
		private final Y y;
		public TwoDimKeyImp(X x,Y y){
			this.x = x;
			this.y = y;
		}
		public X getX() {
			return x;
		}

		public Y getY() {
			return y;
		}
	
	}
	
	public static class TwoDimEntryImp<X,Y,V> implements TwoDimEntry<X, Y, V>,Serializable{

		private static final long serialVersionUID = -3053363433555884934L;
		
		private final X x;
		private final Y y;
		private V v;
		
		public TwoDimEntryImp(X x,Y y,V v){
			this.x = x;
			this.y = y;
			this.v = v;
		}
		public X getX() {
			// TODO Auto-generated method stub
			return x;
		}

		public Y getY() {
			// TODO Auto-generated method stub
			return y;
		}

		public V getValue() {
			return v;
		}

		public V setValue(V v) {
			V ov = this.v;
			this.v = v;
			return ov;
		}
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("( ");
			sb.append(x.toString());
			sb.append(" , ");
			sb.append(y.toString());
			sb.append(" )-->( ");
			sb.append(v.toString());
			sb.append(" )");
			return sb.toString();
		}

		
	}
	
	
	
	
	
}
