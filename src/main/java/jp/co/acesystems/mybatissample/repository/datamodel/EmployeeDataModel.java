package jp.co.acesystems.mybatissample.repository.datamodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 社員データモデル
 * データベースのテーブルに対応したクラス
 * @author U0268
 *
 */
public class EmployeeDataModel implements HavingSinglePk<Integer> {
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

	public EmployeeDataModel() {
		
	}
	
	public EmployeeDataModel(Integer id,
			String name,
			String code,
			LocalDateTime lastUpdateDatetime,
			Integer lastUpdateEmployeeId) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.lastUpdateEmployeeId = lastUpdateEmployeeId;
	}
	
	public EmployeeDataModel(String name,
			String code,
			LocalDateTime lastUpdateDatetime,
			Integer lastUpdateEmployeeId) {
		this(null, name, code, lastUpdateDatetime, lastUpdateEmployeeId);
	}
	
	public EmployeeDataModel clone() {
		return new EmployeeDataModel(this.id, this.name, this.code, this.lastUpdateDatetime, this.lastUpdateEmployeeId);
	}
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the lastUpdateDatetime
	 */
	public LocalDateTime getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}
	/**
	 * @return the lastUpdateEmployeeId
	 */
	public Integer getLastUpdateEmployeeId() {
		return lastUpdateEmployeeId;
	}
	
	
	
}
