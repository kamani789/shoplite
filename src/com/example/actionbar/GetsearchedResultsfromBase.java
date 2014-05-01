package com.example.actionbar;

import java.util.ArrayList;
import java.util.ListIterator;

import com.example.serviceclasses.ProductList;

public class GetsearchedResultsfromBase {

	public String[] getitem(String name)
	{
		System.out.println("searchable item is"+name);
		String ret[]=null;
		ProductList p=new ProductList();
		ArrayList<String[]> a=p.getproductinfo();
		System.out.println("array list size"+a.size());
		ListIterator<String[]> li=a.listIterator();
		while(li.hasNext())
		{
			System.out.println("inside list iterator has next");
			String m[]=li.next();
			System.out.println("m[0] is"+m[0]);
			for(int i=0;i<m.length;i++)
			{
				if(m[i].toString().equalsIgnoreCase(name))
				{
					System.out.println("inside matching of item name");
					ret=m;
					
				}
				
			}
		}
	
		System.out.println("product before returning "+ret[0]);
		return ret;
	}
	
}
