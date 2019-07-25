package homework.session02;


public interface Stack {

	/**
	 * 入栈
	 * @return boolean
	 */
	boolean push(int value);

	/**
	 * 出栈
	 * @return int
	 */
	int pop();

	/**
	 * 查看栈顶元素
	 */

	int peak();

	/**
	 * 栈的大小
	 */

	int size();

	/**
	 * 栈是否为空
	 * @return
	 */
	boolean isEmpty();

}



package com.Collentions;

import java.util.*;

public class CollectionsDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ArrayList list=new ArrayList();
		list.add("aaaa");
		list.add("cc");
		list.add("z");
		list.add("zz");
		list.add("ddd");
		Collections.sort(list);
		System.out.println(list);
	int index=	Collections.binarySearch(list, "cc");
	System.out.println("index="+index);
		System.out.println(list);
		int t=MyBinarySearch(list, "cc");
		System.out.println("index="+t);
	}
public static    int MyBinarySearch (ArrayList <String > a1,String key)
{
	int max,min,mid;
	max=a1.size()-1;
	min=0;
	
	while (min<=max)
	{
		mid=(max+min)/2;
		String   str =a1.get(mid);
		int num=str.compareTo(key);
		if(num>0)
		{
			max=mid-1;
			
		}
		else if (num<0)
		{
			min=mid+1;
		}
		else 
		return   mid;
}
	return -min-1;
}
}
