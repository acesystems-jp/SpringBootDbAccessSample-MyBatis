package jp.co.acesystems.mybatissample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;

/**
 * 
 * @author U0268
 *
 */
@MybatisTest
@Sql(scripts = "/unit/EmployeeMapperTest.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "/unit/AllDelete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)	//インメモリH2DBなのでテストケース毎に勝手にクリアされる
class EmployeeMapperTest {

	@Autowired
	EmployeeMapper mapper;
	
	@Test
	@DisplayName("PK指定-1件取得")
	void findById() {
		Integer id = Integer.valueOf(1);
		Optional<EmployeeDataModel> model = mapper.findById(id);
		
		assertTrue(model.isPresent());
		
		assertEquals(id.intValue(), model.get().getId().intValue());
	}
	
	
	@Test
	@DisplayName("登録処理")
	void insert() {
		EmployeeDataModel entity = new EmployeeDataModel("野口", "u1876", LocalDateTime.of(2020, 3, 6, 16, 47, 23), 1);
		// 登録前の件数
		int count = mapper.findAll().size();
		
		// 登録実行
		EmployeeDataModel saved = mapper.save(entity);
		
		
		// idが自動取得されてnullでない
		assertNotNull(saved.getId());
		assertNotEquals(entity.getId(), saved.getId());
		// 登録後の項目が元のオブジェクトと一致する
		assertEquals(entity.getName(), saved.getName());
		assertEquals(entity.getCode(), saved.getCode());
		assertEquals(entity.getLastUpdateDatetime(), saved.getLastUpdateDatetime());
		assertEquals(entity.getLastUpdateEmployeeId(), saved.getLastUpdateEmployeeId());
		
		
		// 登録後に件数が1件増えている
		List<EmployeeDataModel> list = mapper.findAll();
		assertEquals(count + 1, list.size());
	}
	
	@Test
	@DisplayName("更新処理")
	void update() {
		
		// 更新用データを登録
		EmployeeDataModel entity = new EmployeeDataModel(100, "馬宿", "u0600", LocalDateTime.of(2020, 3, 6, 18, 3, 5), 1);
		
		EmployeeDataModel saved = mapper.save(entity);
		assertEquals(entity.getId(), saved.getId());
		
		
		// 更新処理
		EmployeeDataModel update = new EmployeeDataModel(100, "皇子", "u0599", entity.getLastUpdateDatetime().plusMinutes(1), 2);
		EmployeeDataModel updated = mapper.save(update);
		
		
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
	}
	

}
