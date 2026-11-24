package com.GSBScholarship_API.service;

import com.GSBScholarship_API.dto.*;
import com.GSBScholarship_API.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashBoardService {

	
	@Autowired
    private StudentRepo studentRepo;

    public MonthlyCountResponse getMonthlyStudentCount(String scholarshipYear) {
    	MonthlyCountResponse monthlyCountResponse = new MonthlyCountResponse();
        List<Object[]> results = studentRepo.getMonthlyStudentCount(scholarshipYear);
       List<MonthlyCountDTO> monthlyCountlist = results.stream()
                      .map(row -> new MonthlyCountDTO(row[0].toString(), ((Number) row[1]).intValue()))
                      .collect(Collectors.toList());
       if(monthlyCountlist.size()!=0)
		  {
    	   monthlyCountResponse.setId(1);  
    	   monthlyCountResponse.setStatus("Success");
    	   monthlyCountResponse.setStatusCode(200);
    	   monthlyCountResponse.setMessage("Record found");
    	   monthlyCountResponse.setMonthlyCountList(monthlyCountlist);
		   return monthlyCountResponse;
		  }
		  else
		  {
			  monthlyCountResponse.setId(-1);
			  monthlyCountResponse.setStatus("Failed");
			  monthlyCountResponse.setStatusCode(204);
			  monthlyCountResponse.setMessage("Record's not found");
			  return monthlyCountResponse;
		  }  
         
    }	
    
    
    public GenderCourseCountResponse getGenderCourseCount(String scholarshipYear) {
    	
    	GenderCourseCountResponse genderCourseCountResponse = new GenderCourseCountResponse();
        List<Object[]> results = studentRepo.getGenderCourseCount(scholarshipYear);
        List<GenderCourseCountDTO> genderCourseCountList = results.stream()
                      .map(row -> new GenderCourseCountDTO(row[0].toString(), row[1].toString(), ((Number) row[2]).intValue()))
                      .collect(Collectors.toList());
        
        if(genderCourseCountList.size()!=0)
		  {
        	genderCourseCountResponse.setId(1);  
        	genderCourseCountResponse.setStatus("Success");
        	genderCourseCountResponse.setStatusCode(200);
        	genderCourseCountResponse.setMessage("Record found");
        	genderCourseCountResponse.setGenderCourseCountList(genderCourseCountList);
		   return genderCourseCountResponse;
		  }
		  else
		  {
			  genderCourseCountResponse.setId(-1);
			  genderCourseCountResponse.setStatus("Failed");
			  genderCourseCountResponse.setStatusCode(204);
			  genderCourseCountResponse.setMessage("Record's not found");
			  return genderCourseCountResponse;
		  }  
        
    }
    
    public CourseRecordPercentageResponse  getCourseRecordPercentage(String scholarshipYear) {
    	CourseRecordPercentageResponse courseRecordPercentageResponse = new CourseRecordPercentageResponse();
        List<Object[]> results = studentRepo.getCourseRecordPercentage(scholarshipYear);
        List<CourseRecordPercentageDTO> courseRecordPercentageList = results.stream()
                      .map(row -> new CourseRecordPercentageDTO(
                              row[0].toString(),
                              ((Number) row[1]).intValue(),
                              ((Number) row[2]).doubleValue()))
                      .collect(Collectors.toList());
        
        if(courseRecordPercentageList.size()!=0)
		  {
        	courseRecordPercentageResponse.setId(1);  
        	courseRecordPercentageResponse.setStatus("Success");
        	courseRecordPercentageResponse.setStatusCode(200);
        	courseRecordPercentageResponse.setMessage("Record found");
        	courseRecordPercentageResponse.setCourseRecordPercentageList(courseRecordPercentageList);
		   return courseRecordPercentageResponse;
		  }
		  else
		  {
			  courseRecordPercentageResponse.setId(-1);
			  courseRecordPercentageResponse.setStatus("Failed");
			  courseRecordPercentageResponse.setStatusCode(204);
			  courseRecordPercentageResponse.setMessage("Record's not found");
			  return courseRecordPercentageResponse;
		  }
        
    }
    
    public GenderRecordPercentageResponse getGenderRecordPercentage(String scholarshipYear) {
    	
    	
    	GenderRecordPercentageResponse genderRecordPercentageResponse = new GenderRecordPercentageResponse();
        List<Object[]> results = studentRepo.getGenderRecordPercentage(scholarshipYear);
        List<GenderRecordPercentageDTO> genderRecordPercentageList=results.stream()
                      .map(row -> new GenderRecordPercentageDTO(
                              row[0].toString(),
                              ((Number) row[1]).intValue(),
                              ((Number) row[2]).doubleValue()))
                      .collect(Collectors.toList());
        
        if(genderRecordPercentageList.size()!=0)
		  {
        	genderRecordPercentageResponse.setId(1);  
        	genderRecordPercentageResponse.setStatus("Success");
        	genderRecordPercentageResponse.setStatusCode(200);
        	genderRecordPercentageResponse.setMessage("Record found");
        	genderRecordPercentageResponse.setGenderRecordPercentageResponse(genderRecordPercentageList);
		   return genderRecordPercentageResponse;
		  }
		  else
		  {
			  genderRecordPercentageResponse.setId(-1);
			  genderRecordPercentageResponse.setStatus("Failed");
			  genderRecordPercentageResponse.setStatusCode(204);
			  genderRecordPercentageResponse.setMessage("Record's not found");
			  return genderRecordPercentageResponse;
		  }
        
    }
    
    public CourseStatusCountsResponse getCourseStatusCounts(String scholarshipYear) {
    	CourseStatusCountsResponse courseStatusCountsResponse = new CourseStatusCountsResponse();
        List<Object[]> results = studentRepo.getCourseStatusCounts(scholarshipYear);
        List<CourseStatusCountsDTO> courseStatusCountsList = results.stream()
                      .map(row -> new CourseStatusCountsDTO(
                              row[0].toString(), 
                              ((Number) row[1]).intValue(),
                              ((Number) row[2]).intValue(),
                              ((Number) row[3]).intValue(),
                              ((Number) row[4]).intValue(),
                              ((Number) row[5]).intValue(),
                              ((Number) row[6]).intValue()))
                      .collect(Collectors.toList());
        if(courseStatusCountsList.size()!=0)
		  {
        	courseStatusCountsResponse.setId(1);  
        	courseStatusCountsResponse.setStatus("Success");
        	courseStatusCountsResponse.setStatusCode(200);
        	courseStatusCountsResponse.setMessage("Record found");
        	courseStatusCountsResponse.setCourseStatusCountsList(courseStatusCountsList);
		   return courseStatusCountsResponse;
		  }
		  else
		  {
			  courseStatusCountsResponse.setId(-1);
			  courseStatusCountsResponse.setStatus("Failed");
			  courseStatusCountsResponse.setStatusCode(204);
			  courseStatusCountsResponse.setMessage("Record's not found");
			  return courseStatusCountsResponse;
		  }
    }
    
    public ApplicationStatusCountResponse getApplicationStatusCounts(String scholarshipYear) {
    	
    	ApplicationStatusCountResponse applicationStatusCountResponse = new ApplicationStatusCountResponse();
        List<Object[]> results = studentRepo.getApplicationStatusCounts(scholarshipYear);

        List<ApplicationStatusCountDTO> applicationStatusCountList = results.stream()
                      .map(row -> new ApplicationStatusCountDTO(
                              row[0].toString(), // Application status
                              ((Number) row[1]).intValue(), // Record count
                              ((Number) row[2]).doubleValue())) // Percentage
                      .collect(Collectors.toList());
        if(applicationStatusCountList.size()!=0)
		  {
        	applicationStatusCountResponse.setId(1);  
        	applicationStatusCountResponse.setStatus("Success");
        	applicationStatusCountResponse.setStatusCode(200);
        	applicationStatusCountResponse.setMessage("Record found");
        	applicationStatusCountResponse.setApplicationStatusCountList(applicationStatusCountList);
		   return applicationStatusCountResponse;
		  }
		  else
		  {
			  applicationStatusCountResponse.setId(-1);
			  applicationStatusCountResponse.setStatus("Failed");
			  applicationStatusCountResponse.setStatusCode(204);
			  applicationStatusCountResponse.setMessage("Record's not found");
			  return applicationStatusCountResponse;
		  }
        
        
    }
    
    public long getStudentCountByScholarshipYear(String scholarshipYear) {
        return studentRepo.getStudentCountByScholarshipYear(scholarshipYear);
    }
    
    
}
