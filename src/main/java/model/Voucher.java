package model;

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;

public class Voucher {
	private String name;
	private LocalDate limitDate;
	
	public Voucher(String name, LocalDate limitDate) {
		this.name = name;
		this.limitDate = limitDate;
	}

	public String getName() {
		return name;
	}

	public LocalDate getLimitDate() {
		return limitDate;
	}
	
	public String getJapaneseLimitDate() {
		JapaneseDate japaneseDate = JapaneseDate.of(limitDate.getYear(), limitDate.getMonthValue(), limitDate.getDayOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Gy年MM月dd日");
		
		return japaneseDate.format(formatter);
	}
}
