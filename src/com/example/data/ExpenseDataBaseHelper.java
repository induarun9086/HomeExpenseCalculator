package com.example.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExpenseDataBaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "ExpenseTable";

	public ExpenseDataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE "
				+ DB_NAME
				+ " (ExpenseID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Category TEXT, Amount DECIMAL(10,5),Description TEXT,ExpenseDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);

		onCreate(db);

	}

	public void insertRecord(Map<String, String> queryValMap) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Category", queryValMap.get("category"));
		values.put("Amount", queryValMap.get("amount"));
		values.put("Description", queryValMap.get("desc"));
		values.put("Amount", queryValMap.get("amount"));
		values.put("ExpenseDate", queryValMap.get("date"));
		database.insert(DB_NAME, null, values);
		database.close();
	}

	public ArrayList<HashMap<String, String>> getAllExpenses() {
		ArrayList<HashMap<String, String>> expenseList;
		expenseList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM " + DB_NAME + " ORDER BY Date(ExpenseDate) DESC";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ID", cursor.getString(0));
				map.put("Category", cursor.getString(1));
				map.put("Amount", cursor.getString(2));
				map.put("Description", cursor.getString(3));
				map.put("CreatedTime", cursor.getString(4));
				expenseList.add(map);
			} while (cursor.moveToNext());
		}
		System.out.println(expenseList);
		return expenseList;
	}
}
