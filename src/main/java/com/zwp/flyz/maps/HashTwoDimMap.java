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
	static final int DEFAULT_CAPACITY_X = 1 << 4;
	static final int DEFAULT_CAPACITY_Y = 1 << 4;
	
	/**
	 * default max capacity of x and every capacity of y
	 */
	static final int MAX_CAPACITY_X = 1 << 30;
	static final int MAX_CAPACITY_Y = 1 << 30;
	
	/**
	 * default load factory of X and load factory of y 
	 * 
	 */
	static final float DEFAULT_LOAD_FACTOR_X = 0.9375f;
	static final float DEFAULT_LOAD_FACTOR_Y = 0.75f;
	
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
	 * the number of all elements
	 */
	transient int size;
	
	/**
	 * the size and capacity of all y tables 
	 */
	transient StatusY[] statusY=null;
	
	/**
	 * the threshold of x and y
	 */
	 int thresholdX;

	 
	 final float loadFactorX;
	 final float loadFactorY;
	/**
	 * default structure for map use (16,16,1.0f,0.75f)
	 */
	public HashTwoDimMap(){
		this(DEFAULT_CAPACITY_X,DEFAULT_CAPACITY_Y,
				DEFAULT_LOAD_FACTOR_X,DEFAULT_LOAD_FACTOR_Y);
	}
	
	private HashTwoDimMap(int initCapX,int initCapY,
							float initLoadFactorX,float initLoadFactorY){
		if(initCapX<0||initCapY<0) 
				throw new IllegalArgumentException("Illegal capacity( "+initCapX+" , "+initCapY+" )");
		if(initCapX>MAX_CAPACITY_X) initCapX = MAX_CAPACITY_X;
		if(initCapY>MAX_CAPACITY_Y) initCapY = MAX_CAPACITY_Y;
		if(initLoadFactorX<=0||initLoadFactorY<=0) 
			throw new IllegalArgumentException("Illegal loadfactor( "+initLoadFactorX+" , "+initLoadFactorY+" )");
		
		loadFactorX = initLoadFactorX;
		loadFactorY = initLoadFactorY;
		
	}
	
	/**
	 * 
	 * @param which if which == 0 : set thresholdX ; other set thresholdY; 
	 * @param cap the capacity 
	 * @return the threshold
	 */
	final  int getThreshold(int which,int cap){
		if(which==0)
			return (int) (loadFactorX*cap);
		else
			return (int) (loadFactorY*cap);	
	}
	
	
	/**
	 * 
	 * @param o
	 * @return hash
	 * cal the hash
	 */
	static final int hash(Object o){
		return o.hashCode();
	}
	
	/**
	 * the number of all key-value
	 */
	@Override
	public int size(){
		return size;
	}
	
	
	/**
	 * if x==null or y == null : return null;<br>
	 * if isContain(x,y) == false : return null;<br>
	 * other : return V(x,y); // V(x,y)==null is OK;<br>
	 * 
	 * @throws NullPointerException
	 */
	public V get(Object x, Object y) {
		if(x==null||y==null) return null;
		Node<X,Y,V> tmp = getNode(hash(x),hash(y),x,y);
		return tmp==null?null:tmp.value;
	}

	/**
	 * if x==null or y == null : return defV;<br>
	 * if isContain(x,y) == false : return defV;<br>
	 * other : return V(x,y); // V(x,y)==null is OK;<br>
	 * @throws NullPointerException
	 * 
	 */
	public V get(Object x, Object y, V defV) {
		if(x==null||y==null) return null;
		Node<X,Y,V> tmp = getNode(hash(x),hash(y),x,y);
		return tmp==null?defV:tmp.value;
	}

	@Override
	public boolean isContain(Object x, Object y) {
		if(x==null||y==null) return false;
		return getNode(hash(x),hash(y),x,y)!=null;
	}
	
	final Node<X,Y,V> getNode(int hashX,int hashY,Object x, Object y){
		Node<X,Y,V>[][] tabX;Node<X,Y,V>[] tabY;Node<X,Y,V>p;
		int capX;
		tabX = tableX;capX = tabX.length;
		if((tabY=tabX[hashX & (capX -1)])==null) return null;
		if((p=tabY[hashY & (tabY.length -1)])==null) return null;
		while(p!=null){
			if( (p.hashX==hashX && p.hashY == hashY) && 
					(p.x == x || p.x.equals(x)) && 
					(p.y == y || p.y.equals(y)))
			  break;
			p=p.next;
		}
		return p;
	}
	
	
	

	@Override
	public V remove(Object x, Object y) {
		// TODO Auto-generated method stub
		if(x==null||y==null) return null;
		Node<X,Y,V> tmp = removeNode(hash(x),hash(y),x,y);
		return tmp==null?null:tmp.value;
	}
	
	final Node<X,Y,V> removeNode(int hashX,int hashY,Object x, Object y){
		Node<X,Y,V>[][] tabX;Node<X,Y,V>[] tabY;Node<X,Y,V>p,pre=null;
		int capX,indexY;
		tabX = tableX;capX = tabX.length;
		if((tabY=tabX[hashX & (capX -1)])==null) return null;
		if((p=tabY[indexY = hashY & (tabY.length -1)])==null) return null;
		while(p!=null){
			if( (p.hashX==hashX && p.hashY == hashY) && 
					(p.x == x || p.x.equals(x)) && 
					(p.y == y || p.y.equals(y))){
				if(pre==null)
					tabY[indexY] = p.next;
				else
					pre.next = p.next;
			
				break;
			}else
				pre = p;
			p=p.next;  
		}
		return p;	
	}

	@Override
	public void clear() {
		if(size!=0 && tableX!=null){
			size=sizeX=0;
			for(int i=0;i<tableX.length;i++){
				tableX[i]=null;
				statusY[i]=null;
			}
				
		}
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public V put(X x, Y y, V v) {
		if(x==null||y==null) return null;
		return putVal(hash(x),hash(y),x,y,v);
	}

	final V putVal(int hashX,int hashY,X x,Y y,V v){
		int capX ,indexX;
		Node<X,Y,V>[][]tabX = tableX;
		V oV = null;
		if(tabX==null||tabX.length==0)
			tabX = resizeX();
		capX = tabX.length;
		indexX = hashX & (capX-1);
		if((tabX[indexX]==null)) sizeX++;
		oV = putValY(indexX,hashX,hashY,x,y,v);
		
		if(sizeX >= thresholdX)//maybe something is wrong here,if set loadFactor = 1.0.
			resizeX();
		return oV;
	}
	
	/**
	 * put a new value into map
	 * @param indexX the index 
	 * @param hashX the hash of key-x
	 * @param hashY the hash of key-y
	 * @param x
	 * @param y
	 * @param v
	 * @return the replaced value
	 */
	final V putValY(int indexX,int hashX,int hashY,X x,Y y,V v){
		int capY,indexY;
		Node<X,Y,V>[] tabY = tableX[indexX];
		StatusY sy ;
		if((sy = statusY[indexX])==null)
			sy =statusY[indexX] = newStatusY();
		Node<X,Y,V> p,tp=null;
		if(tabY==null||tabY.length == 0)
			tabY = resizeY(indexX);
		capY = tabY.length;
		indexY = hashY & (capY-1);
		if( (p = tabY[indexY])==null)
			tabY[indexY] = newNode(hashX,hashY,x,y,v);//
		else{
			while(true){
				if(p==null){
					tp.next = newNode(hashX,hashY,x,y,v);
					break;
				}
				if( (p.hashX==hashX && p.hashY == hashY) && 
					(p.x == x || p.x.equals(x)) && 
					(p.y == y || p.y.equals(y))){
					break;
				}//end if
				tp = p;
				p = p.next;
			}//end while
		}//end else
		if(p!=null){
			V ov = p.value;
			p.value = v;
			return ov;//return old value;
		}
		size++;
		if( ++sy.sizeY > sy.thresholdY)
			resizeY(indexX);
		return null;
	}
	
	
	/**
	 * put  Node into map 
	 * @param indexX
	 * @param node
	 */
	final void setNodeY(int indexX , Node<X,Y,V> node){
		int capY,indexY;
		Node<X,Y,V>[] tabY = tableX[indexX];
		StatusY sy ;
		if((sy = statusY[indexX])==null)
			sy =statusY[indexX] = newStatusY();
		if(tabY==null||tabY.length == 0)
			tabY = resizeY(indexX);
		capY = tabY.length;
		indexY = node.hashY & (capY-1);
		if((node.next=tabY[indexY])==null)//change nodes' order here!!
				sizeX++;
		tabY[indexY] = node;

		if( ++sy.sizeY > sy.thresholdY)
			resizeY(indexX);
	
	}
	
	
	/**
	 * resize for X
	 * @return
	 */
	final Node<X,Y,V>[][] resizeX(){
		Node<X,Y,V>[][] oTableX = tableX;
		StatusY[] oStatuY = statusY;
		int oCapX,oTholdX;
		int nCapX,nTholdX;
		oCapX = (tableX==null?0:tableX.length);
		oTholdX = thresholdX;
		if(oCapX<=0){//init the tableX argm
			nCapX = DEFAULT_CAPACITY_X;
			nTholdX = getThreshold(0,nCapX);
		}else if(oCapX >= MAX_CAPACITY_X){
			nCapX = MAX_CAPACITY_X;
			nTholdX= Integer.MAX_VALUE;
			return oTableX;//check 
		}else{
			nCapX = oCapX << 1;
			nTholdX = oTholdX<<1;
		}
		
		thresholdX = nTholdX;	
		@SuppressWarnings("unchecked")
		Node<X,Y,V>[][] nTableX = (Node<X,Y,V>[][])new Node[nCapX][];
		StatusY[] nStatusY = new  StatusY[nCapX];
		tableX = nTableX;
		statusY = nStatusY;
		if(oTableX!=null){
			// rehash here
			System.arraycopy(oStatuY, 0, nStatusY, 0, oStatuY.length);//copy the status
			System.arraycopy(oTableX, 0, nTableX, 0, oTableX.length);//copy the tabx
			Node<X,Y,V>[] ys ;
			Node<X,Y,V> p,pre = null;
			for(int i=0;i<oCapX;i++){
				if((ys=nTableX[i])==null) continue;
					for(int j=0;j<ys.length;j++){
						if((p=ys[j])==null) continue;
						while(p!=null){
							if((p.hashX&oCapX)!=0){
								if(pre==null)
									ys[j]=p.next;
								else
									pre.next = p.next;
								nStatusY[i].sizeY--;
								setNodeY(i+oCapX,p);
							}else
								pre = p;
							p = p.next;
						}//end while
					}//end for j
			}//end for i
		}
		
		return nTableX;
	}
	
	/**
	 * resize for Y
	 * @param indexX
	 * @return
	 */

	final Node<X,Y,V>[] resizeY(int indexX){
		Node<X,Y,V>[] oTableY = tableX[indexX];
		int oCapY,oTholdY;
		int nCapY,nTholdY;
		StatusY sy = statusY[indexX];
		oCapY = oTableY==null?0:oTableY.length;

		oTholdY = sy.thresholdY;
		if(oCapY<=0){
			nCapY = DEFAULT_CAPACITY_Y;
			nTholdY = getThreshold(1, nCapY);
		}else if(oCapY>=MAX_CAPACITY_Y){
			nCapY = MAX_CAPACITY_Y;
			nTholdY = Integer.MAX_VALUE;
			return oTableY;
		}else{
			nCapY = oCapY<<1;
			nTholdY = oTholdY<<1;
		}
		sy.thresholdY = nTholdY;
		@SuppressWarnings("unchecked")
		Node<X,Y,V>[] nTableY = (Node<X,Y,V>[]) new Node[nCapY];
		tableX[indexX] = nTableY;
		if(oTableY!=null){
			// rehash here
			Node<X,Y,V> p,pre;
			Node<X,Y,V> oHead;
			Node<X,Y,V> nHead,nTail;
			for(int i=0;i<oCapY;++i){
				if((oHead=oTableY[i])!=null){
					p = oHead;
					nHead = nTail = pre =null;
					while(p!=null){
						if((p.hashY & oCapY)!=0){
							if(pre==null)
								oHead = p.next;
							else
								pre.next = p.next;
							
							if(nTail==null)
								nHead = nTail = p;
							else{
								nTail.next=p;
								nTail = p;
							}//end else
						}//end if p.hashY
						else
							pre = p;//change the pre if don't rehash
						p = p.next;
					}//end while p
					//if(pre==null) oHead = null;
					nTableY[i] = oHead;
					nTableY[i+oCapY] = nHead;
					if(nTail!=null) nTail.next=null;
				}//end if oHead
			}//end for
		}
		return nTableY;	
	}
	
	final Node<X,Y,V> newNode(int hashX,int hashY,X x,Y y,V v){
		return new Node<X, Y, V>(hashX,hashY,x,y,v,null);
	}
	
	final StatusY newStatusY(){
		return new StatusY();
	}
	
	
	
	@Override
	public Collection<TwoDimEntry<X, Y, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
