package com.demo.infohub.serviceapi.api;

import java.util.List;

import com.demo.infohub.serviceapi.dto.EmployeeDTO;

public interface IEmployeeService {

	EmployeeDTO saveEmployee(EmployeeDTO pEmployeeDTO);

	int deleteEmployee(Long pEmployeeId);

	EmployeeDTO getEmployeeById(Long pEmployeeId);

	List<EmployeeDTO> getEmployeeByName(String pEmployeeName);

	List<EmployeeDTO> getEmployeeByNationality(String pEmployeeNationality);

	List<EmployeeDTO> getAll();

}
