package com.example.myfirstandroidapp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.data.CategoryDataBaseHelper;
import com.example.data.ExpenseDataBaseHelper;

public class ExpenseListActivity extends Activity {
	
	final Calendar myCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.planner_activity_main);
		
		Spinner filter = (Spinner) findViewById(R.id.filter_by);
		
		filter.getSelectedItem().toString();

		ExpenseDataBaseHelper eDBHelper = new ExpenseDataBaseHelper(this);

		ArrayList<HashMap<String, String>> expenseList = eDBHelper
				.getAllExpenses();

		ListView lview = (ListView) findViewById(R.id.expenselist);
		ListViewAdapter adapter = new ExpenseListAdapter(this, expenseList);
		lview.setAdapter(adapter);

	}
	


	public void selectDate(View view) {
		final EditText dateText = (EditText) findViewById(R.id.date);

		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel(dateText);
			}

		};

		dateText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new DatePickerDialog(ExpenseListActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}

	private void updateLabel(EditText dateText) {

		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.date_format,
				Locale.US);

		dateText.setText(sdf.format(myCalendar.getTime()));
	}

	public void saveExpense(View view) throws Exception {

		EditText categoryText = (EditText) findViewById(R.id.category);
		EditText amountText = (EditText) findViewById(R.id.amount);
		EditText descText = (EditText) findViewById(R.id.description);
		EditText dateStr = (EditText) findViewById(R.id.date);

		String category = categoryText.getText().toString();

		if (category == null || category.isEmpty()) {
			Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT)
			.show();
			return;
		}

		String amt = amountText.getText().toString();

		if (amt == null || amt.isEmpty()) 
		{
			Toast.makeText(this, "Enter a valid Amount", Toast.LENGTH_SHORT)
					.show();
			return;

		} 
		else {
			BigDecimal amount = new BigDecimal(amt);
			if (amount == BigDecimal.ZERO
					|| amount.compareTo(BigDecimal.ZERO) < 0) {
				Toast.makeText(this,
						"Make sure that the amount is not zero or less",
						Toast.LENGTH_SHORT).show();
				return;
			}
		}

		Long dateInms = DateUtil
				.getDateAsMilliSec(dateStr.getText().toString());

		Map<String, String> queryValMap = new HashMap<String, String>();
		queryValMap.put("category", category);
		queryValMap.put("desc", descText.getText().toString());
		queryValMap.put("amount", amt);
		queryValMap.put("date", String.valueOf(dateInms));

		ExpenseDataBaseHelper eDBHelper = new ExpenseDataBaseHelper(this);
		eDBHelper.insertRecord(queryValMap);

		Intent intent = new Intent(this, ExpenseListActivity.class);
		startActivity(intent);
	}

	public void cancelExpense(View view) throws Exception {

		Intent intent = new Intent(this,ExpenseListActivity.class);
		startActivity(intent);
	}
	

	public void openExpenseForm(View view) {
		
		setContentView(R.layout.expense_form);
		CategoryDataBaseHelper cBaseHelper = new CategoryDataBaseHelper(this);
		
		Spinner categories = (Spinner)findViewById(R.id.category);
		
		ArrayList<HashMap<String, String>> categoryList = cBaseHelper
				.getExpenseCategories();
		List<String> categoryNames = new ArrayList<String>();
		for (int i = 0; i < categoryList.size(); i++) {
			String categoryName = categoryList.get(i).get("CategoryName");
			categoryNames.add(categoryName);
			
		}
		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categoryNames);
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categories.setAdapter(dataAdapter);
		
		
		EditText date = (EditText) findViewById(R.id.date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		date.setText(sdf.format(new Date()));
		date.setGravity(Gravity.CENTER);
		
	
	}
}
