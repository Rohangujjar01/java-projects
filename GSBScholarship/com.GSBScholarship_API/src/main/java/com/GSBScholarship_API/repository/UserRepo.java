package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User  findByUsername(String username);
	
	User findByPassword(String password);
	
	@Query(value = "select * from online_user_master where username= :username and user_id != :id",nativeQuery = true)
	User getByUsername(@Param("id") Integer id ,@Param("username") String username);
	
}
