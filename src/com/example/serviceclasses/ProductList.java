package com.example.serviceclasses;

import java.util.ArrayList;

public class ProductList {
	public ArrayList<String[]> getproductinfo()
	{
		String a[]={"plus","SamsungGalaxy","$ 123.23"};
		String b[]={"kindle","kindle Fire HD","$ 189.35"};
		String c[]={"hplaptop","HP Pavilion 14inch","$ 389.65"};
		String d[]={"proteindrink","  Boost  ","$40.00"};
		String e[]={"toolkit","Nailer Tools","$35.50"};
		String f[]={"camera","  Nikon ","$160.00"};
		String g[]={"iphone4s","iphone5 32GB","$345.67"};
		String h[]={"nikefieldglove","   Nike Field Glove","$60.00"};
		String i[]={"Playstation 4","   PlayStation4","$160.00"};
		String j[]={"XBOX Console","  Xbox 360 Console ","$260.00"};
		String k[]={"titan fall","   Xbox Titan fall  ","$30.00"};
		String l[]={"finalfantasy","  Xbox finalfantasy","$24.00"};
		ArrayList<String[]> al=new ArrayList<String[]>();
		al.add(a);
		al.add(b);
		al.add(c);
		al.add(d);
		al.add(e);
		al.add(f);
		al.add(g);
		al.add(h);
		al.add(i);
		al.add(j);
		al.add(k);
		al.add(l);
		return al;
	}
	

public ProductList() {
	// TODO Auto-generated constructor stub
}

}
