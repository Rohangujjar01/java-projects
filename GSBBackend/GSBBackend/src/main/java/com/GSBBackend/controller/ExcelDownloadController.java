package com.GSBBackend.controller;

import com.GSBBackend.dto.EncryptedResponse;
import com.GSBBackend.dto.FieldsAndViewValueDto;
import com.GSBBackend.dto.FieldsAndViewValueResponse;
import com.GSBBackend.service.ExcelDownloadService;
import com.GSBBackend.util.AES;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/doctor_profile")
public class ExcelDownloadController {

	@Autowired
	private ExcelDownloadService downloadService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;

	@PostMapping("/downloadExcel")
	public ResponseEntity<ByteArrayResource> downloadExcel(@RequestParam String EncryptedRequest) {
		
		String inputString = aes.decrypt(EncryptedRequest, secretKey);
		List<String> fields = new ArrayList<>();
		for (int i =0;i<inputString.split(",").length;i++) {
			fields.add(inputString.split(",")[i]);
		}
		
		
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Data");

			sheet=downloadService.downloadSpecificExcelData(sheet, fields);
	
			// Write the workbook to a byte array
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			byte[] fileContent = outputStream.toByteArray();

			// Return as downloadable file
			ByteArrayResource resource = new ByteArrayResource(fileContent);
			String pattern = "yyyyMMdd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            // Format current date and time
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(formatter);
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=DoctorData_"+formattedDate+".xlsx");

			return ResponseEntity.ok()
					.headers(headers)
					.contentLength(fileContent.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		} catch (IOException e) {
			throw new RuntimeException("Failed to create Excel file", e);
		}
	}
		
	
	@PostMapping("/getFieldsAndViewValue")
	public EncryptedResponse getFieldsAndViewValue() throws JsonProcessingException
	{
		FieldsAndViewValueResponse response =new  FieldsAndViewValueResponse();
		EncryptedResponse encResponse=new EncryptedResponse();
		ObjectMapper Obj = new ObjectMapper();  
		String jsonStr = null;
		String outputString=null;
		try {
		List<FieldsAndViewValueDto> dtoList= downloadService.fieldsAndViewValue();
		if(dtoList.size()!=0)
		{
			response.setId(1);
			response.setStatus("Success");
			response.setStatusCode(200);
			response.setMessage("Record(s) found");
			response.setFieldsAndViewValueList(dtoList);	
		}
		else
		{
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage("Record not found");
		}
		}
  catch (Exception e) {
			
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
