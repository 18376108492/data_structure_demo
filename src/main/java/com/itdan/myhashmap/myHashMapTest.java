package com.itdan.myhashmap;

/**
 * MyHashMap测试类
 */
public class myHashMapTest {

    public static void main(String[] args) {

        //测试
        MyHashMap<String,Object> map=new MyHashMap<String, Object>();


        map.put("aa",18);
        map.put("bb",18);
        map.put("V",18);
        map.put("dd",18);
        map.put("ff",18);
        map.put("A",18);
        map.put("B",18);
        map.put("ii",18);
        map.put("C",18);
        map.put("1",18);
        map.put("2",18);
        map.put("3",18);
        map.put("h",18);
        map.put("K",18);
        map.put("n",18);
        map.put("m",18);
        map.put("y",18);
        map.put("i",18);
        map.put("u",18);
        map.put("a",18);
        map.put("b",18);
        map.put("aa",22);
        map.put("bb",34);

        //map.remove("aa");

        System.out.println(map.size());
        map.print();

        System.out.println("aa:"+map.getV("aa"));


    }

}
