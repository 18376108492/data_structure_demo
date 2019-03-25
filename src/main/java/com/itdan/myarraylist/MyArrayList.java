package com.itdan.myarraylist;

import java.util.Arrays;

//简单实现MyAraayList
public class MyArrayList {
	//设置数组默认长度
	private final int DEFAULT_CAPACITY = 10;
	//初始化数组
	private Object[] element = null;
	/*public ArrayList(int initialCapacity) {
	    if (initialCapacity > 0) {
	        this.elementmentData = new Object[initialCapacity];
	    } else if (initialCapacity == 0) {
	        this.elementmentData = EMPTY_elementMENTDATA;
	    } else {
	        throw new IllegalArgumentException("Illegal Capacity: "+
	                                           initialCapacity);
	    }
	}*/

	private int size = 0;

	/**
	 * 自定义数组长度参数构造
	 * @param initialCapacity 初始长度
	 */
	MyArrayList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalAccessError("初始长度小于0");
		}
		this.element = new Object[initialCapacity];
	}

	/**
	 * 默认构造器
	 */
	MyArrayList() {//initialCapacity==0
		this.element = new Object[DEFAULT_CAPACITY];
	}

	/**
	 * 添加操作
	 * @param ele
	 */
	public void add(Object ele) {
		//判断元素个数是否大于等于数组长度
		if (size >= element.length) {
			Object[] newElement = Arrays.copyOf(element, element.length * 2);//扩容操作
			element = newElement;
		}
		//添加元素
		element[size] = ele;
		size++;
	}

	/**
	 * 将元素添加到指定的位置
	 * @param index
	 * @param ele
	 */
	public void add(int index, Object ele) {
           element[index]=ele;
	}

	/**
	 * 根据下标索引获取元素值
	 * @param index
	 * @return
	 */
	public Object getElement(int index) {
		//判断索引是否越界
		if (index < 0 || index >= size) {
			throw new IllegalAccessError("索引越界");
		}
		return element[index];
	}

	/**
	 * 根据传入的元素获取该元素所在的位置（从0开始遍历，获取离零最近的下标）
	 * @param ele
	 * @return
	 */
	public int IndexOf(Object ele) {
		if (ele == null) {
			for (int i = 0; i < size; i++) {
				if (element[i] == null) {
					return i;
				}
			}
		}else {
					for (int j = 0; j < element.length; j++) {
						if (element[j].equals(ele)) {
							return j;
						}
					}
				}
		return -1;
	}

	/**
	 * 根据传入的元素获取该元素所在的位置（从数组后面开始遍历，获取最后一个元素的下标）
	 * @param ele
	 * @return
	 */
	public int lastIndexOf(Object ele) {
		if (ele == null) {
			for (int i = size - 1; i >= 0; i--) {
				if (element[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (element[i] == ele) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 *制定的位置下保存元素
	 * @param index
	 * @param ele
	 */
	public void set(int index, Object ele) {
		if (index < 0 || index >= size) {
			throw new IllegalAccessError("索引越界");
		}
		for (int i = 0; i < size; i++) {
			if (i == index) {
				element[index] = ele;
			}
		}
	}

	/**
	 * 删除操作
	 * @param index
	 */
	public void delete(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalAccessError("索引越界");
		}
		for (int i = index; i < size - 1; i++) {
			element[i] = element[i + 1];
		}
		element[size - 1] = null;
	}
	public void delete2(int index){
		if (index < 0 || index >= size) {
			throw new IllegalAccessError("索引越界");
		}
		int length=size-index-1;//计算复制的第一个元素位置
		//复制删除的元素后面的元素
		System.arraycopy( element, index, element, index+1, length);
		element[size-1]=null;
	}

	/**
	 * 返回元素个数
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 打印方法
	 */
	public void print(){
	   StringBuilder stringBuilder=new StringBuilder(element.length);
		stringBuilder.append("[");
	   for (int i = 0; i <size ; i++) {
			stringBuilder.append(element[i]);
			stringBuilder.append(",");
	   	}
	    stringBuilder.append("]");
		System.out.println(stringBuilder.toString());
	}
}
