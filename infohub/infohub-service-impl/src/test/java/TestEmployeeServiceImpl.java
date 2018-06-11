import com.demo.infohub.serviceapi.api.IEmployeeService;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;

public class TestEmployeeServiceImpl {

	private IEmployeeService employeeService;

	public void testSaveEmployee1() {
		EmployeeDTO lSaved = employeeService.saveEmployee(new EmployeeDTO());
	}
}
