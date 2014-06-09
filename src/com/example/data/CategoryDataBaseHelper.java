package com.example.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.enums.CategoryType;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CategoryDataBaseHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "CategoryTable";
	
	private static final String ALL_CATEGORIES_QUERY = "SELECT  * FROM " + DB_NAME;
	
	private static final String INCOME_CATEGORIES_QUERY = ALL_CATEGORIES_QUERY + " Where CategoryType = 1";
	
	private static final String EXPENSE_CATEGORIES_QUERY = ALL_CATEGORIES_QUERY + " Where CategoryType = 2";

	public CategoryDataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE "
				+ DB_NAME
				+ " (CategoryID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CategoryName TEXT,CategoryType Integer,CreatedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);

		onCreate(db);

	}

	public void insertRecord(Map<String, String> queryValMap) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("CategoryName", queryValMap.get("categoryName"));
		values.put("CategoryType", queryValMap.get("categoryType"));
		Long id = database.insert(DB_NAME, null, values);
		Log.i("value of the id is ", id + "******");
		database.close();
	}

	public ArrayList<HashMap<String, String>> getAllCategories() {
		return getCategories(ALL_CATEGORIES_QUERY);
	}

	public ArrayList<HashMap<String, String>> getIncomeCategories() {
		return getCategories(INCOME_CATEGORIES_QUERY);
	}

	public ArrayList<HashMap<String, String>> getExpenseCategories() {
		return getCategories(EXPENSE_CATEGORIES_QUERY);
	}

	public ArrayList<HashMap<String, String>> getCategories(String query){
		ArrayList<HashMap<String, String>> categoryList;
		categoryList = new ArrayList<HashMap<String, String>>();
		
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ID", cursor.getString(0));
				map.put("CategoryName", cursor.getString(1));
				String catType = CategoryType.getLabel(cursor.getInt(2));
				map.put("CategoryType",catType);
				map.put("CreatedTime", cursor.getString(3));
				categoryList.add(map);
			} while (cursor.moveToNext());
		}
		System.out.println(categoryList);
		return categoryList;
	}
}
