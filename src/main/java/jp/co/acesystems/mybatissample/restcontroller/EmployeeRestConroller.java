package jp.co.acesystems.mybatissample.restcontroller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.acesystems.mybatissample.domain.entity.Employee;
import jp.co.acesystems.mybatissample.repository.EmployeeMapper;
import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;

/**
 * 社員を操作するためのRestController
 * @author U0268
 *
 */
@RestController
@RequestMapping("employee")
public class EmployeeRestConroller {

	EmployeeMapper employeeMapper;
	
	EmployeeRestConroller(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}
	
	@GetMapping("")
	public Employee getEmployee(@RequestParam("id") Integer id) {
		var model = employeeMapper.findById(id);
		if(model.isEmpty()) {
			return null;
		}
		return new Employee(model.get());
	}
	
	@GetMapping("copy")
	public Employee copyEmployee(@RequestParam("id") Integer id) {
		var model = employeeMapper.findById(id);
		if(model.isEmpty()) {
			return null;
		}
		
		var ex = model.get();
		var copy = new EmployeeDataModel(ex.getName(), ex.getCode(), LocalDateTime.now(), 99);
		var rtn = employeeMapper.insert(copy);
		return new Employee(copy);
	}
}
