package com.vatsu.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vatsu.department.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("SELECT d FROM Department d WHERE "
			+ "LOWER(d.departmentName) LIKE LOWER(CONCAT('%' , :searchTerm, '%')) OR "
			+ "LOWER(d.departmentAddress) LIKE LOWER(CONCAT('%' , :searchTerm, '%')) OR "
			+ "LOWER(d.departmentCode) LIKE LOWER(CONCAT('%' , :searchTerm, '%'))")
	List<Department> searchDepartment(String searchTerm);
	
}
