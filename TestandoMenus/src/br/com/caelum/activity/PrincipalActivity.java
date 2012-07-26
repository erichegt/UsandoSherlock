package br.com.caelum.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class PrincipalActivity extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.inicio, menu);
    	ActionBar actionBar = getSupportActionBar();
    	
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		TabListener listener = new TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				
			}
		};
		
		Tab aba1 = actionBar.newTab();
		aba1.setText("Aba1");
		aba1.setTabListener(listener);
		actionBar.addTab(aba1);

		
		Tab aba2 = actionBar.newTab();
		aba2.setText("Aba2");
		aba2.setTabListener(listener);
		actionBar.addTab(aba2);
		
    	
    	return super.onCreateOptionsMenu(menu);
    }

    
}
