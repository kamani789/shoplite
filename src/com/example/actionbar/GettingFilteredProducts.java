package com.example.actionbar;

import java.util.Iterator;
import java.util.List;

public class GettingFilteredProducts {
	private List<ModelProducts> mProductList;
	
	public List<ModelProducts> getproductlist(String Selected,List<ModelProducts> m,int groupposition,int childposition)
	{
		System.out.println("size of model products is"+m.size());
		//mProductList=m;
		Iterator<ModelProducts> itr=m.iterator();
		System.out.println("selected is"+Selected);
		int i=0;
		while(itr.hasNext())
		{
			System.out.println("at starting of iteraing");
			ModelProducts mp=(ModelProducts)itr.next();
			System.out.println("product description is"+mp.getProductDesc());
			if(groupposition==0)
			{
				System.out.println("inside groupposition=0");
			if(!(mp.getProductbrand().contains(Selected)))
			{
				itr.remove();
				System.out.println("size of m is"+m.size());
			}
			}
			else if (groupposition==1)
			{
				System.out.println("inside groupposition=1 and child position "+childposition);
				
            	if(childposition==0)
            	{
            		
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		if(!(itemprice<=25.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
            	else if(childposition==1)
            	{
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		if(!(itemprice<25.00 || itemprice>50.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
            	else if(childposition==2)
            	{
            		System.out.println("inside child position 2");
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		System.out.println("item price"+itemprice);
            		if((itemprice<50.00 || itemprice>100.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
            	else if(childposition==3)
            	{
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		if((itemprice<100.00 || itemprice>200.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
            	else if(childposition==4)
            	{
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		if((itemprice<200.00 || itemprice>400.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
            	else if(childposition==5)
            	{
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		if((itemprice<400.00 || itemprice>800.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
            	else 
            	{
            		String itempricestr=mp.getProductPrice().substring(1);
            		double itemprice=Double.parseDouble(itempricestr);
            		if(!(itemprice>800.00))
    				{
    					itr.remove();
    					System.out.println("size of m is"+m.size());
    				}
            	}
			}
			else if(groupposition==2)
			{
				System.out.println("inside groupposition=2");
				if(!(mp.getProductDesc().contains(Selected)))
				{
					itr.remove();
					System.out.println("size of m is"+m.size());
				}
			}
			else if(groupposition==3)
			{
				System.out.println("inside groupposition=3");
				if(!(mp.getProductbrand().contains(Selected)))
				{
					itr.remove();
					System.out.println("size of m is"+m.size());
				}
			}
			else
			{
				System.out.println("inside groupposition=4");
				if(!(mp.getProductDesc().contains(Selected)))
				{
					itr.remove();
					System.out.println("size of m is"+m.size());
				}
			}
			i++;
		}
		
		System.out.println("final mproduct list size is"+m.size());
		return m;
	}
	
}
