package jp.co.acesystems.mybatissample.test.dbaccess.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeHistoryMapper {

	public List<EmployeeHistoryDataModel> findByBaseId(Integer id);
	
}
