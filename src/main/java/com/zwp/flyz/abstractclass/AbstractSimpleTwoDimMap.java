package com.zwp.flyz.abstractclass;

import com.zwp.flyz.interfaces.SimpleTwoDimMap;

/**
 * 
 * @author zwp-flyz
 * @Date 2017年9月13日
 * @version 1.0
 * @param <K> the type of two key
 * @param <V> the type of value
 */
public abstract class AbstractSimpleTwoDimMap<K,V> 
							extends AbstractTwoDimMap<K, K, V> implements SimpleTwoDimMap<K, V> {

}
