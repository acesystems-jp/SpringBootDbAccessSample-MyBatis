package jp.co.acesystems.mybatissample.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;

/**
 * 社員マスタを操作するMybatisのMapperInterface
 * @author U0268
 *
 */
@Mapper	// MyBatisのMapperとしてSpringにBean登録するアノテーション
public interface EmployeeMapper {
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param id PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	Optional<EmployeeDataModel> findById(Integer id);
}
