package com.GSBBackend.controller;

import com.GSBBackend.dto.AuthenticateDTO;
import com.GSBBackend.dto.EncryptedResponse;
import com.GSBBackend.model.User;
import com.GSBBackend.service.UserService;
import com.GSBBackend.util.AES;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/doctor_profile")
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
			user.setEmail(json.get("userName").toString());
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
