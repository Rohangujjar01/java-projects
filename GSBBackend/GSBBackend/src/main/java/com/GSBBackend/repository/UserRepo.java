package com.GSBBackend.repository;

import com.GSBBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User  findByEmail(String email);
	
	User findByPassword(String password);
}
