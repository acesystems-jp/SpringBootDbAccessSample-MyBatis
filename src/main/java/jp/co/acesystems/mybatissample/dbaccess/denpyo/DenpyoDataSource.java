package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.acesystems.mybatissample.domain.type.Date;

/**
 * 伝票のDB連携処理
 * Mapperをそのまま使うとMyBatisの仕様に引きずられるので
 * DataSourceを挟む
 * @author U0268
 *
 */
@Repository
public class DenpyoDataSource {
	private final DenpyoMapper mapper;
	
	/**
	 * コンストラクタインジェクション
	 * @param mapper
	 */
	DenpyoDataSource(DenpyoMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param id PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	public Optional<DenpyoDataModel> findById(Integer id) {
		return mapper.findById(id);
	}
	
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param denpyoDate PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	public List<DenpyoDataModel> findByDate(Date denpyoDate) {
		return mapper.findByDate(denpyoDate);
	}
}
