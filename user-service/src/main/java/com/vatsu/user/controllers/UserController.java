package com.vatsu.user.controllers;


import java.util.List;

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

import com.vatsu.user.entity.User;
import com.vatsu.user.services.UserService;
import com.vatus.user.VO.ResponseTemplateVO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
		User obj = new User();
		obj.setUserEmail(user.getUserEmail());
		obj.setUserName(user.getUserName());
		obj.setDepartmentId(user.getDepartmentId());
		userService.addUser(obj);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	@ResponseBody
	public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable("userId") Long userId) throws Exception {
		ResponseTemplateVO obj = userService.getUserWithDepartment(userId);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
//	@GetMapping(value="/{userid}", headers="Accept-Version=v1")
//	public @ResponseBody ResponseEntity<Optional<User>> getUserById(@PathVariable("userid") int userid) {
//		Optional<User> user = userService.getUserById(userid);
//		return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
//	}
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	
//	@GetMapping("/paginationandsorting")
//	@ResponseBody
//	public ResponseEntity<Page<User>> getUsers(
//		@RequestParam(defaultValue="0") int page,
//		@RequestParam(defaultValue="10") int size,
//		@RequestParam(required=false) String sortBy
//		) {
//		return new ResponseEntity<>(userService.getUserByPaginationAndSorting(page, size, sortBy), HttpStatus.OK);
//	}
	
//	@GetMapping("/search/{searchTerm}")
//	@ResponseBody
//	public ResponseEntity<List<User>> searchUser(@PathVariable("searchTerm") String searchTerm) {
//		return new ResponseEntity<>(userService.searchUser(searchTerm), HttpStatus.OK);
//	}
	
	@GetMapping("/hello")
	@ResponseBody
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.ok("Hello World from User Microservice");
	}
}
