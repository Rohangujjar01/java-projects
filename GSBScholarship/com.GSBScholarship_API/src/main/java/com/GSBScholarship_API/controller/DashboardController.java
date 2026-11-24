package com.GSBScholarship_API.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.GSBScholarship_API.dto.*;
import com.GSBScholarship_API.service.DashBoardService;
import com.GSBScholarship_API.util.AES;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gsb/scholarship")
public class DashboardController {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;


	@PostMapping("/monthlyCount")
    public EncryptedResponse getMonthlyCount(
            @RequestParam String EncryptedRequest) throws JsonProcessingException {
		
		EncryptedResponse encResponse = new EncryptedResponse();
		MonthlyCountResponse monthlyCountResponse = new MonthlyCountResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			String scholarshipYear=json.get("scholarshipYear").toString();
			
			monthlyCountResponse = dashBoardService.getMonthlyStudentCount(scholarshipYear);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			monthlyCountResponse.setId(-1);
			monthlyCountResponse.setStatus("Failed");
			monthlyCountResponse.setStatusCode(204);
			monthlyCountResponse.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(monthlyCountResponse);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;		
	}
	
	@PostMapping("/genderCourseCount")
    public EncryptedResponse getGenderCourseCount(
    		@RequestParam String EncryptedRequest) throws JsonProcessingException {
		GenderCourseCountResponse genderCourseCountResponse = new GenderCourseCountResponse();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			String scholarshipYear=json.get("scholarshipYear").toString();
			genderCourseCountResponse = dashBoardService.getGenderCourseCount(scholarshipYear);
        
		}
		catch (Exception e) {
			
			e.printStackTrace();
			genderCourseCountResponse.setId(-1);
			genderCourseCountResponse.setStatus("Failed");
			genderCourseCountResponse.setStatusCode(204);
			genderCourseCountResponse.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(genderCourseCountResponse);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;		
	}
	
	@PostMapping("/coursePercentage")
	    public EncryptedResponse getCourseRecordPercentage(@RequestParam String EncryptedRequest) throws JsonProcessingException {
		
		CourseRecordPercentageResponse courseRecordPercentageResponse = new CourseRecordPercentageResponse();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			String scholarshipYear=json.get("scholarshipYear").toString();
			courseRecordPercentageResponse = dashBoardService.getCourseRecordPercentage(scholarshipYear);
	        
		}
		catch (Exception e) {
			
			e.printStackTrace();
			courseRecordPercentageResponse.setId(-1);
			courseRecordPercentageResponse.setStatus("Failed");
			courseRecordPercentageResponse.setStatusCode(204);
			courseRecordPercentageResponse.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(courseRecordPercentageResponse);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;		
	}
	 
	@PostMapping("/genderPercentage")
	    public EncryptedResponse getGenderRecordPercentage(@RequestParam String EncryptedRequest) throws JsonProcessingException {
		
		GenderRecordPercentageResponse genderRecordPercentageResponse = new GenderRecordPercentageResponse();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			String scholarshipYear=json.get("scholarshipYear").toString();
			genderRecordPercentageResponse = dashBoardService.getGenderRecordPercentage(scholarshipYear);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			genderRecordPercentageResponse.setId(-1);
			genderRecordPercentageResponse.setStatus("Failed");
			genderRecordPercentageResponse.setStatusCode(204);
			genderRecordPercentageResponse.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(genderRecordPercentageResponse);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;		
	}
	 
	@PostMapping("/courseStatusCounts")
	    public EncryptedResponse getCourseStatusCounts(@RequestParam String EncryptedRequest) throws JsonProcessingException {
		
		CourseStatusCountsResponse courseStatusCountsResponse = new CourseStatusCountsResponse();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			String scholarshipYear=json.get("scholarshipYear").toString();
			courseStatusCountsResponse = dashBoardService.getCourseStatusCounts(scholarshipYear);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			courseStatusCountsResponse.setId(-1);
			courseStatusCountsResponse.setStatus("Failed");
			courseStatusCountsResponse.setStatusCode(204);
			courseStatusCountsResponse.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(courseStatusCountsResponse);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;		
	}
	
	@PostMapping("/applicationStatusCounts")
    public EncryptedResponse getApplicationStatusCounts(
    		@RequestParam String EncryptedRequest) throws JsonProcessingException {
		ApplicationStatusCountResponse applicationStatusCountResponse = new ApplicationStatusCountResponse();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			String scholarshipYear=json.get("scholarshipYear").toString();
			applicationStatusCountResponse = dashBoardService.getApplicationStatusCounts(scholarshipYear);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			applicationStatusCountResponse.setId(-1);
			applicationStatusCountResponse.setStatus("Failed");
			applicationStatusCountResponse.setStatusCode(204);
			applicationStatusCountResponse.setMessage(e.getMessage());	
		}
		
		jsonStr=Obj.writeValueAsString(applicationStatusCountResponse);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	
    }	
	
	 @PostMapping("/applicationCount")
	    public EncryptedResponse getStudentCount(@RequestParam String EncryptedRequest) throws JsonProcessingException {
		 EncryptedResponse encResponse = new EncryptedResponse();
		 TotalRecordYearDto totalRecordYearDto = new TotalRecordYearDto();
			ObjectMapper Obj = new ObjectMapper();  
			String jsonStr = null;
			String outputString=null;
			try {
				String inputString = aes.decrypt(EncryptedRequest, secretKey);
				JSONParser parser = new JSONParser();  
				JSONObject json = (JSONObject) parser.parse(inputString);
				String scholarshipYear=json.get("scholarshipYear").toString();
	        long studentCount = dashBoardService.getStudentCountByScholarshipYear(scholarshipYear);
	        
	        totalRecordYearDto.setId(1);  
	        totalRecordYearDto.setStatus("Success");
	        totalRecordYearDto.setStatusCode(200);
	        totalRecordYearDto.setMessage("Record found");
	        totalRecordYearDto.setTotalApplications(studentCount);
	       
	    }
			catch (Exception e) {
				
				e.printStackTrace();
				totalRecordYearDto.setId(-1);
				totalRecordYearDto.setStatus("Failed");
				totalRecordYearDto.setStatusCode(204);
				totalRecordYearDto.setMessage(e.getMessage());	
			}
			
			jsonStr=Obj.writeValueAsString(totalRecordYearDto);  
			outputString = aes.encrypt(jsonStr, secretKey);
//			System.out.println("outputString :"+outputString);
//			outputString = outputString.replaceAll("\\s", "");
			encResponse.setEncryptedResponse(outputString);
			return encResponse;	
	
	 }
}
