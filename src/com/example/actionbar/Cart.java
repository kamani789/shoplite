package com.example.actionbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.db.OrderDatatransactions;
import com.example.db.Orderdata;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Cart extends Activity implements OnClickListener {

	Button editcart, proceedtocheckout;
	private List<ModelProducts> mProductList;
	ModelProducts m;
	ListView listViewCatalog;
	boolean isediting=false;
	String username,email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart);
		getWindow().setTitle("You Cart");
		editcart = (Button) findViewById(R.id.EditCart);
		proceedtocheckout = (Button) findViewById(R.id.ProceedToCheckout);
		editcart.setOnClickListener(this);
		proceedtocheckout.setOnClickListener(this);
		proceedtocheckout.setOnClickListener(this);
		listViewCatalog = (ListView) findViewById(R.id.cartListViewCatalog);
		mProductList = new ArrayList<ModelProducts>();
		android.content.res.Resources res = getResources();
		Bundle bask = getIntent().getExtras();
		String name = bask.getString("name");
		  email=bask.getString("email");
		 username=bask.getString("username");
		System.out.println("product name and email after coming to cart is" + name+","+email);
		GetsearchedResultsfromBase g = new GetsearchedResultsfromBase();
		OrderDatatransactions o = new OrderDatatransactions();
		System.out.println("getting existing results from database");
		List<Orderdata> ot = o.getorderdata(getApplicationContext(),
				email, "Added");
		if (ot.size() > 0) {
			System.out
					.println("data got from database is"
							+ ot.get(0).getProductname() + " and length is"
							+ ot.size());
			for (com.example.db.Orderdata dt : ot) {

				String prodname = dt.getProductname();
				System.out.println("inside for loop" + prodname);
				String[] product = g.getitem(prodname);
				if (product.length == 0)
					System.out.println("no produc eista with the name"
							+ prodname);
				ModelProducts mp1 = new ModelProducts(product[0], product[1],
						product[2], getimage(product[3]), product[4]);

				mProductList.add(mp1);
			}
		}
		if(name!=null && !name.equalsIgnoreCase("babu"))
		{
		String[] m = g.getitem(name);
		System.out.println("product details are" + m[0] + "," + m[1] + ","
				+ m[2]);
		ModelProducts mp = new ModelProducts(m[0], m[1], m[2], getimage(m[3]),
				m[4]);
		Date d=new Date();
		String date=d.toString();
		Orderdata od = new Orderdata(1, 1,username ,
				email, name, date, "1", "Added");

		//o.isdeleted(getApplicationContext());
		o.isdatainserted(getApplicationContext(), od);
		System.out.println("m details are" + mp.getProductDesc());
		mProductList.add(mp);
		}
		System.out.println("size of cart is" + mProductList.size());
		

		System.out
				.println("product list length before displaying in list adapter is"
						+ mProductList.size());
		listViewCatalog.setAdapter(new ResultsetAdapter(mProductList,
				getLayoutInflater()));
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				System.out.println("inside on click of item position"+position);
				ModelProducts mp=(ModelProducts) arg0.getAdapter().getItem(position);
				System.out.println("selected  item is"+mp.getProductDesc());
				if(isediting)
				{
					OrderDatatransactions odt = new OrderDatatransactions();
					System.out.println("inside editing cart");
					mProductList.remove(mp);
					System.out.println("product list size after deleting is"+mProductList.size());
					odt.isdeleted(getApplicationContext(),mp.getProductName(),email,"Added");
					System.out.println("after deleting from database");
					listViewCatalog.setAdapter(new ResultsetAdapter(mProductList,
							getLayoutInflater()));
					
				}
				
			}
		});
		
	}

	public Drawable getimage(String st) {

		Resources res = getResources();
		int resID = res.getIdentifier(st, "drawable", getPackageName());
		Drawable drawable = res.getDrawable(resID);
		return drawable;
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId())
		{
		case R.id.EditCart:
			isediting=true;
			System.out.println("inside edit cart and editlue is"+isediting);
			Toast.makeText(getBaseContext(), "Please click on the product in cart to remove", Toast.LENGTH_LONG).show();
			break;
		case R.id.ProceedToCheckout:
			Bundle b1=new Bundle();
			System.out.println("inside proceed  cart and editlue is"+isediting);
			System.out.println("mproduct list size in proceeding to check out is"+mProductList.size());
			//Toast.makeText(getBaseContext(), "Proceeding to checkout", Toast.LENGTH_LONG).show();
			HashMap<String, String[]> hm1=getmap(mProductList);
			System.out.println("hash map size after getting from map is"+hm1.size());
			b1.putSerializable("product", hm1);	
			Intent intnt=new Intent(getApplicationContext(),Checkout.class); 	   					
			intnt.putExtras(b1);
 	  	    startActivity(intnt);
			break;
		}
		
	}
	public HashMap<String, String []> getmap(List<ModelProducts> model)
	{
		HashMap<String, String[]> hm=new HashMap<String, String[]>();
		for(int i=0;i<model.size();i++)
		{
			String [] s={model.get(i).getProductbrand(),model.get(i).getProductDesc(),model.get(i).getProductName(),username,email};
			hm.put("one"+i, s);
		}
		return hm;
	}
}
