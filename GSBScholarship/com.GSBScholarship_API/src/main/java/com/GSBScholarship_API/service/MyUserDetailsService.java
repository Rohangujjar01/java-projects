package com.GSBScholarship_API.service;

import com.GSBScholarship_API.entity.User;
import com.GSBScholarship_API.repository.UserRepo;
import com.GSBScholarship_API.util.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {
	
@Autowired
private UserRepo repo;	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=repo.findByUsername(username);
		if(user==null)
		{
			System.out.println("user not found");
			
			throw new UsernameNotFoundException("user not found"); 
		}
		
		return new UserPrinciple(user);
	}

}
