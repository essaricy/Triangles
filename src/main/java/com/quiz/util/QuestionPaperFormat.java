package com.quiz.util;

public class QuestionPaperFormat {

	public static final String QUESTION_SERIAL_NUMBER = "QUESTION_SERIAL_NUMBER";
	public static final String QUESTION_COLUMN = "QUESTION_COLUMN";
	public static final String ANSWER_COLUMN = "ANSWER_COLUMN";
	public static final String OPTION_1_COLUMN = "OPTION_1_COLUMN";
	public static final String OPTION_2_COLUMN = "OPTION_2_COLUMN";
	public static final String OPTION_3_COLUMN = "OPTION_3_COLUMN";
	public static final String OPTION_4_COLUMN = "OPTION_4_COLUMN";
	public static final String OPTION_5_COLUMN = "OPTION_5_COLUMN";

	private static final String[] QUESTION_PAPER_COLUMN_ORDER = {
		QUESTION_SERIAL_NUMBER,
		QUESTION_COLUMN, ANSWER_COLUMN,
		OPTION_1_COLUMN, OPTION_2_COLUMN, OPTION_3_COLUMN, OPTION_4_COLUMN, OPTION_5_COLUMN
	};

	public static String getColumnName(int index) {
		String columnName = null;
		if (index >=0 && index < QUESTION_PAPER_COLUMN_ORDER.length) {
			columnName = QUESTION_PAPER_COLUMN_ORDER[index];
		}
		return columnName;
	}
}
