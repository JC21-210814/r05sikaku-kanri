package model;

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;

public class ExamPlan {
	private String examName;
	private LocalDate examDate;
	private int applicationStatus;
	
	public ExamPlan(String examName, LocalDate examDate, int applicationStatus) {
		this.examName = examName;
		this.examDate = examDate;
		this.applicationStatus = applicationStatus;
	}

	public String getExamName() {
		return examName;
	}

	public LocalDate getExamDate() {
		return examDate;
	}
	
	public String getJapaneseExamDateString() {
		JapaneseDate japaneseDate = JapaneseDate.of(examDate.getYear(), examDate.getMonthValue(), examDate.getDayOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Gy年MM月dd日");
		return japaneseDate.format(formatter);
	}

	public int getApplicationStatus() {
		return applicationStatus;
	}
	
	
}
