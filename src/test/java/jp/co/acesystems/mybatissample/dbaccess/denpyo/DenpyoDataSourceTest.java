package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import jp.co.acesystems.mybatissample.domain.type.Date;

@MybatisTest	// データベース設定（DataSource）やMapperなどをDIコンテナに格納
@Sql(scripts = "/unit/DenpyoMapperTest.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class DenpyoDataSourceTest {
	
	@Autowired
	DenpyoMapper mapper;
	
	DenpyoDataSource source;

	@BeforeEach
	void beforeEach() {
		source = new DenpyoDataSource(mapper);
	}
	
	@Test
	@DisplayName("ID指定で1件取得。明細は2件")
	void testFindById() {
		Optional<DenpyoDataModel> denpyoOpt = source.findById(1);
		
		// 1件取得できている
		assertTrue(denpyoOpt.isPresent());
		
		DenpyoDataModel denpyo = denpyoOpt.get();
		assertEquals(2, denpyo.getDetails().size());
	}
	
	@Test
	@DisplayName("日付指定で親を2件取得")
	void testFindByDate() {
		Collection<DenpyoDataModel> denpyo = source.findByDate(new Date(LocalDate.of(2020,3,25)));
		
		assertEquals(2, denpyo.size());
		
		
	}

}
