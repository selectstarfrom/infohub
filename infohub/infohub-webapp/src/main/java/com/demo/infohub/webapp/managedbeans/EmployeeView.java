package com.demo.infohub.webapp.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.infohub.serviceapi.api.NationalityList;
import com.demo.infohub.serviceapi.dto.AddressDTO;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;
import com.demo.infohub.serviceimpl.services.EmployeeServiceImpl;
import com.demo.infohub.webapp.selectablemodels.EmployeeDataModel;

@ManagedBean(name = "employeeView")
@ViewScoped
public class EmployeeView extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = 8816016836035127826L;

	private boolean viewMode = true;
	private String searchName;
	private List<String> searchNationalities;
	private EmployeeDTO employee;
	private EmployeeDataModel employees;

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

		boolean lUpdate = true;
		if (employee.getId() == null) {
			lUpdate = false;
		}
		try {
			employee = employeeService.saveEmployee(employee);
		} catch (Exception e) {
			if (lUpdate) {
				error("Error occurred while updating Employee details. Please contact admin.", "pg-root-msg");
			} else {
				error("Error occurred while saving Employee details. Please contact admin.", "pg-root-msg");
			}
			return;
		}

		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-employee-details').hide();");
		if (lUpdate) {
			info("Employee details updated Successfully.", "pg-root-msg");
		} else {
			info("Employee details saved Successfully.", "pg-root-msg");
		}
		seacrhEmployee();
		setViewMode(true);

	}

	public void deleteEmployee() {

		try {
			employeeService.deleteEmployee(getEmployee().getId());
		} catch (Exception e) {
			error("Error occurred while deleting Employee details. Please contact admin.", "pg-root-msg");
			return;
		}

		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-delete-employee').hide();");
		info("Employee details deleted Successfully.", "pg-root-msg");
		seacrhEmployee();
		setViewMode(true);

	}

	public void showNewEmployeeActionListener() {
		this.employee = newEmployeeInstance();
		setViewMode(false);
	}

	public void showViewEmployeeActionListener(Long pEmployeeId) {
		setViewMode(true);
		EmployeeDTO lById = employeeService.getEmployeeById(pEmployeeId);
		setEmployee(lById);
		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-employee-details').show();");
	}

	public void showDeleteEmployeeActionListener(Long pEmployeeId) {
		setViewMode(true);
		EmployeeDTO lById = employeeService.getEmployeeById(pEmployeeId);
		setEmployee(lById);
		setViewMode(true);
		PrimeFaces pf = PrimeFaces.current();
		pf.executeScript("PF('dlg-delete-employee').show();");
	}

	public void makeDetailsEditableActionListener() {
		setViewMode(false);
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

	public EmployeeDataModel getEmployees() {
		return employees;
	}

	public void setEmployees(EmployeeDataModel employees) {
		this.employees = employees;
	}

	public List<String> getNations() {
		return NationalityList.ALL;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}

}
