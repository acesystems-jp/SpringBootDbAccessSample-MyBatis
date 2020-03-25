package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import java.math.BigDecimal;

import jp.co.acesystems.mybatissample.dbaccess.RecordWithSinglePk;
import jp.co.acesystems.mybatissample.dbaccess.item.ItemDataModel;
import jp.co.acesystems.mybatissample.domain.type.DateTime;

public class DenpyoDetailDataModel implements RecordWithSinglePk<Integer> {
	private Integer id;
	private Integer denpyoId;
	private Integer rowNo;
	private ItemDataModel item;
	private Integer suryo;
	private BigDecimal tanka;
	/** 更新日時 */
	private DateTime lastUpdateDatetime;
	/** 更新ユーザー */
	private Integer lastUpdateEmployeeId;
	
	@Deprecated
	DenpyoDetailDataModel() {
		
	}
	
	DenpyoDetailDataModel(
			Integer id,
			Integer denpyoId,
			Integer rowNo,
			ItemDataModel item,
			Integer suryo,
			BigDecimal tanka,
			DateTime lastUpdateDatetime,
			Integer lastUpdateEmployeeId) {
		this.id = id;
		this.denpyoId = denpyoId;
		this.rowNo = rowNo;
		this.item = item;
		this.suryo = suryo;
		this.tanka	= tanka;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.lastUpdateEmployeeId = lastUpdateEmployeeId;
	}
	
	@Override
	public DenpyoDetailDataModel clone() {
		return new DenpyoDetailDataModel(
				id,
				denpyoId,
				rowNo,
				item,
				suryo,
				tanka,
				lastUpdateDatetime,
				lastUpdateEmployeeId);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the denpyoId
	 */
	public Integer getDenpyoId() {
		return denpyoId;
	}

	/**
	 * @return the rowNo
	 */
	public Integer getRowNo() {
		return rowNo;
	}

	/**
	 * @return the itemCode
	 */
	public ItemDataModel getItem() {
		return item;
	}

	/**
	 * @return the suryo
	 */
	public Integer getSuryo() {
		return suryo;
	}

	/**
	 * @return the tanka
	 */
	public BigDecimal getTanka() {
		return tanka;
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
