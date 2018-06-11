package com.demo.infohub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.infohub.models.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

	String BY_NAME_LIKE_AND_NATIONALITY = "SELECT e FROM Employee e WHERE lower(e.name) LIKE CONCAT('%',:pName,'%') and lower(e.nationality) IN :pNationalities";

	String FEW_BY_NAME_LIKE_AND_NATIONALITY = "SELECT e.id, e.name, e.email, e.nationality, e.mobile FROM Employee e WHERE lower(e.name) LIKE CONCAT('%',:pName,'%') and lower(e.nationality) IN :pNationalities";

	String FEW_BY_NAME_LIKE = "SELECT e.id, e.name, e.email, e.nationality, e.mobile FROM Employee e WHERE lower(e.name) LIKE CONCAT('%',:pName,'%')";

	@Query(BY_NAME_LIKE_AND_NATIONALITY)
	List<Employee> getByNameAndNationality(@Param("pName") String pName,
			@Param("pNationalities") List<String> pNationalities);

	@Query(FEW_BY_NAME_LIKE_AND_NATIONALITY)
	List<Object[]> getFewByNameAndNationality(@Param("pName") String pName,
			@Param("pNationalities") List<String> pNationalities);

	@Query(FEW_BY_NAME_LIKE)
	List<Object[]> getFewByName(@Param("pName") String pName);

	Employee getByEmailAndPassword(String pUsername, String pPassword);

}
