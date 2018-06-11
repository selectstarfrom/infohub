package com.demo.infohub.serviceimpl.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.infohub.serviceapi.api.IEmployeeService;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO pEmployeeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmployee(EmployeeDTO pEmployeeDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(Long pEmployeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EmployeeDTO getEmployeeById(Long pEmployeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> getEmployeeByName(String pEmployeeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> getEmployeeByNationality(String pEmployeeNationality) {
		// TODO Auto-generated method stub
		return null;
	}

}
