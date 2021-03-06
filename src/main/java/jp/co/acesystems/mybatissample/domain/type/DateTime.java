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
		
		this.value = LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
	}
	
	private DateTime(LocalDateTime value) {
		this.value = value;
	}
	
	/**
	 * IOS形式で日時を返す
	 * （yyyy-MM-ddTHH:mm:ss）
	 * @return
	 */
	public String getString() {
		return value.format(DateTimeFormatter.ISO_DATE_TIME);
	}
	
	/**
	 * 日本語形式で日時を返す
	 * （yyyy年MM月dd日 HH時mm分ss秒）
	 * @return
	 */
	public String getJpString() {
		return value.format(DateTimeFormatter.ofPattern("uuuu年M月d日 HH時m分s秒"));
	}
	
	public static DateTime now() {
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
	
	public boolean isAfter(DateTime from) {
		return value.isAfter(from.value);
	}
	
	public boolean isBefore(DateTime from) {
		return value.isBefore(from.value);
	}
}
