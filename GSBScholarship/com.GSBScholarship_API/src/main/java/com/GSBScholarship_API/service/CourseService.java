package com.GSBScholarship_API.service;

import com.GSBScholarship_API.dto.ClassResponseDto;
import com.GSBScholarship_API.dto.CourseResponseDto;
import com.GSBScholarship_API.entity.Category;
import com.GSBScholarship_API.entity.SubCategory;
import com.GSBScholarship_API.repository.CategoryRepo;
import com.GSBScholarship_API.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	
	public CourseResponseDto getCategories(){
		CourseResponseDto courseResponseDto = new CourseResponseDto();
		List<Category> list =(List<Category>) categoryRepo.findAll();
		
		if(list.size()!=0)
    	{
			courseResponseDto.setId(1);
			courseResponseDto.setMessage("Record's found");
			courseResponseDto.setStatus("Success");
			courseResponseDto.setStatusCode(200);
			courseResponseDto.setCategoryList(list);
    	}
   else
   {
	   courseResponseDto.setId(1);
	   courseResponseDto.setMessage("Record's not found");
	   courseResponseDto.setStatus("Failed");
	   courseResponseDto.setStatusCode(200);   
   }
   return courseResponseDto;
	}
	

	public ClassResponseDto getSubCategories(String category) {
	    ClassResponseDto classResponseDto = new ClassResponseDto();

	    // Log the input category


	    List<SubCategory> subCategoryList = subCategoryRepo.getByCategory(category);

	    if (subCategoryList != null && !subCategoryList.isEmpty()) {
	        classResponseDto.setId(1);
	        classResponseDto.setMessage("Records found");
	        classResponseDto.setStatus("Success");
	        classResponseDto.setStatusCode(200); // Success status code
	        classResponseDto.setSubCategoryList(subCategoryList);
	    } else {
	        classResponseDto.setId(1);
	        classResponseDto.setMessage("Records not found");
	        classResponseDto.setStatus("Failed");
	        classResponseDto.setStatusCode(404); // Not Found status code
	    }

	    return classResponseDto;
	}

	
	
	
	
	
}
