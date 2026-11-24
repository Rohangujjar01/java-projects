package com.GSBBackend.service;

import com.GSBBackend.model.User;
import com.GSBBackend.model.UserPrinciple;
import com.GSBBackend.repository.UserRepo;
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
		User user=repo.findByEmail(username);
		if(user==null)
		{
			System.out.println("user not found");
			
			throw new UsernameNotFoundException("user not found"); 
		}
		
		return new UserPrinciple(user);
	}

}
