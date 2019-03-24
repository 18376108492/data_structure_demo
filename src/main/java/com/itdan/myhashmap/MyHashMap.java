package com.itdan.myhashmap;

import java.util.HashMap;

/**
 * 1.7 数组+单链表实现hashmap
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K,V> implements MyMap<K,V> {

    //创建table容器
    private  Node<K,V>[]table=null;

    //容器的值数量
    private static int size;

    //加载因子,加载因子越小，map的效率越高(当table里的数据长度为16*0.75时，table会自动扩容)
    private static float DEFAULT_LOAD_FACTOR=0.75f;

    //table初始长度
    private static int DEFAULT_INITIAL_CAPACTIY=16;//1<<4

    public MyHashMap(){
        this.table=null;;
    }

    public MyHashMap(int intialcapactiy){
        if (intialcapactiy<0){
            throw new RuntimeException("初始长度小于0");
        }
        this.table=new Node[intialcapactiy];
    }




    public V put(K k, V v) {
        //1.先判断table是否为null
        if(table==null){
            table=new Node[DEFAULT_INITIAL_CAPACTIY];
        }
        //2.判断table容器长度是否达到要扩容的长度(扩容机制)
        if(size>(DEFAULT_LOAD_FACTOR*DEFAULT_INITIAL_CAPACTIY)){
            //调用扩容方法
             resize();
        }


        //3.解决hash冲突
        //计算key的在table中的下标值index
        int index=getIndex(k,DEFAULT_INITIAL_CAPACTIY);
        //获取table该下标是否存在节点，如果不存在下标冲突，直接将节点存入其中
         Node<K,V> node=table[index];
        //判断新存入的key是否已经存在table中
        if(node==null){
            //作为table该下标的第一个节点所以next为null
            node=new Node<K, V>(k,v,null);
            size++;
        }else{
            //当table该下标的节点不为null时,先要判断该传入的key是否为已经存在
           /* Node<K,V>newNode=node;
             while (newNode!=null){
                 //判断key值是否相同
                 if(newNode.getK().equals(k) || newNode.getK()==k){
                     //当key相同时，说明为同一对象,需要修改当前的value值就行
                     V oldValue = newNode.setV(v);
                     node=newNode;
                     return oldValue;
                 }else {
                     //当key不相同时，需要将该节点作为一个新的节点存入，并将已有的节点作为下一个节点
                     if(newNode.next==null){
                         node=new Node<K, V>(k,v,node);
                         size++;
                     }
                 }
                 newNode=newNode.next;
             }*/

             //代码整改
            for (Node<K,V>newNode=node;newNode!=null;newNode=newNode.next) {
                if(newNode.getK().equals(k) || newNode.getK()==k){
                    //当key相同时，说明为同一对象,需要修改当前的value值就行
                    V oldValue = newNode.setV(v);
                    node=newNode;
                    return oldValue;
                }else {
                    //当key不相同时，需要将该节点作为一个新的节点存入，并将已有的节点作为下一个节点
                    if(newNode.next==null){
                        node=new Node<K, V>(k,v,node);
                        size++;
                    }
                }
            }

        }
        table[index]=node;
        return null;
    }

    public V getV(K k) {
        //根据传入的k获取value值
        //计算k的下标
        int index = getIndex(k, table.length);
        Node node = table[index];
        while (node != null) {
            if (node.getK().equals(k)) {
                return (V) node.getV();
            }
            node = node.next;
        }
        return null;
    }

    //删除节点测试有错（多节点）
    public void remove(K k) {
        //根据传入的k获取value值
        //计算k的下标
        int index= getIndex(k,table.length);
        Node node=table[index];
        //遍历节点
        if(node.next!=null){

        while (node!=null){
             if(node.getK().equals(k)){
               //判断其是否有下一个节点
               //当key相同时，判断该节点是否还有下一个节点，将上一个节点的
                Node nodeNext=node.next;
                node=null;
                node=nodeNext;
             }
        }
        }else {
            //table只有一个节点时
            table[index]=null;
        }
    }

    /**
     * 通过key计算出hash值，并计算出index的值
     * @param k
     * @param tableLength
     * @return
     */
    public int getIndex(K k,int tableLength){
        //计算hash值
        int index=k==null?0:k.hashCode() % tableLength;
        return index;
    }

    /**
     * 扩容机制
     */
    public void resize(){
        //先进行扩容，再重新计算hash和节点的index值
       Node[] newTable=new Node[DEFAULT_INITIAL_CAPACTIY<<1];
        //重新计算index
        for (int i = 0; i <table.length ; i++) {
            //将旧table下的节点移动到新table的新坐标下
            Node oldNode=table[i];
            //遍历单链表
            while (oldNode!=null){
                //获取下一个节点
                Node nextNode=oldNode.next;
                //计算新的index值
                K key=(K) oldNode.getK();
                int newIndex= getIndex(key,newTable.length);
               //先吧下一个节点作为第一个节点
                oldNode.next=newTable[newIndex];
                //将旧的节点
                newTable[newIndex]=oldNode;
                oldNode=nextNode;
            }

        }
        //将newTable赋值给table
        table=newTable;
        DEFAULT_INITIAL_CAPACTIY=newTable.length;
        newTable=null;
    }


    /**
     * 打印map方法
     */
    public void print(){

        for (int i=0;i<table.length;i++){
            Node<K,V> node=table[i];
            System.out.print("节点的下标为:"+i);
           while (node!=null){
                System.out.print(",[key:"+node.getK()+",value:"+node.getV()+"]");
                node=node.next;
            }
            System.out.println();
        }
    }

    /**
     * 返回元素数量
     * @return
     */
    public int size(){
        return size;
    }


    //单链表节点类
    class Node<K,V> implements MyMap.Entry<K,V> {

        private  K key;
        private  V value;
        private  Node<K,V> next;//下一个节点

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getK(){
          return this.key;
        }
        public  V getV(){
           return this.value;
        }
        public  V setV(V v){
           V oldV=this.value;
           this.value=v;
           return oldV;
        }

    }

}


