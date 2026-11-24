package com.GSBScholarship_API.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.GSBScholarship_API.dto.EncryptedResponse;
import com.GSBScholarship_API.dto.SampleResponseDto;
import com.GSBScholarship_API.service.StudentLoginService;
import com.GSBScholarship_API.util.AES;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gsb/scholarship")
public class StudentLoginController {
	
	@Autowired
	private StudentLoginService studentLoginService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;
	
	
	@PostMapping("/sendOTP")
	public EncryptedResponse sendOTP(@RequestParam String encryptedRequest) throws JsonProcessingException {
	    EncryptedResponse encResponse = new EncryptedResponse();
	    SampleResponseDto sampleResponseDto = new SampleResponseDto(); 
	    ObjectMapper objectMapper = new ObjectMapper();
	    String outputString;

	    try {
	        // Decrypt the incoming request
	        String inputString = aes.decrypt(encryptedRequest, secretKey);
//	        logger.info("Decrypted Request: {}", inputString);

	        // Parse the JSON input
	        JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
		String number=json.get("number").toString();
		String aadhar=json.get("aadhar").toString();	        // Fetch class details
		sampleResponseDto = studentLoginService.sendOTP(number, aadhar);
	    } catch (Exception e) {
//	        logger.error("Error processing request", e);
	    	e.printStackTrace();
	    	sampleResponseDto.setId(-1);
	    	sampleResponseDto.setStatus("Failed");
	    	sampleResponseDto.setStatusCode(500); // Internal Server Error
	    	sampleResponseDto.setMessage(e.getMessage());
	    }

	    // Convert response to JSON and encrypt
	    String jsonResponse = objectMapper.writeValueAsString(sampleResponseDto);
	    outputString = aes.encrypt(jsonResponse, secretKey);

	    // Set encrypted response
	    encResponse.setEncryptedResponse(outputString);
	    return encResponse;
	}	
	
	@PostMapping("/verifyOTP")
	public EncryptedResponse verifyOTP(@RequestParam String encryptedRequest) throws JsonProcessingException {
	    EncryptedResponse encResponse = new EncryptedResponse();
	    SampleResponseDto sampleResponseDto = new SampleResponseDto(); 
	    ObjectMapper objectMapper = new ObjectMapper();
	    String outputString;

	    try {
	        // Decrypt the incoming request
	        String inputString = aes.decrypt(encryptedRequest, secretKey);
//	        logger.info("Decrypted Request: {}", inputString);

	        // Parse the JSON input
	        JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
		Integer otp= Integer.parseInt(json.get("otp").toString());
		String number=json.get("number").toString();
		String aadhar=json.get("aadhar").toString();	        // Fetch class details
		sampleResponseDto = studentLoginService.otpVerify(otp,number, aadhar);
	    } catch (Exception e) {
//	        logger.error("Error processing request", e);
	    	e.printStackTrace();
	    	sampleResponseDto.setId(-1);
	    	sampleResponseDto.setStatus("Failed");
	    	sampleResponseDto.setStatusCode(500); // Internal Server Error
	    	sampleResponseDto.setMessage(e.getMessage());
	    }

	    // Convert response to JSON and encrypt
	    String jsonResponse = objectMapper.writeValueAsString(sampleResponseDto);
	    outputString = aes.encrypt(jsonResponse, secretKey);

	    // Set encrypted response
	    encResponse.setEncryptedResponse(outputString);
	    return encResponse;
	}	
	
}
