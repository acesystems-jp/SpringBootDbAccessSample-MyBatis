package jp.co.acesystems.mybatissample.repository;

/**
 * 履歴への書き込み処理を持ったMapperを表すInterface
 * @author U0268
 *
 * @param <T>
 * @param <PK>
 */
public interface MapperWithHistory<T extends RecordWithSinglePk<PK>, PK> {

	int insert(T savedata);
	int update(T savedata);
	int insertHistory(T savedata, String operation);
	int insertHistoryById(PK pk, String operation);
	void delete(PK pk);
}
