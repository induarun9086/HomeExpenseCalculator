package com.example.myfirstandroidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExpenseListAdapter extends ListViewAdapter{
	public ExpenseListAdapter(Activity activity,
			ArrayList<HashMap<String, String>> list) {
		super(activity, list);
	}

	private class ViewHolder {
		TextView category;
		TextView amount;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		LayoutInflater inflater = activity.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expense_list, null);
			holder = new ViewHolder();
			holder.category = (TextView) convertView
					.findViewById(R.id.expensecategory);
			holder.amount = (TextView) convertView
					.findViewById(R.id.expenseamount);

			convertView.setTag(holder);
		}

		else {
			holder = (ViewHolder) convertView.getTag();
		}

		HashMap map = list.get(position);
		holder.category.setText((String) map.get("Category"));
		holder.amount.setText((String) map.get("Amount"));
		return convertView;
	}
}
