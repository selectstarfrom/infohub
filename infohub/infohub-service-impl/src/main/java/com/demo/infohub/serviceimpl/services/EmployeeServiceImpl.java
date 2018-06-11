package com.demo.infohub.serviceimpl.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.demo.infohub.dao.IEmployeeRepository;
import com.demo.infohub.models.Address;
import com.demo.infohub.models.Employee;
import com.demo.infohub.serviceapi.api.IEmployeeService;
import com.demo.infohub.serviceapi.constants.Constants;
import com.demo.infohub.serviceapi.dto.AddressDTO;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDTO getByEmailIdAndPassword(String pUsername, String pPassword) {
		Employee lSource = employeeRepository.getByEmailAndPassword(pUsername, pPassword);

		EmployeeDTO lTarget = copyToDTO(lSource);

		return lTarget;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public EmployeeDTO saveEmployee(EmployeeDTO pEmployeeDTO) {

		String lPassword = pEmployeeDTO.getPassword();
		if (StringUtils.isEmpty(lPassword)) {
			lPassword = Constants.DEFAULT_PASSWORD;
		}
		Employee lTargetEmployee = copyToEntity(pEmployeeDTO);

		lTargetEmployee = employeeRepository.save(lTargetEmployee);

		pEmployeeDTO.setId(lTargetEmployee.getId());

		return pEmployeeDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int deleteEmployee(Long pEmployeeId) {
		employeeRepository.delete(pEmployeeId);
		return 1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDTO getEmployeeById(Long pEmployeeId) {

		Employee lSource = employeeRepository.findOne(pEmployeeId);

		EmployeeDTO lTarget = copyToDTO(lSource);

		return lTarget;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDTO> getEmployeeByNameAndNationality(String pName, List<String> pNationalities) {

		List<EmployeeDTO> lResult = new ArrayList<EmployeeDTO>();
		List<Employee> lQueryResult = employeeRepository.getByNameAndNationality(pName, pNationalities);

		for (Employee lEmployee : lQueryResult) {
			lResult.add(copyToDTO(lEmployee));
		}

		return lResult;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDTO> getFewByNameAndNationality(String pName, List<String> pNationalities) {

		List<EmployeeDTO> lResult = new ArrayList<EmployeeDTO>();

		if (pName == null) {
			pName = "";
		}
		pName = pName.trim().toLowerCase();

		List<Object[]> lQueryResult = null;

		if (pNationalities == null || pNationalities.isEmpty()) {
			lQueryResult = employeeRepository.getFewByName(pName);
		} else {
			pNationalities = pNationalities.stream().map(p -> p.toLowerCase()).collect(Collectors.toList());
			lQueryResult = employeeRepository.getFewByNameAndNationality(pName, pNationalities);
		}

		for (Object[] lEmployee : lQueryResult) {
			Long lId = (Long) lEmployee[0];
			String lName = (String) lEmployee[1];
			String lEmail = (String) lEmployee[2];
			String lNationality = (String) lEmployee[3];
			String lMobile = (String) lEmployee[4];

			EmployeeDTO lDto = new EmployeeDTO();
			lDto.setAddress(new AddressDTO());
			lDto.setEmail(lEmail);
			lDto.setId(lId);
			lDto.setMobile(lMobile);
			lDto.setName(lName);
			lDto.setNationality(lNationality);

			lResult.add(lDto);
		}

		return lResult;
	}

	@Override
	public List<EmployeeDTO> getAll() {
		List<EmployeeDTO> lResult = new ArrayList<EmployeeDTO>();
		List<Employee> lQueryResult = employeeRepository.findAll();

		for (Employee lEmployee : lQueryResult) {
			lResult.add(copyToDTO(lEmployee));
		}

		return lResult;
	}

	private Employee copyToEntity(EmployeeDTO pSource, Employee pTarget) {
		BeanUtils.copyProperties(pSource, pTarget);
		if (pSource.getAddress() != null) {
			BeanUtils.copyProperties(pSource.getAddress(), pTarget.getAddress());
		}
		return pTarget;
	}

	private EmployeeDTO copyToDTO(Employee pSource, EmployeeDTO pTarget) {
		BeanUtils.copyProperties(pSource, pTarget);
		if (pSource.getAddress() != null) {
			BeanUtils.copyProperties(pSource.getAddress(), pTarget.getAddress());
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
		if (pEmployee == null) {
			return null;
		}
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
