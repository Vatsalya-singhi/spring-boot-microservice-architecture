package com.vatsu.department.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vatsu.department.entity.Department;
import com.vatsu.department.service.DepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/departments")
@Slf4j
@CrossOrigin
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	private static Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department) {
		log.info("inside saveDepartment of the Department Controller");
		Department obj = new Department();
		obj.setDepartmentName(department.getDepartmentName());
		obj.setDepartmentAddress(department.getDepartmentAddress());
		obj.setDepartmentCode(department.getDepartmentCode());
		departmentService.addDepartment(obj);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
	
	@GetMapping("/{departmentId}")
	@ResponseBody
	public ResponseEntity<Optional<Department>> findDepartmentById(@PathVariable("departmentId") Long departmentId) {
		log.info("inside findDepartmentById of the Department Controller");
		Optional<Department> department = departmentService.getDepartmentById(departmentId);
		return (department != null) ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/all")
	@ResponseBody
	@CircuitBreaker(name="departmentCircuitBreaker", fallbackMethod="retryFallback")
	public ResponseEntity<List<Department>> getAllDepartment() {
		return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchTerm}")
	@ResponseBody
	public ResponseEntity<List<Department>> searchDepartment(@PathVariable("searchTerm") String searchTerm) {
		return new ResponseEntity<>(departmentService.searchDepartment(searchTerm), HttpStatus.OK);
	}
	
	
	@GetMapping("/hello")
	@ResponseBody
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.ok("Hello World from Department Microservice");
	}
	
	
	public String retryFallback(Throwable t) {
        return "Service is temporarily unavailable. Retry fallback triggered.";
    }

    public String rateLimiterFallback(Throwable t) {
        return "Too many requests. Rate limiter fallback triggered.";
    }
    
    public String bulkheadFallback(Throwable t) {
        return "Service is overloaded. Bulkhead fallback triggered.";
    }
	
}
