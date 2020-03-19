package jp.co.acesystems.mybatissample.dbaccess.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.acesystems.mybatissample.dbaccess.HistorySaver;

/**
 * Mapperをそのまま使うとMyBatisの仕様に引きずられるので
 * DataSourceを挟む
 * @author U0268
 *
 */
@Repository
public class EmployeeDataSource {

	private final EmployeeMapper mapper;
	private final HistorySaver<EmployeeDataModel, Integer> historySaver;
	
	EmployeeDataSource(EmployeeMapper mapper) {
		this.mapper = mapper;
		this.historySaver = new HistorySaver<EmployeeDataModel, Integer>(mapper);
	}
	
	/**
	 * PKを指定して1件取得
	 * SQLはXMLファイルに記述する
	 * @param id PK項目
	 * @return 取得できない場合はNullとなるためOptionalで返す
	 */
	public Optional<EmployeeDataModel> findById(Integer id) {
		return mapper.findById(id);
	}
	
	public Optional<EmployeeDataModel> findByCode(String code) {
		return mapper.findByCode(code);
	}
	
	
	/**
	 * 全件取得
	 * 全件取得はテーブルの件数を考えて作ること！！！
	 * （トランザクションなど件数の多いテーブルには作らない）
	 * @return
	 */
	public List<EmployeeDataModel> findAll() {
		return mapper.findAll();
	}

	/**
	 * 対象データを保存する
	 * @param savedata 登録データ
	 * @return
	 */
	public EmployeeDataModel save(EmployeeDataModel savedata) {
		return historySaver.save(savedata);
	}
	
	/**
	 * 対象データの有無を調べる
	 * @param source
	 * @return
	 */
	public boolean exists(EmployeeDataModel source) {
		var rtnById = findById(source.getId());
		if (rtnById.isPresent()) {
			return true;
		}
		
		var rtnByCode = findByCode(source.getCode());
		return rtnByCode.isPresent();
		
	}
	
	
	
	
}
