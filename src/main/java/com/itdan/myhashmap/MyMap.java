package com.itdan.myhashmap;

//类似于源码的Map接口
public interface MyMap<K,V> {
    /**
     * 向map中添加键值对的方法
     * @param k
     * @param v
     * @return
     */
    V put(K k,V v);

    /**
     * 根据传入的键来获取出map中的值
     * @param k
     * @return
     */
    V getV(K k);

    /**
     * 根据传入的键删除对应的值
     * @param k
     * @return
     */
    void remove(K k);

    /**
     * Entry 键值对接口
     */
    interface Entry<K,V>{
         K getK();
         V getV();

        /**
         * 存入新的Value值时，并返回旧的Value值
         * @param v
         * @return
         */
         V setV(V v);
    }

}
