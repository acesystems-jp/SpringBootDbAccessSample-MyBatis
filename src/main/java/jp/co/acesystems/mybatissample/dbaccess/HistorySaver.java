package jp.co.acesystems.mybatissample.dbaccess;

/**
 * Insert、Updateと同時に履歴テーブルに1件書き込む処理を受け持つ
 * @author U0268
 *
 * @param <T> 対象テーブル（元テーブル）のデータモデル
 * @param <K> 対象テーブルのPK項目の型
 */
public class HistorySaver<T extends RecordWithSinglePk<K>, K> {
	
	MapperWithHistory<T, K> mapper;
	
	/**
	 * コンストラクタ
	 * @param mapper 履歴への書き込み処理を持つMapper
	 */
	public HistorySaver(MapperWithHistory<T, K> mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * InsertまたはUpdateを実行する。
	 * Updateのときは元レコードを
	 * @param savedata
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T save(final T savedata) {
		// insert,updateで中身が書き換えられてしまうためディープコピーする
		T working = (T)savedata.clone();
		
		// idが無ければInsert
		if (savedata.getId() == null) {
			mapper.insert(working); 
			mapper.insertHistory((T)working.clone(), HistoryOperation.INSERT.toString());
			return working;
		}
		
		// idがあれば更新
		int cnt = mapper.insertHistoryById(working.getId(), HistoryOperation.UPDATE.toString());
		if (cnt > 0) {
			mapper.update(working);
			return working;
		}
		
		// 更新対象がなければInsert
		mapper.insert(working);
		mapper.insertHistory((T)working.clone(), HistoryOperation.INSERT.toString());
		return working;
	}
	
	/**
	 * レコードを1件削除
	 * @param pk
	 */
	public void delete(final K pk) {
		
		mapper.insertHistoryById(pk, HistoryOperation.DELETE.toString());
		mapper.delete(pk);
	}
}
