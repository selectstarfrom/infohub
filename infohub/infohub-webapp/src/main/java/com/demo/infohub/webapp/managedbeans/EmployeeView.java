package com.demo.infohub.webapp.managedbeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.infohub.serviceapi.dto.AddressDTO;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;
import com.demo.infohub.serviceimpl.services.EmployeeServiceImpl;
import com.demo.infohub.webapp.selectablemodels.EmployeeDataModel;

@ManagedBean(name = "employeeView")
@ViewScoped
public class EmployeeView extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = 8816016836035127826L;

	private String searchName;
	private List<String> searchNationalities;
	private EmployeeDTO employee;
	private EmployeeDTO selectedEmployee;
	private EmployeeDataModel employees;

	// @formatter:off
	private List<String> nations = Arrays.asList(
			"Australia",
			"Bangladesh",
			"Canada",
			"Denmark",
			"Egypt",
			"Finland"
			);
	// @formatter:on

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostConstruct
	public void init() {
		super.init();
	}

	public void initialize() {
		this.employee = newEmployeeInstance();
	}

	private EmployeeDTO newEmployeeInstance() {
		EmployeeDTO lEmployeeDTO = new EmployeeDTO();
		lEmployeeDTO.setAddress(new AddressDTO());
		return lEmployeeDTO;
	}

	public void saveEmployee() {
		try {
			employee = employeeService.saveEmployee(employee);
		} catch (Exception e) {
			error("Error occurred while saving Employee details. Please contact admin.", "pg-root-msg");
		}

		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-new-employee').hide();");

		info("Employee details saved Successfully.", "pg-root-msg");

	}

	public void updateEmployee() {
		try {
			employee = employeeService.saveEmployee(employee);
		} catch (Exception e) {
			error("Error occurred while updating Employee details. Please contact admin.", "pg-root-msg");
		}

		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-new-employee').hide();");

		info("Employee details updated Successfully.", "pg-root-msg");

	}

	public void selectEmployeeDetailActionListener(SelectEvent pEvent) {
		EmployeeDTO lObject = (EmployeeDTO) pEvent.getObject();
		EmployeeDTO lById = employeeService.getEmployeeById(lObject.getId());
		setSelectedEmployee(lById);
	}

	public void showNewEmployeeActionListener() {
		this.employee = newEmployeeInstance();
	}

	public void showViewEmployeeActionListener(Long pEmployeeId) {
		EmployeeDTO lById = employeeService.getEmployeeById(pEmployeeId);
		setEmployee(lById);
		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-view-employee').show();");
	}

	public void seacrhEmployee() {

		String lSearchName = getSearchName();
		List<String> lSearchNationalities = getSearchNationalities();

		List<EmployeeDTO> lAllEmployees = employeeService.getFewByNameAndNationality(lSearchName, lSearchNationalities);
		employees = new EmployeeDataModel(lAllEmployees);

	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<String> getSearchNationalities() {
		return searchNationalities;
	}

	public void setSearchNationalities(List<String> searchNationalities) {
		this.searchNationalities = searchNationalities;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public EmployeeDTO getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(EmployeeDTO selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public EmployeeDataModel getEmployees() {
		return employees;
	}

	public void setEmployees(EmployeeDataModel employees) {
		this.employees = employees;
	}

	public List<String> getNations() {
		return nations;
	}

	public void setNations(List<String> nations) {
		this.nations = nations;
	}

}
