package com.vatsu.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vatsu.user.dao.UserRepository;
import com.vatsu.user.entity.User;
import com.vatus.user.VO.Department;
import com.vatus.user.VO.ResponseTemplateVO;


@Service
public class UserService {

	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void addUser(User user) {
		userRepo.save(user);
	}

	public Optional<User> getUserById(Long userid) {
		return userRepo.findById(userid);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public List<User> searchUser(String searchTerm) {
		return userRepo.searchUser(searchTerm);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) throws Exception {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepo.findById(userId).orElseThrow(()-> new Exception("User Not found"));
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/api/v1/departments/"+ user.getDepartmentId(), Department.class);
		
		vo.setUser(user);
		vo.setDepartment(department);
		
		return vo;
	}
	
	
	//	public Page<User> getUserByPaginationAndSorting(int page, int size, String sortBy) {
	//	Pageable pageable;
	//	if(sortBy == null )  {
	//		pageable = PageRequest.of(page, size);
	//	}else {
	//		pageable = PageRequest.of(page, size, Sort.by(sortBy));
	//	}
	//	return userRepo.findAll(pageable);
	//}

}
