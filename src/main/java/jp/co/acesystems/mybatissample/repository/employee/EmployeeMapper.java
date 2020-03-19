package jp.co.acesystems.mybatissample.repository.employee;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.acesystems.mybatissample.repository.MapperWithHistory;

/**
 * 社員マスタを操作するMybatisのMapperInterface
 * @author U0268
 *
 */
@Mapper	// MyBatisのMapperとしてSpringにBean登録するアノテーション
interface EmployeeMapper extends MapperWithHistory<EmployeeDataModel, Integer> {
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param id PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	Optional<EmployeeDataModel> findById(Integer id);
	
	/**
	 * 社員コードで1件取得
	 * @param code 社員コード
	 * @return
	 */
	Optional<EmployeeDataModel> findByCode(String code);

	/**
	 * 全件取得
	 * 全件取得はテーブルの件数を考えて作ること！！！
	 * （トランザクションなど件数の多いテーブルには作らない）
	 * @return
	 */
	List<EmployeeDataModel> findAll();
	
	/**
	 * 1件登録
	 * @param 登録データ
	 * @return 
	 */
	@Override
	int insert(EmployeeDataModel savedata);
	
	/**
	 * PKを条件に1件更新
	 * @param 登録データ
	 * @return
	 */
	@Override
	int update(EmployeeDataModel savedata);
	
	/**
	 * 履歴テーブルに1件書き込む
	 * @param savedata 元テーブルデータ
	 * @return
	 */
	@Override
	int insertHistory(@Param("savedata") EmployeeDataModel savedata, @Param("operation") String operation);
	
	/**
	 * 履歴テーブルに1件書き込む
	 * @param id 元テーブルデータのPK
	 * @return
	 */
	@Override
	int insertHistoryById(@Param("id") Integer id, @Param("operation") String operation);

}
