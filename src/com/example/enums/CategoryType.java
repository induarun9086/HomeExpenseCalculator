package com.example.enums;

public enum CategoryType {

	INCOME(1, "Income"), EXPENSE(2, "Expense");

	private int value;
	private String label;

	private CategoryType(int value, String label) {
		this.value = value;
		this.label = label;

	}

	public static String getLabel(int value) {
		CategoryType[] catTypeArr = CategoryType.values();
		for (int i = 0; i < catTypeArr.length; i++) {
			CategoryType catType = catTypeArr[i];
			if (catType.value == value) {
				return catType.label;
			}
		}
		return null;
	}

	public static int getValue(String label) {
		CategoryType[] catTypeArr = CategoryType.values();
		for (int i = 0; i < catTypeArr.length; i++) {
			CategoryType catType = catTypeArr[i];
			if (catType.label.equalsIgnoreCase( label)) {
				return catType.value;
			}
		}
		return 0;
	}

}
