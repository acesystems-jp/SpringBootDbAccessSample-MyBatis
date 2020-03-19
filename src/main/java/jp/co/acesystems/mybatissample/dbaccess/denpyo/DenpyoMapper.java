package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.acesystems.mybatissample.dbaccess.MapperWithHistory;
import jp.co.acesystems.mybatissample.domain.type.Date;

/**
 * 伝票取得処理
 * @author U0268
 *
 */
@Mapper	// MyBatisのMapperとしてSpringにBean登録するアノテーション
interface DenpyoMapper extends MapperWithHistory<DenpyoDataModel, Integer> {
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param id PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	Optional<DenpyoDataModel> findById(Integer id);

	/**
	 * 日付を指定して複数件取得
	 * SQLはXMLファイルに記述する
	 * @param denpyoDate 伝票日付
	 * @return 
	 */
	List<DenpyoDataModel> findByDate(Date denpyoDate);
	

	
	/**
	 * 1件登録
	 * @param savedata
	 * @return 登録後データ
	 */
	@Override
	int insert(DenpyoDataModel savedata);
	
	/**
	 * PKを条件に1件更新
	 * @param savedata
	 * @return 登録後データ
	 */
	@Override
	int update(DenpyoDataModel savedata);
	
	/**
	 * 履歴テーブルに1件書き込む
	 * @param savedata 元テーブルデータ
	 * @return
	 */
	@Override
	int insertHistory(@Param("savedata") DenpyoDataModel savedata, @Param("operation") String operation);
	
	/**
	 * 履歴テーブルに1件書き込む
	 * @param id 元テーブルデータのPK
	 * @return
	 */
	@Override
	int insertHistoryById(@Param("id") Integer id, @Param("operation") String operation);

}
