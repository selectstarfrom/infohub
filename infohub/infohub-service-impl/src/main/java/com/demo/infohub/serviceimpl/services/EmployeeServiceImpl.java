package com.demo.infohub.serviceimpl.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.infohub.dao.IEmployeeRepository;
import com.demo.infohub.models.Address;
import com.demo.infohub.models.Employee;
import com.demo.infohub.serviceapi.api.IEmployeeService;
import com.demo.infohub.serviceapi.dto.AddressDTO;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public EmployeeDTO saveEmployee(EmployeeDTO pEmployeeDTO) {

		Employee lTargetEmployee = copyToEntity(pEmployeeDTO);

		lTargetEmployee = employeeRepository.save(lTargetEmployee);

		pEmployeeDTO.setId(lTargetEmployee.getId());

		return pEmployeeDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int deleteEmployee(Long pEmployeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDTO getEmployeeById(Long pEmployeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDTO> getEmployeeByName(String pEmployeeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDTO> getEmployeeByNationality(String pEmployeeNationality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> getAll() {
		List<EmployeeDTO> lResult = new ArrayList<EmployeeDTO>();
		List<Employee> findAll = employeeRepository.findAll();

		for (Employee lEmployee : findAll) {
			lResult.add(copyToDTO(lEmployee));
		}

		return lResult;
	}

	private Employee copyToEntity(EmployeeDTO pSource, Employee pTarget) {
		BeanUtils.copyProperties(pSource, pTarget);
		if (pSource.getAddress() != null) {
			BeanUtils.copyProperties(pSource.getAddress(), pTarget);
		}
		return pTarget;
	}

	private EmployeeDTO copyToDTO(Employee pSource, EmployeeDTO pTarget) {
		BeanUtils.copyProperties(pSource, pTarget);
		if (pSource.getAddress() != null) {
			BeanUtils.copyProperties(pSource.getAddress(), pTarget);
		}
		return pTarget;
	}

	private Employee copyToEntity(EmployeeDTO pEmployeeDTO) {
		Employee lTargetEmployee = new Employee();
		Address lTargetAddress = new Address();
		lTargetEmployee.setAddress(lTargetAddress);
		copyToEntity(pEmployeeDTO, lTargetEmployee);
		return lTargetEmployee;
	}

	private EmployeeDTO copyToDTO(Employee pEmployee) {
		EmployeeDTO lTargetEmployee = new EmployeeDTO();
		AddressDTO lTargetAddress = new AddressDTO();
		lTargetEmployee.setAddress(lTargetAddress);

		BeanUtils.copyProperties(pEmployee, lTargetEmployee);
		Address lAddress = pEmployee.getAddress();
		if (lAddress != null) {
			BeanUtils.copyProperties(lAddress, lTargetAddress);
		}

		return lTargetEmployee;
	}

}
