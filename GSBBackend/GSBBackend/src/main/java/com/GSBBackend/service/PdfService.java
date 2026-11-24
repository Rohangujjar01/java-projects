package com.GSBBackend.service;

import com.GSBBackend.model.Profile;
import com.GSBBackend.repository.ProfileRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {
	
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
        String sql = "SELECT "+fields +" FROM profile";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }
    
    public List<Profile> getAllData() {
        return profileRepository.findAll();
        }
    
    public byte[] generateDynamicPdf(List<String> fields) throws Exception {
    	
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<String> columnNames= new ArrayList<>();
    	for(String field : fields )
    	{
    		if(field.equals("available"))
    		{
    			columnNames.add("sunday");
    			columnNames.add("monday");
    			columnNames.add("tuesday");
    			columnNames.add("wednesday");
    			columnNames.add("thursday");
    			columnNames.add("Friday");
    			columnNames.add("saturday");
    		}
    		else {
    			columnNames.add(field);
    		}
        
    	}
        Document document = new Document();
        if(columnNames.size()<=7) {
         document = new Document(PageSize.A4.rotate());
        }else if(columnNames.size()<=11&&columnNames.size()>=8){
        	 document = new Document(PageSize.A3.rotate());
        }
        else if(columnNames.size()<=17&&columnNames.size()>=12){
        	 document = new Document(PageSize.A2.rotate());
        }
        else {
        	 document = new Document(PageSize.A1.rotate());
        }
        PdfWriter.getInstance(document, out);
        document.open();

        // Add a title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        document.add(new Paragraph("Profile details", titleFont));

        // Fetch column names dynamically
        
        
    	System.out.println(fields);
//    	  Set custom column widths (this can be adjusted)
        float[] columnWidths = new float[columnNames.size()];

        // Iterate over the headers and set widths dynamically
        for (int i = 0; i <columnNames.size(); i++) {
            if (columnNames.get(i).equals("description")) {
                columnWidths[i] = 2f; // Assign more width to the "Description" column
            } 
            else if(columnNames.get(i).equals("clinic_address")){
            	columnWidths[i] = 2f;
            }
            else if(columnNames.get(i).equals("status")||columnNames.get(i).equals("title")||columnNames.get(i).equals("gender")||columnNames.get(i).equals("ima_ida")
            		||columnNames.get(i).equals("sunday")||columnNames.get(i).equals("monday")||columnNames.get(i).equals("tuesday")
            		||columnNames.get(i).equals("thursday")||columnNames.get(i).equals("Friday")||columnNames.get(i).equals("saturday")){
            	columnWidths[i] = 0.75f;
            }
            else {
                columnWidths[i] = 1f; // Default width for other columns
            }
        }

        // Set the widths for the table
        
    	
    	
        // Create a table with the correct number of columns
        PdfPTable table = new PdfPTable(columnNames.size());
        if(columnNames.size()==3) {
        	table.setWidthPercentage(60);
        	}
        else if(columnNames.size()==1) {
        	table.setWidthPercentage(30);
        	}
        else if(columnNames.size()==2) {
        	table.setWidthPercentage(50);
        	}
        else {
        table.setWidthPercentage(100);
        }
        table.setSpacingBefore(10f);
        table.setWidths(columnWidths);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        String selectedfields="";
        // Add dynamic headers
        
            for(int i=0;i<columnNames.size();i++)
        	{
            	String key=columnNames.get(i);


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
        			table.addCell(getHeaderCell("IMA IDA"));
        			break;
        		}      
        		case "title": {

        			table.addCell(getHeaderCell("Title"));
        			break;
        		}
        		case "name": {

        			table.addCell(getHeaderCell("Name"));
        			break;
        		}
        		case "gender": {

        			table.addCell(getHeaderCell("Gender"));
        			break;
        		}
        		case "email": {

        			table.addCell(getHeaderCell("Email"));
        			break;
        		}
        		case "mobile_number": {

        			table.addCell(getHeaderCell("Mobile Number"));
        			break;
        		}
        		case "specialization": {

        			table.addCell(getHeaderCell("Specialization"));
        			break;
        		}
        		case "total_experience": {

        			table.addCell(getHeaderCell("Total Experience"));
        			break;
        		}
        		case "description": {

        			table.addCell(getHeaderCell("Description"));
        			break;
        		}
        		case "clinic_address": {

        			table.addCell(getHeaderCell("Clinic Address"));
        			break;
        		}
        		case "pincode": {

        			table.addCell(getHeaderCell("Pincode"));
        			break;
        		}
        		case "city": {

        			table.addCell(getHeaderCell("City"));
        			break;
        		}
        		case "website": {

        			table.addCell(getHeaderCell("Website"));
        			break;
        		}
        		case "linkedin": {

        			table.addCell(getHeaderCell("Linkedin"));
        			break;
        		}
        		case "facebook": {

        			table.addCell(getHeaderCell("Facebook"));
        			break;
        		}
        		case "sunday": {

        			table.addCell(getHeaderCell("Sunday"));
        			break;
        		}
        		case "monday": {

        			table.addCell(getHeaderCell("Monday"));
        			break;
        		}
        		case "tuesday": {

        			table.addCell(getHeaderCell("Tuesday"));
        			break;
        		}
        		case "wednesday": {

        			table.addCell(getHeaderCell("Wednesday"));
        			break;
        		}
        		case "thursday": {

        			table.addCell(getHeaderCell("Thursday"));
        			break;
        		}
        		case "Friday": {

        			table.addCell(getHeaderCell("Friday"));
        			break;
        		}

        		case "saturday": {

        			table.addCell(getHeaderCell("Saturday"));
        			break;
        		}

        		case "status": {

        			table.addCell(getHeaderCell("Status"));
        			break;
        		}

        		default:
        			throw new IllegalArgumentException("Unexpected value: " + key);
        		}	
        		}
            table.setHeaderRows(1);
        // Fetch table data dynamically
            if(columnNames.size()!=1) {
             List<Object[]> tableData = getSpecificData(selectedfields);
             
             for (Object[] row : tableData) {
                 for (Object cellData : row) {
                     table.addCell(getCell(cellData != null ? cellData.toString() : ""));
                 }
             }
            }else {
            	
            	 List<String> tableData = getSingleData(selectedfields);
                 
                 for ( String cellData : tableData) {
                         table.addCell(getCell(cellData != null ? cellData.toString() : ""));
                 }
            }
            
            
            
        document.add(table);
        document.close();

        return out.toByteArray();
    }
    
    public byte[] generateDynamicPdfForAllfields() throws DocumentException
    {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A1.rotate());
        PdfWriter.getInstance(document, out);
        document.open();

        // Add a title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        document.add(new Paragraph("Profile Details", titleFont));

        // Create a table with the correct number of columns
        PdfPTable table = new PdfPTable(24);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
      
        // Add dynamic headers
        table.addCell(getHeaderCell("ID"));
        table.addCell(getHeaderCell("IMA IDA"));
        table.addCell(getHeaderCell("Title"));
        table.addCell(getHeaderCell("Name"));
        table.addCell(getHeaderCell("Gender"));
        table.addCell(getHeaderCell("Email"));
        table.addCell(getHeaderCell("Mobile Number"));
        table.addCell(getHeaderCell("Specialization"));
        table.addCell(getHeaderCell("Total Experience"));
        table.addCell(getHeaderCell("Description"));
        table.addCell(getHeaderCell("Clinic Address"));
        table.addCell(getHeaderCell("Pincode"));
        table.addCell(getHeaderCell("City"));
        table.addCell(getHeaderCell("Website"));
        table.addCell(getHeaderCell("Linkedin"));
        table.addCell(getHeaderCell("Facebook"));
        table.addCell(getHeaderCell("Sunday"));
        table.addCell(getHeaderCell("Monday"));
        table.addCell(getHeaderCell("Tuesday"));
        table.addCell(getHeaderCell("Wednesday"));
        table.addCell(getHeaderCell("Thursday"));
        table.addCell(getHeaderCell("Friday"));
        table.addCell(getHeaderCell("Saturday"));
        table.addCell(getHeaderCell("Status"));
        table.setHeaderRows(1);
        List<Profile> dataList = getAllData();
        for (Profile profile : dataList) {
        	table.addCell(getCell(String.valueOf(profile.getId())));
            table.addCell(getCell(profile.getImaIda()));
            table.addCell(getCell(profile.getTitle()));
            table.addCell(getCell(profile.getName()));
            table.addCell(getCell(profile.getGender()));
            table.addCell(getCell(profile.getEmail()));
            table.addCell(getCell(profile.getMobileNumber()));
            table.addCell(getCell(profile.getSpecialization()));
            table.addCell(getCell(profile.getTotalExperience()));
            table.addCell(getCell(profile.getDescription()));
            table.addCell(getCell(profile.getClinicAddress()));
            table.addCell(getCell(profile.getPincode()));
            table.addCell(getCell(profile.getCity()));
            table.addCell(getCell(profile.getWebsite()));
            table.addCell(getCell(profile.getLinkedin()));
            table.addCell(getCell(profile.getFacebook()));
            table.addCell(getCell(profile.getSunday()));
            table.addCell(getCell(profile.getMonday()));
            table.addCell(getCell(profile.getTuesday()));
            table.addCell(getCell(profile.getWednesday()));
            table.addCell(getCell(profile.getThursday()));
            table.addCell(getCell(profile.getFriday()));
            table.addCell(getCell(profile.getSaturday()));
            table.addCell(getCell(profile.getStatus()));
        }
        
        
        document.add(table);
        document.close();

        return out.toByteArray();
    	
    	
    }
    
    private PdfPCell getHeaderCell(String text) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(223, 242, 191));
        cell.setPadding(8f);
        cell.setBorderColor(new BaseColor(211, 211, 211));
        return cell;
    }
    
    private PdfPCell getCell(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorderColor(new BaseColor(211, 211, 211));
//        cell.setPadding(8f);
        return cell;
    }
}


