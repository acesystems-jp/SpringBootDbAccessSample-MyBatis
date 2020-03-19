package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import java.util.ArrayList;
import java.util.List;

import jp.co.acesystems.mybatissample.dbaccess.RecordWithSinglePk;
import jp.co.acesystems.mybatissample.domain.type.Date;
import jp.co.acesystems.mybatissample.domain.type.DateTime;

public class DenpyoDataModel implements RecordWithSinglePk<Integer> {

	private Integer id;
	private Integer denpyoNo;
	private Integer edaNo;
	private Date denpyoDate;
	/** 更新日時 */
	private DateTime lastUpdateDatetime;
	/** 更新ユーザー */
	private Integer lastUpdateEmployeeId;
	private List<DenpyoDetailDataModel> details;

	/**
	 * MyBatis用のコンストラクタ
	 */
	@Deprecated
	DenpyoDataModel() {
		this.details = new ArrayList<>();
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param denpyoNo
	 * @param edaNo
	 * @param denpyoDate
	 * @param lastUpdateDatetime
	 * @param lastUpdateEmployeeId
	 */
	public DenpyoDataModel(Integer id,
			Integer denpyoNo,
			Integer edaNo,
			Date denpyoDate,
			DateTime lastUpdateDatetime,
			Integer lastUpdateEmployeeId,
			List<DenpyoDetailDataModel> details) {
		this.id = id;
		this.denpyoNo = denpyoNo;
		this.edaNo = edaNo;
		this.denpyoDate = denpyoDate;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.lastUpdateEmployeeId = lastUpdateEmployeeId;
		this.details = details;
//		this(id,
//				denpyoNo,
//				edaNo,
//				denpyoDate,
//				lastUpdateDatetime,
//				lastUpdateEmployeeId,
//				new DenpyoDetailDataModelList(details));
	}
	
//	private DenpyoDataModel(Integer id,
//			Integer denpyoNo,
//			Integer edaNo,
//			Date denpyoDate,
//			DateTime lastUpdateDatetime,
//			Integer lastUpdateEmployeeId,
//			DenpyoDetailDataModelList details) {
//		this.id = id;
//		this.denpyoNo = denpyoNo;
//		this.edaNo = edaNo;
//		this.denpyoDate = denpyoDate;
//		this.lastUpdateDatetime = lastUpdateDatetime;
//		this.lastUpdateEmployeeId = lastUpdateEmployeeId;
//		this.details = details;
//	}
	
	/**
	 * コンストラクタ
	 * @param denpyoNo
	 * @param edaNo
	 * @param denpyoDate
	 * @param lastUpdateDatetime
	 * @param lastUpdateEmployeeId
	 */
	public DenpyoDataModel(Integer denpyoNo, Integer edaNo, Date denpyoDate, DateTime lastUpdateDatetime, Integer lastUpdateEmployeeId,
			List<DenpyoDetailDataModel> details) {
		this(null, denpyoNo, edaNo, denpyoDate,lastUpdateDatetime, lastUpdateEmployeeId, details);
	}
	
	
	@Override
	public DenpyoDataModel clone() {
		return new DenpyoDataModel(
				id, denpyoNo, edaNo, denpyoDate, lastUpdateDatetime, lastUpdateEmployeeId, details);
				
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the denpyoNo
	 */
	public Integer getDenpyoNo() {
		return denpyoNo;
	}

	/**
	 * @return the edaNo
	 */
	public Integer getEdaNo() {
		return edaNo;
	}

	/**
	 * @return the denpyoDate
	 */
	public Date getDenpyoDate() {
		return denpyoDate;
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
	 * @return the details
	 */
	public List<DenpyoDetailDataModel> getDetails() {
		return details;
	}
}
