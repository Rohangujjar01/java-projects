package com.GSBScholarship_API.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.GSBScholarship_API.dto.*;
import com.GSBScholarship_API.service.CourseService;
import com.GSBScholarship_API.service.ExcelService;
import com.GSBScholarship_API.service.StudentService;
import com.GSBScholarship_API.util.AES;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gsb/scholarship")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;
	
	@Autowired
	private ExcelService  excelService;
	
	
	
	@PostMapping("/search")
    public EncryptedResponse search(
            @RequestParam(required = false) String scholarshipYear,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String sclass,
            @RequestParam(required = false) String status) throws JsonProcessingException {
		EncryptedResponse encResponse = new EncryptedResponse();
		StudentApplicantListResponseDto applicantListResponseDto = new StudentApplicantListResponseDto();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			
			String scholarshipYear1 = aes.decrypt(scholarshipYear, secretKey);
			String course1 =  aes.decrypt(course, secretKey);
			String sclass1 =  aes.decrypt(sclass, secretKey);
			String status1= aes.decrypt(status, secretKey);	
			
		 applicantListResponseDto=studentService.dynamicSearch(scholarshipYear1, course1, sclass1, status1);
		    }
        catch (Exception e) {
        	e.printStackTrace();
        	applicantListResponseDto.setId(-1);
        	applicantListResponseDto.setStatus("Failed");
        	applicantListResponseDto.setStatusCode(204);
        	applicantListResponseDto.setMessage(e.getMessage());
        	  }
		jsonStr=Obj.writeValueAsString(applicantListResponseDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	     
    }
	
	@PostMapping("/getCategoryList")
    public EncryptedResponse getCategoryList() throws JsonProcessingException {
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		CourseResponseDto courseResponseDto = new CourseResponseDto();
		try {
			
			courseResponseDto= courseService.getCategories();
		    }
        catch (Exception e) {
        	e.printStackTrace();
        	courseResponseDto.setId(-1);
        	courseResponseDto.setStatus("Failed");
        	courseResponseDto.setStatusCode(204);
        	courseResponseDto.setMessage(e.getMessage());
        	  }
		jsonStr=Obj.writeValueAsString(courseResponseDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	     
    }
	
	@PostMapping("/getClassByCourse")
	public EncryptedResponse getClassByCourse(@RequestParam String encryptedRequest) throws JsonProcessingException {
	    EncryptedResponse encResponse = new EncryptedResponse();
	    ClassResponseDto classResponseDto = new ClassResponseDto();
	    ObjectMapper objectMapper = new ObjectMapper();
	    String outputString;

	    try {
	        // Decrypt the incoming request
	        String inputString = aes.decrypt(encryptedRequest, secretKey);
//	        logger.info("Decrypted Request: {}", inputString);

	        // Parse the JSON input
	        JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
		String course=json.get("course").toString();

	        // Fetch class details
	        classResponseDto = courseService.getSubCategories(course);
	    } catch (Exception e) {
//	        logger.error("Error processing request", e);
	    	e.printStackTrace();
	        classResponseDto.setId(-1);
	        classResponseDto.setStatus("Failed");
	        classResponseDto.setStatusCode(500); // Internal Server Error
	        classResponseDto.setMessage(e.getMessage());
	    }

	    // Convert response to JSON and encrypt
	    String jsonResponse = objectMapper.writeValueAsString(classResponseDto);
	    outputString = aes.encrypt(jsonResponse, secretKey);

	    // Set encrypted response
	    encResponse.setEncryptedResponse(outputString);
	    return encResponse;
	}
	
	@PostMapping("/getYearList")
    public EncryptedResponse getYearList() throws JsonProcessingException {
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		YearListResponseDto yearListResponseDto  = new YearListResponseDto();
		try {
			
			yearListResponseDto= studentService.getYearList();
		    }
        catch (Exception e) {
        	yearListResponseDto.setId(-1);
        	yearListResponseDto.setStatus("Failed");
        	yearListResponseDto.setStatusCode(204);
        	yearListResponseDto.setMessage(e.getMessage());
        	  }
		jsonStr=Obj.writeValueAsString(yearListResponseDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;     
    }	
	
	@PostMapping("/getStatusList")
    public EncryptedResponse getStatusList() throws JsonProcessingException {
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		StatusListResponseDto statusListResponseDto  = new StatusListResponseDto();
		try {
			
			statusListResponseDto= studentService.getStatusList();
		    }
        catch (Exception e) {
        	statusListResponseDto.setId(-1);
        	statusListResponseDto.setStatus("Failed");
        	statusListResponseDto.setStatusCode(204);
        	statusListResponseDto.setMessage(e.getMessage());
        	  }
		jsonStr=Obj.writeValueAsString(statusListResponseDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;     
    }
	
	
	@RequestMapping(value = "/saveForm", method = RequestMethod.POST)
	public EncryptedResponse save(@RequestParam("fee") MultipartFile fee, @RequestParam("certificate") MultipartFile certificate,
			@RequestParam("loginId") String mobileno, @RequestParam("marksheet") MultipartFile marksheet,
			@RequestParam String encryptedRequest  , @RequestParam("aadharcardStud") MultipartFile aadharcard,
			// @RequestParam("cancelledCheque") MultipartFile cancelledCheque,
			HttpServletRequest servletRequest) throws JsonProcessingException{
		SampleResponseDto sampleResponseDto = new SampleResponseDto();
		EncryptedResponse encResponse = new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
		 String inputString = aes.decrypt(encryptedRequest, secretKey);
		 ObjectMapper objectMapper = new ObjectMapper();
		 
            // Convert JSON string to Java object
		 StudentFormDetailsDTO studentFormDetailsDTO = objectMapper.readValue(inputString,StudentFormDetailsDTO.class);
		 
		 sampleResponseDto =studentService.save(fee, certificate, mobileno, marksheet, studentFormDetailsDTO, aadharcard, servletRequest);
		}
		 catch (Exception e) {
			 sampleResponseDto.setId(-1);
			 sampleResponseDto.setStatus("Failed");
			 sampleResponseDto.setStatusCode(204);
			 sampleResponseDto.setMessage(e.getMessage());
	        	  }
			jsonStr=Obj.writeValueAsString(sampleResponseDto);  
			outputString = aes.encrypt(jsonStr, secretKey);
//			System.out.println("outputString :"+outputString);
//			outputString = outputString.replaceAll("\\s", "");
			encResponse.setEncryptedResponse(outputString);
			return encResponse; 	
	}	
	
	@PostMapping("/getStudentDetailsById")
	public EncryptedResponse getStudentDetailsById(@RequestParam String encryptedRequest) throws JsonProcessingException {
	    EncryptedResponse encResponse = new EncryptedResponse();
	    StudentFormDetailsResponseDto studentFormDetailsResponseDto = new StudentFormDetailsResponseDto();
	    ObjectMapper objectMapper = new ObjectMapper();
	    String outputString;

	    try {
	        // Decrypt the incoming request
	        String inputString = aes.decrypt(encryptedRequest, secretKey);
//	        logger.info("Decrypted Request: {}", inputString);

	        // Parse the JSON input
	        JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
		    Integer id=Integer.parseInt(json.get("id").toString());

	        // Fetch class details
		    studentFormDetailsResponseDto = studentService.getStudentDetailsById(id);
	    } catch (Exception e) {
//	        logger.error("Error processing request", e);
	    	e.printStackTrace();
	    	studentFormDetailsResponseDto.setId(-1);
	    	studentFormDetailsResponseDto.setStatus("Failed");
	    	studentFormDetailsResponseDto.setStatusCode(500); // Internal Server Error
	    	studentFormDetailsResponseDto.setMessage(e.getMessage());
	    }

	    // Convert response to JSON and encrypt
	    String jsonResponse = objectMapper.writeValueAsString(studentFormDetailsResponseDto);
	    outputString = aes.encrypt(jsonResponse, secretKey);

	    // Set encrypted response
	    encResponse.setEncryptedResponse(outputString);
	    return encResponse;
	}	
	
	@PostMapping("/uploadExcelFile")
    public EncryptedResponse uploadExcelFile(@RequestParam("file") MultipartFile file) throws JsonProcessingException {
		EncryptedResponse encResponse = new EncryptedResponse();
		SampleResponseDto sampleResponseDto = new SampleResponseDto();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
        try {
        	sampleResponseDto= excelService.importExcelData(file);
            
        } catch (IOException e) {
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
	
	@PostMapping("/getRegistrationList")
    public EncryptedResponse search(
             @RequestParam String EncryptedRequest) throws JsonProcessingException {
		EncryptedResponse encResponse = new EncryptedResponse();
		RegistrationListDto registrationListDto = new RegistrationListDto();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
			String inputString = aes.decrypt(EncryptedRequest, secretKey);
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(inputString);
			int page =!json.get("page").toString().equals("")?Integer.parseInt(json.get("page").toString()):0;
			int size =!json.get("size").toString().equals("")?Integer.parseInt(json.get("size").toString()):25;
			String scholarshipYear =!json.get("scholarshipYear").toString().equals("")?json.get("scholarshipYear").toString():studentService.getCurrentYear();
		    registrationListDto=studentService.getRegistrationList(scholarshipYear, page, size);
		    }
        catch (Exception e) {
        	e.printStackTrace();
        	registrationListDto.setId(-1);
        	registrationListDto.setStatus("Failed");
        	registrationListDto.setStatusCode(204);
        	registrationListDto.setMessage(e.getMessage());
        	  }
		jsonStr=Obj.writeValueAsString(registrationListDto);  
		outputString = aes.encrypt(jsonStr, secretKey);
//		System.out.println("outputString :"+outputString);
//		outputString = outputString.replaceAll("\\s", "");
		encResponse.setEncryptedResponse(outputString);
		return encResponse;	     
    }
	
	@PostMapping("/Review")
	public EncryptedResponse review(@RequestParam String EncryptedRequest,HttpServletRequest servletRequest) throws JsonProcessingException
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
			sampleResponseDto=studentService.review(json);
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
			
		Integer studentId = Integer.parseInt(json.get("studentId").toString());
		reviewHistoryListDto=studentService.getReviewHistoryList(studentId);
		
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
}
	
	
	
	
	
	
	
	
	

