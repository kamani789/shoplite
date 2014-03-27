package com.example.actionbar;

import android.app.SearchManager;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.List;
 
public class SearchInterface extends SearchInterfaceBase {
    @Override
    ListAdapter makeMeAnAdapter(Intent intent) {
        ListAdapter adapter=null; 
         System.out.println("inside creating list adapter");
        if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
            String query=intent.getStringExtra(SearchManager.QUERY);
            List<String> results=searchItems(query);
             
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results);
            setTitle("Search : "+query);
        }
        return(adapter);
    }
     
    private List<String> searchItems(String query) {
    	
    	System.out.println("inside super");
        SearchSuggestionProvider
            .getBridge(this)
            .saveRecentQuery(query, null);
         
        List<String> results=new ArrayList<String>();
         
        for (String item : items) {
            if (item.indexOf(query)>-1) {
                results.add(item);
            }
        }
        return(results);
    }
}