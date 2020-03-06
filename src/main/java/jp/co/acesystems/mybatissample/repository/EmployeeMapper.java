package jp.co.acesystems.mybatissample.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;

/**
 * 社員マスタを操作するMybatisのMapperInterface
 * @author U0268
 *
 */
@Mapper	// MyBatisのMapperとしてSpringにBean登録するアノテーション
public interface EmployeeMapper extends EnableSave<EmployeeDataModel, Integer> {
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param id PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	Optional<EmployeeDataModel> findById(Integer id);
	
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
	int insert(EmployeeDataModel savedata);
	
	/**
	 * PKを条件に1件更新
	 * @param 登録データ
	 * @return
	 */
	int update(EmployeeDataModel savedata);
	

}
