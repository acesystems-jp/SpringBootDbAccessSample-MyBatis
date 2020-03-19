package jp.co.acesystems.mybatissample.dbaccess;

/**
 * 履歴への書き込み処理を持ったMapperを表すInterface
 * @author U0268
 *
 * @param <T>
 * @param <K>
 */
public interface MapperWithHistory<T extends RecordWithSinglePk<K>, K> {

	int insert(T savedata);
	
	int update(T savedata);
	
	int insertHistory(T savedata, String operation);
	
	int insertHistoryById(K pk, String operation);
	
	void delete(K pk);
}
