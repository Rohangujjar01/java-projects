package com.GSBBackend.service;

import com.GSBBackend.dto.FieldsAndViewValueDto;
import com.GSBBackend.model.Profile;
import com.GSBBackend.repository.ProfileRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelDownloadService {

@Autowired
private ProfileRepository profileRepository ;

@PersistenceContext
private EntityManager entityManager;

	
public List<Object[]> getSpecificData(String fields) {
    
        String sql = "SELECT "+fields +" FROM profile";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

  public List<String> getSingleData(String fields) {
    
    String sql = "SELECT " + fields + " FROM profile";
    Query query = entityManager.createNativeQuery(sql);
    return (List<String>) query.getResultList();
   }
  public List<Profile> getAllData() {
    return profileRepository.findAll();
    }

  public Sheet  downloadSpecificExcelData(Sheet sheet,List<String> fields)
  {
 
  List<String> rowHeaders= new ArrayList<>();
	for(String field : fields )
	{
		if(field.equals("available"))
		{
			rowHeaders.add("sunday");
			rowHeaders.add("monday");
			rowHeaders.add("tuesday");
			rowHeaders.add("wednesday");
			rowHeaders.add("thursday");
			rowHeaders.add("Friday");
			rowHeaders.add("saturday");
		}
		else {
			rowHeaders.add(field);
		}	
	}
	String selectedfields="";
	Row headerRow = sheet.createRow(0);
	for(int i=0;i<rowHeaders.size();i++)
	{

		String key=rowHeaders.get(i);


		if(i==0)
		{
			selectedfields=selectedfields+key;
		}		
		else
		{
			selectedfields=selectedfields+","+key;
		}

		switch (key) {
		
		case "ima_ida": {

			headerRow.createCell(i).setCellValue("IMA IDA");
			break;
		}      
		case "title": {

			headerRow.createCell(i).setCellValue("Title");
			break;
		}
		case "name": {

			headerRow.createCell(i).setCellValue("Name");
			break;
		}
		case "gender": {

			headerRow.createCell(i).setCellValue("Gender");
			break;
		}
		case "email": {

			headerRow.createCell(i).setCellValue("Email");
			break;
		}
		case "mobile_number": {

			headerRow.createCell(i).setCellValue("Mobile Number");
			break;
		}
		case "specialization": {

			headerRow.createCell(i).setCellValue("Specialization");
			break;
		}
		case "total_experience": {

			headerRow.createCell(i).setCellValue("Total Experience");
			break;
		}
		case "description": {

			headerRow.createCell(i).setCellValue("Description");
			break;
		}
		case "clinic_address": {

			headerRow.createCell(i).setCellValue("Clinic Address");
			break;
		}
		case "pincode": {

			headerRow.createCell(i).setCellValue("Pincode");
			break;
		}
		case "city": {

			headerRow.createCell(i).setCellValue("City");
			break;
		}
		case "website": {

			headerRow.createCell(i).setCellValue("Website");
			break;
		}
		case "linkedin": {

			headerRow.createCell(i).setCellValue("Linkedin");
		}
		case "facebook": {

			headerRow.createCell(i).setCellValue("Facebook");
			break;
		}
		case "sunday": {

			headerRow.createCell(i).setCellValue("Sunday");
			break;
		}
		case "monday": {

			headerRow.createCell(i).setCellValue("Monday");
			break;
		}
		case "tuesday": {

			headerRow.createCell(i).setCellValue("Tuesday");
			break;
		}
		case "wednesday": {

			headerRow.createCell(i).setCellValue("Wednesday");
			break;
		}
		case "thursday": {

			headerRow.createCell(i).setCellValue("Thursday");
			break;
		}
		case "Friday": {

			headerRow.createCell(i).setCellValue("Friday");
			break;
		}

		case "saturday": {

			headerRow.createCell(i).setCellValue("Saturday");
			break;
		}

		case "status": {

			headerRow.createCell(i).setCellValue("Status");
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}	
	}

if(fields.size()==1)
{
	List<String> dataList=getSingleData(selectedfields);
	int rowIndex = 1;
	for (String data : dataList) {
		Row row = sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(data);	
	}
}else {
	List<Object[]> dataList = (List<Object[]>)getSpecificData(selectedfields);

	// Populate rows with data
	int rowIndex = 1;
	for (Object[] data : dataList) {
		Row row = sheet.createRow(rowIndex++);

		for(int i=0;i<rowHeaders.size();i++)
		{
			row.createCell(i).setCellValue(data[i]!=null?data[i].toString():"");	
		}

	}
}
	return sheet;
  }
  public Sheet  downloadAllExcelData(Sheet sheet){
  Row headerRow = sheet.createRow(0);

	headerRow.createCell(0).setCellValue("ID");
	headerRow.createCell(1).setCellValue("IMA IDA");
	headerRow.createCell(2).setCellValue("Title");
	headerRow.createCell(3).setCellValue("Name");
	headerRow.createCell(4).setCellValue("Gender");
	headerRow.createCell(5).setCellValue("Email");
	headerRow.createCell(6).setCellValue("Mobile Number");
	headerRow.createCell(7).setCellValue("Specialization");
	headerRow.createCell(8).setCellValue("Total Experience");
	headerRow.createCell(9).setCellValue("Description");
	headerRow.createCell(10).setCellValue("Clinic Address");
	headerRow.createCell(11).setCellValue("Pincode");
	headerRow.createCell(12).setCellValue("City");
	headerRow.createCell(13).setCellValue("Website");
	headerRow.createCell(14).setCellValue("Linkedin");
	headerRow.createCell(15).setCellValue("Facebook");
	headerRow.createCell(16).setCellValue("Sunday");
	headerRow.createCell(17).setCellValue("Monday");
	headerRow.createCell(18).setCellValue("Tuesday");
	headerRow.createCell(19).setCellValue("Wednesday");
	headerRow.createCell(20).setCellValue("Thursday");
	headerRow.createCell(21).setCellValue("Friday");
	headerRow.createCell(22).setCellValue("Saturday");
	headerRow.createCell(23).setCellValue("Status");
	List<Profile> dataList = getAllData();

	// Populate rows with data
	int rowIndex = 1;
	for (Profile data : dataList) {
		Row row = sheet.createRow(rowIndex++);
		row.createCell(0).setCellValue(data.getId());
		row.createCell(1).setCellValue(data.getImaIda());
		row.createCell(2).setCellValue(data.getTitle());
		row.createCell(3).setCellValue(data.getName());
		row.createCell(4).setCellValue(data.getGender());
		row.createCell(5).setCellValue(data.getEmail());
		row.createCell(6).setCellValue(data.getMobileNumber());
		row.createCell(7).setCellValue(data.getSpecialization());
		row.createCell(8).setCellValue(data.getTotalExperience());
		row.createCell(9).setCellValue(data.getDescription());
		row.createCell(10).setCellValue(data.getClinicAddress());
		row.createCell(11).setCellValue(data.getPincode());
		row.createCell(12).setCellValue(data.getCity());
		row.createCell(13).setCellValue(data.getWebsite());
		row.createCell(14).setCellValue(data.getLinkedin());
		row.createCell(15).setCellValue(data.getFacebook());
		row.createCell(16).setCellValue(data.getSunday());
		row.createCell(17).setCellValue(data.getMonday());
		row.createCell(18).setCellValue(data.getTuesday());
		row.createCell(19).setCellValue(data.getWednesday());
		row.createCell(20).setCellValue(data.getThursday());
		row.createCell(21).setCellValue(data.getFriday());
		row.createCell(22).setCellValue(data.getSaturday());
		row.createCell(23).setCellValue(data.getStatus());

	}
  return sheet;
  
  }
  
  public List<FieldsAndViewValueDto> fieldsAndViewValue()
  {
	List<String>  fields =  profileRepository.getFieldsAndViewedValue();
	List<FieldsAndViewValueDto> list = new ArrayList<>();
	
	for(int i=0;i<fields.size();i++)
	{
		String key =fields.get(i);
		FieldsAndViewValueDto fieldsAndViewValue = new FieldsAndViewValueDto();
		switch(key){
		
		}
			switch (key) {
			case "ima_ida": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("IMA IDA");
				list.add(fieldsAndViewValue);
				break;
			}      
			case "title": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Title");
				break;
			}
			case "name": {

				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Name");
				list.add(fieldsAndViewValue);
				break;
			}
			case "gender": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Gender");
				list.add(fieldsAndViewValue);
				break;
			}
			case "email": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Email");
				list.add(fieldsAndViewValue);
				break;
			}
			case "mobile_number": {

				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Mobile Number");
				list.add(fieldsAndViewValue);
				break;
			}
			case "specialization": {
				
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Specialization");
				list.add(fieldsAndViewValue);
				break;
			}
			case "total_experience": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Total Experience");
				list.add(fieldsAndViewValue);
				break;
			}
			case "description": {

				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Description");
				list.add(fieldsAndViewValue);
				break;
			}
			case "clinic_address": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Clinic Address");
				list.add(fieldsAndViewValue);
				break;
			}
			case "pincode": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Pincode");
				list.add(fieldsAndViewValue);
				break;
			}
			case "city": {

				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("City");
				list.add(fieldsAndViewValue);
				
				break;
			}
			case "website": {

				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Website");
				list.add(fieldsAndViewValue);
				break;
			}
			case "linkedin": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Linkedin");
				list.add(fieldsAndViewValue);
				break;
			}
			case "facebook": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Facebook");
				list.add(fieldsAndViewValue);
				break;
			}
			
			case "status": {
				fieldsAndViewValue.setField(key);
				fieldsAndViewValue.setViewedValue("Status");
				list.add(fieldsAndViewValue);
				break;
			}

			default:
//				throw new IllegalArgumentException("Unexpected value: " + key);
			}			
	}
	FieldsAndViewValueDto fieldsAndViewValue = new FieldsAndViewValueDto();
	fieldsAndViewValue.setField("available");
	fieldsAndViewValue.setViewedValue("Available");
	list.add(fieldsAndViewValue);
	return list;
  }
  
  
}
