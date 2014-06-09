package com.example.myfirstandroidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CategoryListAdapter extends ListViewAdapter{

	public CategoryListAdapter(Activity activity,
			ArrayList<HashMap<String, String>> categoryList) {
		super(activity, categoryList);
		// TODO Auto-generated constructor stub
	}
	

	private class ViewHolder {
		TextView category;
		TextView categoryType;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		LayoutInflater inflater = activity.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.category_list, null);
			holder = new ViewHolder();
			holder.category = (TextView) convertView
					.findViewById(R.id.categoryname);
			holder.categoryType = (TextView) convertView
					.findViewById(R.id.categoryType);

			convertView.setTag(holder);
		}

		else {
			holder = (ViewHolder) convertView.getTag();
		}

		HashMap map = list.get(position);
		holder.category.setText((String) map.get("CategoryName"));
		holder.categoryType.setText((String) map.get("CategoryType"));
		return convertView;
	}

}
