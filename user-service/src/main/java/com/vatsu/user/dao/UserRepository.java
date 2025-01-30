package com.vatsu.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vatsu.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE "
			+ "LOWER(u.userName) LIKE LOWER(CONCAT('%' , :searchTerm, '%')) OR "
			+ "LOWER(u.userEmail) LIKE LOWER(CONCAT('%' , :searchTerm, '%'))")
	List<User> searchUser(String searchTerm);

}
