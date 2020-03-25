package jp.co.acesystems.mybatissample.dbaccess.item;

import java.math.BigDecimal;

import jp.co.acesystems.mybatissample.domain.type.DateTime;

/**
 * 商品データモデル
 * @author U0268
 *
 */
public class ItemDataModel {
	/** 商品コード */
	private String code;
	/** 商品名 */
	private String name;
	/** 基本単価 */
	private BigDecimal basicTanka;
	/** 更新日時 */
	private DateTime lastUpdateDatetime;
	/** 更新ユーザー */
	private Integer lastUpdateEmployeeId;
	
	/**
	 * MyBatis用コンストラクタ
	 */
	@Deprecated
	ItemDataModel() {
		
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the basicTanka
	 */
	public BigDecimal getBasicTanka() {
		return basicTanka;
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
