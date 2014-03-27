package com.example.actionbar;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
public class SearchActivity  extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	 requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.searchscreen);
	onSearchRequested();
	
	System.out.println("inside launching search screen");
	
	
}

}
