package com.GSBBackend.controller;

import com.GSBBackend.dto.*;
import com.GSBBackend.service.ProfileService;
import com.GSBBackend.service.StatusService;
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
public class ProfileController {

	@Autowired
	private StatusService statusService ;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;
	
	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/specializationList")
    public EncryptedResponse specializationList() throws JsonProcessingException {
		
		SpecializationListDTO response = new SpecializationListDTO();
		EncryptedResponse encResponse=new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			response=profileService.getSpecializationList();
		   }
		catch(Exception e)
		{
			e.printStackTrace();
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage(e.getMessage());	
		}
		jsonStr=Obj.writeValueAsString(response);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
	}
	
	@PostMapping("/specializationListForAdd")
    public EncryptedResponse specializationListForAdd() throws JsonProcessingException {
		
		SpecializationListDTO response = new SpecializationListDTO();
		EncryptedResponse encResponse=new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			response=profileService.getSpecializationList();
		   }
		catch(Exception e)
		{
			e.printStackTrace();
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage(e.getMessage());	
		}
		jsonStr=Obj.writeValueAsString(response);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
	}

	@PostMapping("/statusList")
    public EncryptedResponse statusList() throws JsonProcessingException {
		
		StatutsDto response = new StatutsDto();
		EncryptedResponse encResponse=new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			response=statusService.getStatusList();
		   }
		catch(Exception e)
		{
			e.printStackTrace();
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage(e.getMessage());	
		}
		jsonStr=Obj.writeValueAsString(response);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
	}
	
	
	@PostMapping("/Review")
	public EncryptedResponse login(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
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
			sampleResponseDto=profileService.review(json);
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
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
		
	}	
	
	
	@PostMapping("/addNew")
	public EncryptedResponse addNew(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
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
			sampleResponseDto=profileService.addProfile(json);
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
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
		
	}
	
	@PostMapping("/getReviewHistory")
	public EncryptedResponse getReviewHistory(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
	{
		EncryptedResponse encResponse = new EncryptedResponse();
		ReviewHistoryListDto reviewHistoryListDto = new ReviewHistoryListDto();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			
		Integer profileId = Integer.parseInt(json.get("ProfileId").toString());
		reviewHistoryListDto=profileService.getReviewHistoryList(profileId);
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
			reviewHistoryListDto.setId(-1);
			reviewHistoryListDto.setStatus("Failed");
			reviewHistoryListDto.setStatusCode(204);
			reviewHistoryListDto.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(reviewHistoryListDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
		
	}
	
	@PostMapping("/listNew")
    public EncryptedResponse AllProfiles(@RequestParam String EncryptedRequest) throws JsonProcessingException, org.json.simple.parser.ParseException{
		
		
		ProfileListDto profileListDto = new ProfileListDto();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			int page =!json.get("page").toString().equals("np")?Integer.parseInt(json.get("page").toString()):0;
			int size =!json.get("size").toString().equals("np")?Integer.parseInt(json.get("size").toString()):25;
			String specialization =!json.get("specialization").toString().equals("np")?json.get("specialization").toString():"All";
			String status =!json.get("status").toString().equals("np")?json.get("status").toString():"All";
	
//			System.out.println( "page=="+page+" size===" +size+"  specialization==="+ specialization+" status==="+ status );
			
		    profileListDto=profileService.getList(specialization, status, page, size);
		}
        catch (Exception e) {
        	e.printStackTrace();
        	profileListDto.setId(-1);
        	profileListDto.setStatus("Failed");
        	profileListDto.setStatusCode(204);
        	profileListDto.setMessage(e.getMessage());
		}
       
		jsonStr=Obj.writeValueAsString(profileListDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;
    }
	
	@PostMapping("/statusListForUpdate")
    public EncryptedResponse statusListForUpdate() throws JsonProcessingException {
		
		StatutsDto response = new StatutsDto();
		EncryptedResponse encResponse=new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			response=statusService.statusListForUpdate();
		   }
		catch(Exception e)
		{
			e.printStackTrace();
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage(e.getMessage());	
		}
		jsonStr=Obj.writeValueAsString(response);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
	}
    }
		

