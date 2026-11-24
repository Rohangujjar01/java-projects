package com.GSBScholarship_API.service;

import com.GSBScholarship_API.dto.SampleResponseDto;
import com.GSBScholarship_API.entity.OnlineNewStudent;
import com.GSBScholarship_API.repository.OnlineNewStudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ExcelService {

    @Autowired
    private OnlineNewStudentRepository newStudentRepository;

    public SampleResponseDto importExcelData(MultipartFile file) throws IOException {
    	 SampleResponseDto sampleResponseDto = new SampleResponseDto();
    	try (InputStream inputStream = file.getInputStream();
                Workbook workbook = WorkbookFactory.create(inputStream)) {

               Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

               for (Row row : sheet) {
                   // Skip the header row
            	   if (isRowEmpty(row)||row.getRowNum() == 0) {
                       continue;
                   }
                   
                   OnlineNewStudent newStudent = new OnlineNewStudent(); 
                   
                   newStudent.setFirstName(getCellValueAsString(row.getCell(1)));
                   newStudent.setLastName(getCellValueAsString(row.getCell(2)));
                   newStudent.setMobileNo(getCellValueAsString(row.getCell(3)));
                   newStudent.setAadhar(getCellValueAsString(row.getCell(4)));
                   newStudent.setYear(getCurrentYear());
                   OnlineNewStudent data   =newStudentRepository.getAadharwithScholershipYear(String.valueOf(BigDecimal.valueOf(row.getCell(4).getNumericCellValue())),getCurrentYear());
                   if(data==null)
                   {
                   newStudentRepository.save(newStudent);
                   }
               }
           }  
    	sampleResponseDto.setId(1);
		sampleResponseDto.setMessage("Data imported successfully!");
		sampleResponseDto.setStatus("Success");
		sampleResponseDto.setStatusCode(200);
		return sampleResponseDto;
    	
    	
        }

     
        
    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // Row has at least one non-blank cell
            }
        }
        return true;
    }
    
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Format dates if needed
                } else {
                    return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return String.valueOf(cell.getNumericCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    public String getCurrentYear() {
		Integer startYear = null;
		Integer endYear = null;
		LocalDate currentdate = LocalDate.now();
		Integer month = currentdate.getMonthValue();
		if (month <= 3) {
			startYear = currentdate.getYear() - 1;
			endYear = currentdate.getYear();
		} else {
			startYear = currentdate.getYear();
			endYear = currentdate.getYear() + 1;
		}

		String lyear = startYear.toString() + "-" + endYear.toString().substring(2);
		System.out.println("lyear  ==============================  " + lyear);

		return lyear;
	}

	
}

