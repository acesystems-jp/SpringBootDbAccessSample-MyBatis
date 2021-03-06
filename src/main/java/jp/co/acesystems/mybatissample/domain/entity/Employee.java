package jp.co.acesystems.mybatissample.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jp.co.acesystems.mybatissample.dbaccess.employee.EmployeeDataModel;
import jp.co.acesystems.mybatissample.domain.type.DateTime;

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
	private DateTime lastUpdateDatetime;
	/** 更新ユーザー */
	private Integer lastUpdateEmployeeId;
	
	public Employee() {
		
	}
	
	/**
	 * コンストラクタ
	 * DataModelからインスタンス生成
	 * @param datamodel
	 */
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
	public DateTime getLastUpdateDatetimeByLocalDateTime() {
		return lastUpdateDatetime;
	}
	
	/**
	 * @return 更新日時をISODatetimeフォーマット編集して返す
	 */
	public String getLastUpdateDatetime() {
		return lastUpdateDatetime.getString();
	}
	
	/**
	 * @return 更新日時を日本語フォーマット編集して返す
	 */
	public String getLastUpdateDatetimeJp() {
		return lastUpdateDatetime.getJpString();
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
