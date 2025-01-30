package com.vatsu.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vatsu.department.entity.Department;
import com.vatsu.department.repository.DepartmentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepo;

	public void addDepartment(Department department) {
		departmentRepo.save(department);
	}
	
	public Optional<Department> getDepartmentById(Long departmentid) {
		return departmentRepo.findById(departmentid);
	}
	
	public List<Department> getAllDepartment() {
		return departmentRepo.findAll();
	}
	
	public List<Department> searchDepartment(String searchTerm) {
		return departmentRepo.searchDepartment(searchTerm);
	}
    
}
