package com.GSBScholarship_API.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.GSBScholarship_API.dto.AuthenticateDTO;
import com.GSBScholarship_API.dto.EncryptedResponse;
import com.GSBScholarship_API.entity.User;
import com.GSBScholarship_API.service.UserService;
import com.GSBScholarship_API.util.AES;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gsb/scholarship")
public class LoginController {

	@Autowired
	private UserService userService ;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;
	
	@PostMapping("/login")
	public EncryptedResponse login(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
	{
		EncryptedResponse encResponse = new EncryptedResponse();
		AuthenticateDTO authenticateDTO = new AuthenticateDTO();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			
			User user = new User();
			user.setUsername(json.get("userName").toString());
			user.setPassword(json.get("password").toString());
			
			authenticateDTO=userService.verify(user);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			authenticateDTO.setId(-1);
			authenticateDTO.setStatus("Failed");
			authenticateDTO.setStatusCode(204);
			authenticateDTO.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(authenticateDTO);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;		
	}	
	
}	
	
	
	

