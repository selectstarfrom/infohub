package com.demo.infohub.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.infohub.models.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
