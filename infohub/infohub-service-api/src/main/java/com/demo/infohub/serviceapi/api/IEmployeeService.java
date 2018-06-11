package com.demo.infohub.serviceapi.api;

import java.util.List;

import com.demo.infohub.serviceapi.dto.EmployeeDTO;

public interface IEmployeeService {

	EmployeeDTO saveEmployee(EmployeeDTO pEmployeeDTO);

	int deleteEmployee(Long pEmployeeId);

	EmployeeDTO getEmployeeById(Long pEmployeeId);

	List<EmployeeDTO> getEmployeeByNameAndNationality(String pName, List<String> pNationalities);

	List<EmployeeDTO> getAll();

	List<EmployeeDTO> getFewByNameAndNationality(String pName, List<String> pNationalities);

	EmployeeDTO getByEmailIdAndPassword(String username, String password);

}
