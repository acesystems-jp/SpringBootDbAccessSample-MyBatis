package jp.co.acesystems.mybatissample.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.acesystems.mybatissample.dbaccess.employee.EmployeeDataModel;
import jp.co.acesystems.mybatissample.dbaccess.employee.EmployeeDataSource;
import jp.co.acesystems.mybatissample.domain.entity.Employee;
import jp.co.acesystems.mybatissample.domain.type.DateTime;

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
	
	/**
	 * 1件取得
	 * @param id
	 * @return
	 */
	@GetMapping("")
	public Employee getEmployee(@RequestParam("id") Integer id) {
		var model = employeeDataSource.findById(id);
		if (model.isEmpty()) {
			return null;
		}
		return new Employee(model.get());
	}
	
	/**
	 * 1件取得してコピー保存
	 * @param id
	 * @return
	 */
	@GetMapping("copy")
	public Employee copyEmployee(@RequestParam("id") Integer id) {
		var model = employeeDataSource.findById(id);
		if (model.isEmpty()) {
			return null;
		}
		
		var ex = model.get();
		var copy = new EmployeeDataModel(ex.getName(), ex.getCode(), DateTime.now(), 99);
		employeeDataSource.save(copy);
		return new Employee(copy);
	}
}
