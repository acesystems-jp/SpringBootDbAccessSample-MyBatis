package jp.co.acesystems.mybatissample.domain.type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
	LocalDate value;
	
	@Deprecated
	Date() {
		
	}
	
	public Date(LocalDate date) {
		this.value = date;
	}
	
	public String getString() {
		return value.format(DateTimeFormatter.ISO_DATE);
	}
	
	public String getJpString() {
		return value.format(DateTimeFormatter.ofPattern("uuuu年MM月dd日"));
	}
	
	public static Date today() {
		return new Date(LocalDate.now());
	}
	
	
	
}
