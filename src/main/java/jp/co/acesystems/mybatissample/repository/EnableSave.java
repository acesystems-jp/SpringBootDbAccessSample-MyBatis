package jp.co.acesystems.mybatissample.repository;

import jp.co.acesystems.mybatissample.repository.datamodel.HavingSinglePk;

/**
 * saveメソッドを使えるようにするInterface
 * @author U0268
 *
 * @param <T> 該当レコードに対応する型
 * @param <PK> PK項目の型
 */
public interface EnableSave<T extends HavingSinglePk<PK>, PK> {
	public int insert(T savedata);
	public int update(T savedata);
	
	/**
	 * 登録、または更新する
	 * @param savedata
	 * @return 
	 */
	public default T save(final T savedata) {
		// insert,updateで中身が書き換えられてしまうためディープコピーする
		@SuppressWarnings("unchecked")
		T working = (T)savedata.clone();
		
		if(savedata.getId() == null) {
			insert(working); 
			return working;
		}
		
		int cnt = update(working);
		if(cnt > 0) {
			return working;
		}
		
		insert(working);
		return working;
	}
	
}
