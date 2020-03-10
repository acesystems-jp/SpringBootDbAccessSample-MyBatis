package jp.co.acesystems.mybatissample.domain.type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日時を扱うクラス
 * LocalDateTimeを直接使うとタイムゾーンを意識する必要がでたときなど
 * 日付を扱うクラスを変更するときに改修が大規模になるので
 * 専用クラスだけ使用するようにしておく
 * @author U0268
 *
 */
public class DateTime {
	/**
	 * ラップして保持している実際の値
	 * finalは書いていないが値を書き換えないこと！
	 * （値を書き換えるメソッドを作成しないこと）
	 */
	private LocalDateTime value;
	
	/**
	 * MyBatisが使うためのコンストラクタ
	 */
	@Deprecated
	DateTime() {
		
	}
	
	public DateTime(String value) {
		
		this.value = LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE);
	}
	
	private DateTime(LocalDateTime value) {
		this.value = value;
	}
	
	public String getString() {
		return value.format(DateTimeFormatter.BASIC_ISO_DATE);
	}
	
	public String getJpString() {
		return value.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒"));
	}
	
	public static DateTime Now() {
		return new DateTime(LocalDateTime.now());
	}
	
	public static DateTime fromIsoDatetime(String isoDateTimeString) {
		return new DateTime(isoDateTimeString);
	}
	
	public static DateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
		return new DateTime(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second));
	}

	
	public DateTime plusMinutes(long minutes) {
		return new DateTime(this.value.plusMinutes(minutes));
	}
	public DateTime minusMinutes(long minutes) {
		return new DateTime(this.value.minusMinutes(minutes));
	}
}
