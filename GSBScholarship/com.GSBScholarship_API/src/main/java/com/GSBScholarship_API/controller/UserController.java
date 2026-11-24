package com.GSBScholarship_API.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.GSBScholarship_API.dto.EncryptedResponse;
import com.GSBScholarship_API.dto.SampleResponseDto;
import com.GSBScholarship_API.dto.UserListDto;
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
public class UserController {

	@Autowired
	private AES aes ;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public EncryptedResponse addUser(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
	{
		EncryptedResponse encResponse = new EncryptedResponse();
		SampleResponseDto sampleResponseDto = new SampleResponseDto();
		
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			sampleResponseDto=userService.addUser(json);
		}
	
	catch (Exception e) {
		
		e.printStackTrace();
		sampleResponseDto.setId(-1);
		sampleResponseDto.setStatus("Failed");
		sampleResponseDto.setStatusCode(204);
		sampleResponseDto.setMessage(e.getMessage());	
	}
	
	jsonStr=Obj.writeValueAsString(sampleResponseDto);  
	outputString = aes.encrypt(jsonStr, secretKey);
//	System.out.println("outputString :"+outputString);
//	outputString = outputString.replaceAll("\\s", "");
	encResponse.setEncryptedResponse(outputString);
	return encResponse;	
	}


@PostMapping("/getUserList")
public EncryptedResponse getUserList() throws JsonProcessingException
{
	EncryptedResponse encResponse = new EncryptedResponse();
	UserListDto userListDto = new UserListDto();
	
	ObjectMapper Obj = new ObjectMapper();  
	String jsonStr = null;
	String outputString=null;
	try {
		
		userListDto=userService.getUserList();
	}

catch (Exception e) {
	
	e.printStackTrace();
	userListDto.setId(-1);
	userListDto.setStatus("Failed");
	userListDto.setStatusCode(204);
	userListDto.setMessage(e.getMessage());	
}

jsonStr=Obj.writeValueAsString(userListDto);  
outputString = aes.encrypt(jsonStr, secretKey);
//System.out.println("outputString :"+outputString);
//outputString = outputString.replaceAll("\\s", "");
encResponse.setEncryptedResponse(outputString);
return encResponse;	
}

@PostMapping("/editUser")
public EncryptedResponse editUser(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
{
	EncryptedResponse encResponse = new EncryptedResponse();
	SampleResponseDto sampleResponseDto = new SampleResponseDto();
	
	ObjectMapper Obj = new ObjectMapper();  
	String jsonStr = null;
	String outputString=null;
	try {
		String inputString = aes.decrypt(EncryptedRequest, secretKey);
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(inputString);
		sampleResponseDto=userService.editUser(json);
	}

catch (Exception e) {
	
	e.printStackTrace();
	sampleResponseDto.setId(-1);
	sampleResponseDto.setStatus("Failed");
	sampleResponseDto.setStatusCode(204);
	sampleResponseDto.setMessage(e.getMessage());	
}

jsonStr=Obj.writeValueAsString(sampleResponseDto);  
outputString = aes.encrypt(jsonStr, secretKey);
//System.out.println("outputString :"+outputString);
//outputString = outputString.replaceAll("\\s", "");
encResponse.setEncryptedResponse(outputString);
return encResponse;	
}

@PostMapping("/editStatus")
public EncryptedResponse editStatus(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
{
	EncryptedResponse encResponse = new EncryptedResponse();
	SampleResponseDto sampleResponseDto = new SampleResponseDto();
	
	ObjectMapper Obj = new ObjectMapper();  
	String jsonStr = null;
	String outputString=null;
	try {
		String inputString = aes.decrypt(EncryptedRequest, secretKey);
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(inputString);
		sampleResponseDto=userService.editStatus(json);
	}

catch (Exception e) {
	
	e.printStackTrace();
	sampleResponseDto.setId(-1);
	sampleResponseDto.setStatus("Failed");
	sampleResponseDto.setStatusCode(204);
	sampleResponseDto.setMessage(e.getMessage());	
}

jsonStr=Obj.writeValueAsString(sampleResponseDto);  
outputString = aes.encrypt(jsonStr, secretKey);
//System.out.println("outputString :"+outputString);
//outputString = outputString.replaceAll("\\s", "");
encResponse.setEncryptedResponse(outputString);
return encResponse;	
}
}










