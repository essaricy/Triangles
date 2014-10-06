package com.quiz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.quiz.exception.QuestionPaperException;
import com.quiz.valueobject.Question;
import com.quiz.valueobject.QuestionPaperFormat;

public class QuestionPaperReaderUtil {

	public static List getQuestions(File questionPaper) throws QuestionPaperException {

		int cellType = 0;
		int noOfSheets = 0;
		int lastRowNum = 0;
		int lastColumnNum = 0;

		String columnName = null;
		String columnValue = null;
		List questionStateList = new ArrayList();
		FileInputStream fileInputStream = null;

		HSSFWorkbook workbook = null;
		HSSFSheet currentsheet = null;
		HSSFRow currentRow = null;
		HSSFCell currentCell = null;

		Question question = null;

		try {
			if (questionPaper != null) {
				fileInputStream = new FileInputStream(questionPaper);
				workbook = new HSSFWorkbook(fileInputStream);
				noOfSheets = workbook.getNumberOfSheets();
				// Read each sheet
				for (int sheetIndex = 0; sheetIndex < noOfSheets; sheetIndex++) {
					currentsheet = workbook.getSheetAt(sheetIndex);
					if (currentsheet != null) {
						lastRowNum = currentsheet.getLastRowNum();
						for (int rowIndex = 0; rowIndex <= lastRowNum; rowIndex++) {
							currentRow = currentsheet.getRow(rowIndex);
							if (currentRow != null) {
								lastColumnNum = currentRow.getLastCellNum();
								if (lastColumnNum != 0) {
									// Create new question state object
									question = new Question();
									for (int columnIndex = 0; columnIndex < lastColumnNum; columnIndex++) {
										currentCell = currentRow.getCell(columnIndex);
										if (currentCell != null) {
											cellType = currentCell.getCellType();
											if (cellType == HSSFCell.CELL_TYPE_BOOLEAN) {
												columnValue = String.valueOf(currentCell.getBooleanCellValue());
											} else if (cellType == HSSFCell.CELL_TYPE_ERROR) {
												columnValue = String.valueOf(currentCell.getErrorCellValue());
											} else if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
												columnValue = String.valueOf(currentCell.getCellFormula());
											} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
												columnValue = String.valueOf(currentCell.getNumericCellValue());
											} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
												columnValue = currentCell.getStringCellValue();
											}
											columnName = QuestionPaperFormat.getColumnName(columnIndex);
											updateQuestionState(question, columnName, columnValue);
										}
									}
									if (validQuestion(question)) {
										questionStateList.add(question);
									}
								}
							}
						}
					}
				}
				
			}
		} catch (FileNotFoundException fileNotFoundException) {
			throw new QuestionPaperException(fileNotFoundException.getMessage(), fileNotFoundException);
		} catch (IOException ioException) {
			throw new QuestionPaperException(ioException.getMessage(), ioException);
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException ioException) {
				throw new QuestionPaperException(ioException.getMessage(), ioException);
			}
		}
		return questionStateList;
	}

	private static boolean validQuestion(Question question) {
		boolean returnValue = false;
		if (question != null) {
			String answer = question.getAnswer();
			List options = question.getOptions();
			String questionString = question.getQuestion();
			if ((answer != null && answer.trim().length() > 0)
					&& options != null && !options.isEmpty()
					&& (questionString != null && questionString.trim().length() > 0)
					) {
				returnValue = true;
			}
		}
		return returnValue;
	}

	private static void updateQuestionState(Question question,
			String columnName, String columnValue) {
		if (question != null && columnName != null && columnValue != null) {
			if (columnName.equalsIgnoreCase(QuestionPaperFormat.ANSWER_COLUMN)) {
				question.setAnswer(columnValue);
			} else if (columnName.equalsIgnoreCase(QuestionPaperFormat.OPTION_1_COLUMN)
					|| columnName.equalsIgnoreCase(QuestionPaperFormat.OPTION_2_COLUMN)
					|| columnName.equalsIgnoreCase(QuestionPaperFormat.OPTION_3_COLUMN)
					|| columnName.equalsIgnoreCase(QuestionPaperFormat.OPTION_4_COLUMN)
					|| columnName.equalsIgnoreCase(QuestionPaperFormat.OPTION_5_COLUMN)) {
				List options = question.getOptions();
				if (options == null) {
					options = new ArrayList();
				}
				options.add(columnValue);
				question.setOptions(options);
			} else if (columnName.equalsIgnoreCase(QuestionPaperFormat.QUESTION_COLUMN)) {
				question.setQuestion(columnValue);
			} else if (columnName.equalsIgnoreCase(QuestionPaperFormat.QUESTION_SERIAL_NUMBER)) {
				question.setQuestionNumber((int)Double.parseDouble(columnValue));
			}
		}
		
	}

}
