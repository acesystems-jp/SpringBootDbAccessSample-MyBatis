package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
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
@Sql(scripts = "/unit/DenpyoMapperTest.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)	// 各テストメソッド実行前にテストデータ投入
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
		
		assertEquals("2020年3月25日", denpyo.getDenpyoDate().getJpString());
		assertEquals(1001, denpyo.getDenpyoNo());
		assertEquals("2020年3月5日 16時21分0秒", denpyo.getLastUpdateDatetime().getJpString());
		
		
		// 明細が2件
		assertEquals(2, denpyo.getDetails().size());
		
		var itemA001 = denpyo.getDetails().stream()
			.filter(dtl -> dtl.getItem().getCode().equals("A001"))
			.findFirst().get();
		
		assertEquals("マニピュレータ-指", itemA001.getItem().getName());
		assertEquals(5, itemA001.getSuryo().intValue());
		// 100.00, 120.00
		assertTrue(BigDecimal.valueOf(10000, 2).equals(itemA001.getTanka()));
		assertTrue(BigDecimal.valueOf(12000, 2).equals(itemA001.getItem().getBasicTanka()));
		
		
		var itemA002 = denpyo.getDetails().stream()
			.filter(dtl -> dtl.getItem().getCode().equals("A002"))
			.findFirst().get();
		assertEquals("マニピュレータ-手首", itemA002.getItem().getName());
		assertEquals(1, itemA002.getSuryo().intValue());
		// 600.00
		assertTrue(BigDecimal.valueOf(60000, 2).equals(itemA002.getTanka()));
		assertTrue(BigDecimal.valueOf(60000, 2).equals(itemA002.getItem().getBasicTanka()));
		
	}
	
	@Test
	@DisplayName("日付指定で親を2件取得")
	void testFindByDate() {
		Collection<DenpyoDataModel> denpyo = source.findByDate(new Date(LocalDate.of(2020,3,25)));
		
		assertEquals(2, denpyo.size());
		
		
	}

}
