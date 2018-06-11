package com.demo.infohub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.infohub.models.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
