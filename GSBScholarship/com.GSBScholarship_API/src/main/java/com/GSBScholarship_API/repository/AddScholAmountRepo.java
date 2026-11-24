package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.AddSchoolAmount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddScholAmountRepo extends CrudRepository<AddSchoolAmount, Long> {
List<AddSchoolAmount> findBySyear(String mobileNo);	
}
