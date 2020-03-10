package jp.co.acesystems.mybatissample.repository;

import jp.co.acesystems.mybatissample.repository.datamodel.RecordWithSinglePk;

/**
 * Insert、Updateと同時に履歴テーブルに1件書き込む処理を受け持つ
 * @author U0268
 *
 * @param <T> 対象テーブル（元テーブル）のデータモデル
 * @param <PK> 対象テーブルのPK項目の型
 */
public class HistorySaver<T extends RecordWithSinglePk<PK>, PK> {
	
	private enum Operation{
		INSERT,
		UPDATE,
		DELETE
	}

	MapperWithHistory<T, PK> mapper;
	
	/**
	 * コンストラクタ
	 * @param mapper 履歴への書き込み処理を持つMapper
	 */
	public HistorySaver(MapperWithHistory<T, PK> mapper) {
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
		if(savedata.getId() == null) {
			mapper.insert(working); 
			mapper.insertHistory((T)working.clone(), Operation.INSERT.toString());
			return working;
		}
		
		// idがあれば更新
		int cnt = mapper.insertHistoryById(working.getId(), Operation.UPDATE.toString());
		if(cnt > 0) {
			mapper.update(working);
			return working;
		}
		
		// 更新対象がなければInsert
		mapper.insert(working);
		mapper.insertHistory((T)working.clone(), Operation.INSERT.toString());
		return working;
	}
	
	/**
	 * レコードを1件削除
	 * @param pk
	 */
	public void delete(final PK pk) {
		
		mapper.insertHistoryById(pk, Operation.DELETE.toString());
		mapper.delete(pk);
	}
}
