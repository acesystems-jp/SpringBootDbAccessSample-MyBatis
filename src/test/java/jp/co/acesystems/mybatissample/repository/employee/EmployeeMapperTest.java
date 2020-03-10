package jp.co.acesystems.mybatissample.repository.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import jp.co.acesystems.mybatissample.domain.type.DateTime;
import jp.co.acesystems.mybatissample.repository.HistoryOperation;
import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;
import jp.co.acesystems.mybatissample.test.repository.employee.EmployeeHistoryDataModel;
import jp.co.acesystems.mybatissample.test.repository.employee.EmployeeHistoryMapper;

/**
 * 社員テーブル処理のテスト
 * @author U0268
 *
 */
@MybatisTest	// データベース設定（DataSource）やMapperなどをDIコンテナに格納
@Sql(scripts = "/unit/EmployeeMapperTest.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "/unit/AllDelete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)	//インメモリH2DBなのでテストケース毎に勝手にクリアされる
class EmployeeMapperTest {

	@Autowired
	EmployeeMapper mapper;
	
	EmployeeDataSource source;
	
	@Autowired
	EmployeeHistoryMapper historyMapper;
	
	
	@BeforeEach
	void beforeAll() {
		source = new EmployeeDataSource(mapper);
	}
	
	@Test
	@DisplayName("PK指定-1件取得")
	void findById() {
		Integer id = Integer.valueOf(1);
		Optional<EmployeeDataModel> model = source.findById(id);
		
		assertTrue(model.isPresent());
		
		assertEquals(id.intValue(), model.get().getId().intValue());
	}
	
	
	@Test
	@DisplayName("登録処理")
	void insert() {
		EmployeeDataModel entity = new EmployeeDataModel("野口", "u1876", DateTime.of(2020, 3, 6, 16, 47, 23), 1);
		// 登録前の件数
		int count = source.findAll().size();
		
		// 登録実行
		EmployeeDataModel saved = source.save(entity);
		
		
		// idが自動取得されてnullでない
		assertNotNull(saved.getId());
		assertNotEquals(entity.getId(), saved.getId());
		// 登録後の項目が元のオブジェクトと一致する
		assertEquals(entity.getName(), saved.getName());
		assertEquals(entity.getCode(), saved.getCode());
		assertEquals(entity.getLastUpdateDatetime(), saved.getLastUpdateDatetime());
		assertEquals(entity.getLastUpdateEmployeeId(), saved.getLastUpdateEmployeeId());
		
		
		// 登録後に件数が1件増えている
		List<EmployeeDataModel> list = source.findAll();
		assertEquals(count + 1, list.size());
	}
	
	@Test
	@DisplayName("更新処理")
	void update() {
		
		
		DateTime now = DateTime.Now();
		
		// 更新用データを登録
		EmployeeDataModel entity = new EmployeeDataModel(100, "馬宿", "u0600", DateTime.of(2020, 3, 6, 18, 3, 5), 1);
		
		EmployeeDataModel saved = source.save(entity);
		assertEquals(entity.getId(), saved.getId());
		
		
		// 更新処理
		EmployeeDataModel update = new EmployeeDataModel(100, "皇子", "u0599", entity.getLastUpdateDatetime().plusMinutes(1), 2);
		EmployeeDataModel updated = source.save(update);
		
		
		// 更新後の値が更新用オブジェクトの値になっている
		assertEquals(update.getId(), updated.getId());
		assertEquals(update.getName(), updated.getName());
		assertEquals(update.getCode(), updated.getCode());
		assertEquals(update.getLastUpdateDatetime(), updated.getLastUpdateDatetime());
		assertEquals(update.getLastUpdateEmployeeId(), updated.getLastUpdateEmployeeId());
		
		// 更新後の値と初期値は一致しない
		assertEquals(entity.getId(), updated.getId());
		assertNotEquals(entity.getName(), updated.getName());
		assertNotEquals(entity.getCode(), updated.getCode());
		assertNotEquals(entity.getLastUpdateDatetime(), updated.getLastUpdateDatetime());
		assertNotEquals(entity.getLastUpdateEmployeeId(), updated.getLastUpdateEmployeeId());
		
		
		// 履歴テーブル取得
		List<EmployeeHistoryDataModel> history = historyMapper.findByBaseId(entity.getId());
		assertEquals(2, history.size());
		
		// INSERTの履歴が登録されている
		Optional<EmployeeHistoryDataModel> history_1
			= history.stream()
			.filter(e -> e.getName().equals(entity.getName()))
			.findFirst();
		
		assertTrue(history_1.isPresent());
		assertEquals(entity.getCode(),  history_1.get().getCode());
		assertEquals(HistoryOperation.INSERT.toString(), history_1.get().getOperation());
		assertTrue(history_1.get().getOperationDatetime().isAfter(now));
		

		// UPDATE前の履歴が登録されている
		Optional<EmployeeHistoryDataModel> history_2
			= history.stream()
			.filter(e -> e.getOperation().equals(HistoryOperation.UPDATE.toString()))
			.findFirst();
		
		assertTrue(history_2.isPresent());
		assertEquals(entity.getCode(),  history_2.get().getCode());
		assertEquals(entity.getName(), history_2.get().getName());
		

		// UPDATE後の履歴は入っていない
		Optional<EmployeeHistoryDataModel> history_3
			= history.stream()
			.filter(e -> e.getName().equals(update.getName()))
			.findFirst();
		
		assertTrue(history_3.isEmpty());
	}
	
	@Test
	@DisplayName("登録処理、一意制約違反")
	void duplicate() {
		
		EmployeeDataModel entity_1 = new EmployeeDataModel("松坂", "u1980", DateTime.of(2020, 3, 9, 13, 20, 15), 1);
		EmployeeDataModel entity_2 = new EmployeeDataModel("藤川", "u1980", DateTime.of(2020, 3, 9, 13, 20, 16), 1);
		
		source.save(entity_1);
		
		// 重複を検出
		assertTrue(source.exists(entity_2));
		
		// 一意制約違反が発生したら DuplicateKeyException をThrowする
		assertThrows(org.springframework.dao.DuplicateKeyException.class, () -> source.save(entity_2));
		
	}
}
