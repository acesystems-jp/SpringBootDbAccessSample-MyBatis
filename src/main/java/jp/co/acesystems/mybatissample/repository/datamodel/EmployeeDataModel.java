package jp.co.acesystems.mybatissample.repository.datamodel;

import jp.co.acesystems.mybatissample.domain.type.DateTime;

/**
 * 社員データモデル
 * データベースのテーブルに対応したクラス
 * @author U0268
 *
 */
public class EmployeeDataModel implements RecordWithSinglePk<Integer> {
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

	/**
	 * MyBatisが使うためのコンストラクタ
	 */
	@Deprecated
	public EmployeeDataModel() {
		
	}
	/**
	 * コンストラクタ
	 * 全項目指定する
	 * @param id
	 * @param name
	 * @param code
	 * @param lastUpdateDatetime
	 * @param lastUpdateEmployeeId
	 */
	public EmployeeDataModel(Integer id,
			String name,
			String code,
			DateTime lastUpdateDatetime,
			Integer lastUpdateEmployeeId) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.lastUpdateEmployeeId = lastUpdateEmployeeId;
	}
	
	/**
	 * コンストラクタ
	 * ID以外の項目を元にインスタンス生成
	 * @param name
	 * @param code
	 * @param lastUpdateDatetime
	 * @param lastUpdateEmployeeId
	 */
	public EmployeeDataModel(String name,
			String code,
			DateTime lastUpdateDatetime,
			Integer lastUpdateEmployeeId) {
		this(null, name, code, lastUpdateDatetime, lastUpdateEmployeeId);
	}
	
	/**
	 * クローン
	 * インスタンスをディープコピーする
	 */
	@Override
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
	public DateTime getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}
	/**
	 * @return the lastUpdateEmployeeId
	 */
	public Integer getLastUpdateEmployeeId() {
		return lastUpdateEmployeeId;
	}
	
	
	
}
