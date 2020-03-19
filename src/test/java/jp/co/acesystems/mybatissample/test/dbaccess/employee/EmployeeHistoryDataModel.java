package jp.co.acesystems.mybatissample.test.dbaccess.employee;

import jp.co.acesystems.mybatissample.domain.type.DateTime;

public class EmployeeHistoryDataModel {
	/** 履歴id */
	private Integer historyId;
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
	/** 操作日時 */
	private DateTime operationDatetime;
	/** 操作 */
	private String operation;
	
	@Deprecated
	EmployeeHistoryDataModel() {
		
	}
	
	/**
	 * @return the historyId
	 */
	public Integer getHistoryId() {
		return historyId;
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
	
	/**
	 * @return the operationDatetime
	 */
	public DateTime getOperationDatetime() {
		return operationDatetime;
	}
	
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	
	
}
