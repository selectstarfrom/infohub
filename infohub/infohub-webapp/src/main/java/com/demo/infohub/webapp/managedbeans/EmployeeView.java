package com.demo.infohub.webapp.managedbeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.demo.infohub.serviceapi.dto.EmployeeDTO;
import com.demo.infohub.serviceimpl.services.EmployeeServiceImpl;
import com.demo.infohub.webapp.selectablemodels.EmployeeDataModel;

@ManagedBean(name = "employeeView")
@ViewScoped
public class EmployeeView implements Serializable {

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
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public void initialize() {
		this.employee = new EmployeeDTO();
	}

	private EmployeeDTO newEmployeeInstance() {
		return new EmployeeDTO();
	}

	public void saveEmployee() {

		employee = employeeService.saveEmployee(employee);

	}

	public void seacrhEmployee() {

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
