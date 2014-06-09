package com.example.myfirstandroidapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.data.CategoryDataBaseHelper;
import com.example.enums.CategoryType;

public class CategoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_home);
		
		CategoryDataBaseHelper cDBHelper = new CategoryDataBaseHelper(this);

		ArrayList<HashMap<String, String>> categoryList = cDBHelper
				.getAllCategories();

		ListView lview = (ListView) findViewById(R.id.categorylist);
		ListViewAdapter adapter = new CategoryListAdapter(this, categoryList);
		lview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	public void openCategoryForm(View view) {
		setContentView(R.layout.category_form);

	}
	
	public void cancelCategory(View view) {
		Intent intent = new Intent(this,CategoryActivity.class);
		startActivity(intent);

	}
	
	public void saveCategory(View view) throws Exception {

		EditText categoryText = (EditText) findViewById(R.id.category);
		Spinner categoryTypeSpinner = (Spinner) findViewById(R.id.categorytype);
		
		String categoryType = categoryTypeSpinner.getSelectedItem().toString();
		
	
	   Integer catType = CategoryType.getValue(categoryType);

	
		String category = categoryText.getText().toString();

		if (category == null || category.isEmpty()) {
			Toast.makeText(this, "Please Enter a name for the  category", Toast.LENGTH_SHORT)
			.show();
			return;
		}
		
		if(catType == 0)
		{
			Toast.makeText(this, "Please select a valid Category Type", Toast.LENGTH_SHORT)
			.show();
			return;
		}

		

		Map<String, String> queryValMap = new HashMap<String, String>();
		queryValMap.put("categoryName", category);
		queryValMap.put("categoryType", catType.toString());

		CategoryDataBaseHelper cDBHelper = new CategoryDataBaseHelper(this);
		cDBHelper.insertRecord(queryValMap);

		Intent intent = new Intent(this, CategoryActivity.class);
		startActivity(intent);
	}

}
