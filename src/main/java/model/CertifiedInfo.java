package model;

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;

public class CertifiedInfo {
	private String name;
	private LocalDate certifiedDate;
	
	public CertifiedInfo(String name, LocalDate certifiedDate) {
		this.name = name;
		this.certifiedDate = certifiedDate;
	}

	public String getName() {
		return name;
	}

	public LocalDate getCertifiedDate() {
		return certifiedDate;
	}

	public String getJapaneseCertifiedDate() {
		JapaneseDate japaneseDate = JapaneseDate.of(certifiedDate.getYear(), certifiedDate.getMonthValue(), certifiedDate.getDayOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Gy年MM月dd日");
		
		return japaneseDate.format(formatter);
	}
}
