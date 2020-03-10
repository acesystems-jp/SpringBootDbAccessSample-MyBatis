package jp.co.acesystems.mybatissample.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.acesystems.mybatissample.domain.entity.Employee;
import jp.co.acesystems.mybatissample.domain.type.DateTime;
import jp.co.acesystems.mybatissample.repository.datamodel.EmployeeDataModel;
import jp.co.acesystems.mybatissample.repository.employee.EmployeeDataSource;

/**
 * 社員を操作するためのRestController
 * @author U0268
 *
 */
@RestController
@RequestMapping("employee")
public class EmployeeRestConroller {

	EmployeeDataSource employeeDataSource;
	
	EmployeeRestConroller(EmployeeDataSource employeeDataSource) {
		this.employeeDataSource = employeeDataSource;
	}
	
	@GetMapping("")
	public Employee getEmployee(@RequestParam("id") Integer id) {
		var model = employeeDataSource.findById(id);
		if(model.isEmpty()) {
			return null;
		}
		return new Employee(model.get());
	}
	
	@GetMapping("copy")
	public Employee copyEmployee(@RequestParam("id") Integer id) {
		var model = employeeDataSource.findById(id);
		if(model.isEmpty()) {
			return null;
		}
		
		var ex = model.get();
		var copy = new EmployeeDataModel(ex.getName(), ex.getCode(), DateTime.Now(), 99);
		employeeDataSource.save(copy);
		return new Employee(copy);
	}
}
