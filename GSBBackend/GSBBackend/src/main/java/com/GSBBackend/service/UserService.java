package com.GSBBackend.service;


import com.GSBBackend.dto.AuthenticateDTO;
import com.GSBBackend.model.User;
import com.GSBBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
//	public User addUser(User user)
//	{
//		user.setPassword(encoder.encode(user.getPassword()));
//		
//		return userRepo.save(user);
//	}

	public AuthenticateDTO verify(User user) {
		// TODO Auto-generated method stub
		
		AuthenticateDTO authenticateDTO = new AuthenticateDTO();
		
		User userObj = userRepo.findByEmail(user.getEmail());
		
		
		if(userObj==null)
		{
			
			authenticateDTO.setId(-1);
			authenticateDTO.setStatusCode(204);
			authenticateDTO.setMessage("Invalid email address");
			authenticateDTO.setStatus("Failed");
			
			return authenticateDTO;
		}
		
		else if(!userObj.getPassword().equals(user.getPassword()))       	
		{
			authenticateDTO.setId(-1);
			authenticateDTO.setStatusCode(204);
			authenticateDTO.setMessage("Password does not match");
			authenticateDTO.setStatus("Failed");
			
			return authenticateDTO;
		}
			
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if(authentication.isAuthenticated())
		{
			String token =jwtService.generateToken(user.getEmail());
			authenticateDTO.setId(1);
			authenticateDTO.setStatusCode(200);
			authenticateDTO.setMessage("Login successfully");
			authenticateDTO.setStatus("success");
			authenticateDTO.setToken(token);
		}
		
		return authenticateDTO;
	}	
}
