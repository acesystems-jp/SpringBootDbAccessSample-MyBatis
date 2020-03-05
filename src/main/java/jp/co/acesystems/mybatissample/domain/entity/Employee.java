package jp.co.acesystems.mybatissample.domain.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;

/**
 * 社員クラス
 * @author U0268
 *
 */
public class Employee {
	/** id */
	private Integer id;
	/** 氏名 */
	private String name;
	/** 社員コード */
	private String code;
	/** 更新日時 */
	private LocalDateTime lastUpdateDatetime;
	/** 更新ユーザー */
	private Integer lastUpdateEmployeeId;
	
	public Employee() {
		
	}
	
	public Employee(EmployeeDataModel datamodel) {
		this.id = datamodel.getId();
		this.name = datamodel.getName();
		this.code = datamodel.getCode();
		this.lastUpdateDatetime = datamodel.getLastUpdateDatetime();
		this.lastUpdateEmployeeId = datamodel.getLastUpdateEmployeeId();
	}

	/**
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return 社員名
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return 社員番号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 更新日時をLocalDateTime型のまま返す
	 * クライアントには投げたくないので @JsonIgnore で対象外にする
	 * @return 更新日時
	 */
	@JsonIgnore
	public LocalDateTime getLastUpdateDatetimeByLocalDateTime() {
		return lastUpdateDatetime;
	}
	/**
	 * @return 更新日時をISODatetimeフォーマット編集して返す
	 */
	public String getLastUpdateDatetime() {
		return lastUpdateDatetime.format(DateTimeFormatter.ISO_DATE_TIME);
	}
	/**
	 * @return 更新日時を日本語フォーマット編集して返す
	 */
	public String getLastUpdateDatetimeJp() {
		return lastUpdateDatetime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒"));
	}

	/**
	 * クライアントには投げたくないので @JsonIgnore で対象外にする
	 * @return 更新ユーザー
	 */
	@JsonIgnore
	public Integer getLastUpdateEmployeeId() {
		return lastUpdateEmployeeId;
	}
	
	
}
