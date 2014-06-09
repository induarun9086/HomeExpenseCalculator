package com.example.myfirstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);

	}

	
	
	public void openExpensePage(View view)
	{

	    Intent intent = new Intent(this, ExpenseListActivity.
	    		class);
                    startActivity(intent); 
	}

	
	public void  openCategoriesPage(View view) {
		 Intent intent = new Intent(this, CategoryActivity.
		    		class);
	                    startActivity(intent); 
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
