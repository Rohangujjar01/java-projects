package com.GSBBackend.controller;


import com.GSBBackend.service.PdfService;
import com.GSBBackend.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/doctor_profile")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Value("${jwt.secret}")
	private String secretKey;
	
	@Autowired
	private AES aes ;
    
    @PostMapping("/downloadpdf")
    public ResponseEntity<byte[]> downloadDynamicPdf(@RequestParam String EncryptedRequest) {
        try {
        	byte[] pdfBytes = {};
        	
        	String inputString = aes.decrypt(EncryptedRequest, secretKey);
    		List<String> fields = new ArrayList<>();
    		for (int i =0;i<inputString.split(",").length;i++) {
    			fields.add(inputString.split(",")[i]);
    		}
            pdfBytes = pdfService.generateDynamicPdf(fields);
        	String pattern = "yyyyMMdd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            // Format current date and time
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(formatter);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +"DoctorData_"+formattedDate+ ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage().getBytes());
        }
    }

	
}
