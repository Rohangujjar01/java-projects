package com.GSBScholarship_API.service;


import com.GSBScholarship_API.dto.AuthenticateDTO;
import com.GSBScholarship_API.dto.SampleResponseDto;
import com.GSBScholarship_API.dto.UserListDto;
import com.GSBScholarship_API.entity.User;
import com.GSBScholarship_API.repository.UserRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
		
		User userObj = userRepo.findByUsername(user.getUsername());
		
		System.out.println(userObj.getIsEnable());
		if(userObj==null)
		{
			
			authenticateDTO.setId(-1);
			authenticateDTO.setStatusCode(204);
			authenticateDTO.setMessage("Invalid username");
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
		}else if(userObj.getIsEnable()== false) {
			authenticateDTO.setId(-1);
			authenticateDTO.setStatusCode(204);
			authenticateDTO.setMessage("User is disabled. Please contact Admin.");
			authenticateDTO.setStatus("Failed");
			return authenticateDTO;
		}
			
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated())
		{
			String token =jwtService.generateToken(user.getUsername());
			authenticateDTO.setId(1);
			authenticateDTO.setStatusCode(200);
			authenticateDTO.setMessage("Login successfully");
			authenticateDTO.setStatus("success");
			authenticateDTO.setToken(token);
			authenticateDTO.setUsertype(userObj.getUserType());
		}
		
		return authenticateDTO;
	}
	
	public SampleResponseDto addUser(JSONObject json)
	{
		SampleResponseDto sampleResponseDto = new SampleResponseDto();
		User user1 =userRepo.findByUsername(getCurrentUser());
		User user = new User();
		
		user.setFirstName(json.get("firstName").toString());
		user.setLastName(json.get("lastName").toString());
		user.setUsername(json.get("userName").toString());
		user.setPassword(json.get("password").toString());
		user.setUserType(json.get("userType").toString());
		user.setIsEnable(Boolean.parseBoolean(json.get("status").toString()));
		user.setCreatedBy(user1.getFirstName());
		user.setCreatedDate(new Date());
		user.setUpdatedBy(user1.getFirstName());
		user.setUpdatedDate(new Date());
		User userNameCheck=userRepo.findByUsername(json.get("userName").toString());
		User saveUser = new User();
		if(userNameCheck==null) {
		 saveUser=userRepo.save(user);
		}
		else {
			sampleResponseDto.setId(-1);
			  sampleResponseDto.setStatus("Failed");
			  sampleResponseDto.setStatusCode(204);
			  sampleResponseDto.setMessage("Username already exists");
			  return sampleResponseDto;
		}
		if(saveUser!=null)
		  {
			  sampleResponseDto.setId(1);
			  sampleResponseDto.setStatus("Success");
			  sampleResponseDto.setStatusCode(200);
			  sampleResponseDto.setMessage("User data has been saved Successfully");
			  return sampleResponseDto;
		  }else
		{
			  sampleResponseDto.setId(-1);
			  sampleResponseDto.setStatus("Failed");
			  sampleResponseDto.setStatusCode(204);
			  sampleResponseDto.setMessage("There is an error. Please contact Admin.");
			  return sampleResponseDto;
		}
	}
	
	public UserListDto getUserList()
	{
		UserListDto userListDto = new UserListDto();
		List<User> userList=userRepo.findAll();
		if(userList.size()!=0)
		{
			userListDto.setId(1);
			userListDto.setStatus("Success");
			userListDto.setStatusCode(200);
			userListDto.setMessage("Records found");
			userListDto.setUserList(userList);
			 return userListDto;
		}
		else
		{
			userListDto.setId(-1);
			userListDto.setStatus("Failed");
			userListDto.setStatusCode(204);
			userListDto.setMessage("Records not found");
			  return userListDto;
		}
	}
	
	
	public SampleResponseDto editUser(JSONObject json)
	{
		SampleResponseDto sampleResponseDto = new SampleResponseDto();
		User user1 =userRepo.findByUsername(getCurrentUser());
		User user = userRepo.findById( Integer.parseInt(json.get("id").toString())).get();
		
		user.setFirstName(json.get("firstName").toString());
		user.setLastName(json.get("lastName").toString());
		user.setUsername(json.get("userName").toString());
		user.setPassword(json.get("password").toString());
		user.setUserType(json.get("userType").toString());
		user.setIsEnable(Boolean.parseBoolean(json.get("status").toString()));
		user.setUpdatedBy(user1.getFirstName());
		user.setUpdatedDate(new Date());
		User saveUser = new User();
		User userNameCheck=userRepo.getByUsername(Integer.parseInt(json.get("id").toString()),json.get("userName").toString());
		if(userNameCheck==null) {
		 saveUser=userRepo.save(user);
		}
		else {
			sampleResponseDto.setId(-1);
			  sampleResponseDto.setStatus("Failed");
			  sampleResponseDto.setStatusCode(204);
			  sampleResponseDto.setMessage("Username already exists");
			  return sampleResponseDto;
		}
		if(saveUser!=null)
		  {
			  sampleResponseDto.setId(1);
			  sampleResponseDto.setStatus("Success");
			  sampleResponseDto.setStatusCode(200);
			  sampleResponseDto.setMessage("User data has been edited Successfully");
			  return sampleResponseDto;
		  }else
		{
			  sampleResponseDto.setId(-1);
			  sampleResponseDto.setStatus("Failed");
			  sampleResponseDto.setStatusCode(204);
			  sampleResponseDto.setMessage("There is an error. Please contact Admin.");
			  return sampleResponseDto;
		}
	}
	
	
	public SampleResponseDto editStatus(JSONObject json)
	{
		SampleResponseDto sampleResponseDto = new SampleResponseDto();
		User user1 =userRepo.findByUsername(getCurrentUser());
		User user = userRepo.findById( Integer.parseInt(json.get("id").toString())).get();
		user.setIsEnable(Boolean.parseBoolean(json.get("status").toString()));
		user.setUpdatedBy(user1.getFirstName());
		user.setUpdatedDate(new Date());
		User saveUser=userRepo.save(user);
		
		if(saveUser!=null)
		  {
			  sampleResponseDto.setId(1);
			  sampleResponseDto.setStatus("Success");
			  sampleResponseDto.setStatusCode(200);
			  sampleResponseDto.setMessage("User data has been edited Successfully");
			  return sampleResponseDto;
		  }else
		{
			  sampleResponseDto.setId(-1);
			  sampleResponseDto.setStatus("Failed");
			  sampleResponseDto.setStatusCode(204);
			  sampleResponseDto.setMessage("There is an error. Please contact Admin.");
			  return sampleResponseDto;
		}
	}
	
	
	
	
	private String getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	
	
	 
}
