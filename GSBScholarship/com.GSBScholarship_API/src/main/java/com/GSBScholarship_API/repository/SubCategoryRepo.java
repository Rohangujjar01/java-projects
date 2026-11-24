package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

 @Query(value="select * from online_subcategory where category=:category",nativeQuery = true)
 List<SubCategory>  getByCategory(@Param("category") String category);	
}
